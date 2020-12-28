package br.com.cast.avaliacao.repository;

import br.com.cast.avaliacao.i18n.MensagemI18N;
import br.com.cast.avaliacao.model.Entidade;
import br.com.cast.avaliacao.model.Status;
import br.com.cast.avaliacao.repository.interfaces.IRepository;
import br.com.cast.avaliacao.util.NegocioException;
import br.com.cast.avaliacao.util.RegraNegocioList;
import br.com.cast.avaliacao.util.Util;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.hibernate.Session;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
@Transactional
public abstract class RepositoryImpl<E extends Entidade> extends RepositoryConsultarImpl<E> implements IRepository<E> {

    protected final Session getSession(){
        return getEntityManager().unwrap(Session.class);
    }

    protected List<Predicate> getPredicatesDefault(Root<?> root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder){
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(root.get("status"), Status.ATIVO));

        return predicates;
    }

    protected void beforeSalvarAlterar(E entidade) throws NegocioException {
        NegocioException negocioException = new NegocioException();

        getRegrasBeforeSalvarAlterar(entidade).forEach(regraNegocio -> {
            CriteriaBuilder criteriaBuilder = criteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<E> root = criteriaQuery.from(entidadeClass());

            if(regraNegocio.hasSpecification()){
                List<Predicate> predicates = getPredicatesDefault(root, criteriaQuery, criteriaBuilder);

                if(!entidade.isNew()){
                    predicates.add(criteriaBuilder.notEqual(root.get(getNameIdProperty()), entidade.getId()));
                }

                predicates.add(regraNegocio.getPredicate(criteriaBuilder, criteriaQuery, root));

                criteriaQuery.where(predicates.toArray(new Predicate[0]));
                criteriaQuery.select(criteriaBuilder.count(root.get(getNameIdProperty())));

                Long qtdeRegistros = getEntityManager().createQuery(criteriaQuery).getSingleResult();

                if(qtdeRegistros > 0){
                    negocioException.addMensagem(MensagemI18N.getKey(regraNegocio.getKey()));
                }

            } else {
                negocioException.addMensagem(MensagemI18N.getKey(regraNegocio.getKey()));

            }
        });

        negocioException.lancar();
    }

    public void alterarAtributos(E entidade, String... atributos){
        StringBuilder sqlUpdate = new StringBuilder();

        Table table = entidade.getClass().getAnnotation(Table.class);

        sqlUpdate.append("update ");
        if(!StringUtils.isEmpty(table.schema())){
            sqlUpdate.append(table.schema()).append(".");
        }
        sqlUpdate.append(table.name()).append(" set ");

        Map<String, Object> valoresPorParametro = new HashMap<>();

        Arrays.stream(atributos).forEach(atributo -> {
            Field field = FieldUtils.getField(entidade.getClass(), atributo, true);

            String nomeColune;
            if(field.isAnnotationPresent(JoinColumn.class)){
                JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
                nomeColune = joinColumn.name();

            } else if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                nomeColune = column.name().length() > 0 ? column.name() : field.getName();

            } else {
                nomeColune = field.getName();

            }

            Object valor = Util.getValorFromField(entidade, field);


            sqlUpdate.append(nomeColune).append(" = ").append(Objects.isNull(valor) ? "null" : ":"+atributo).append(", ");

            if(!Objects.isNull(valor)){
                if(valor.getClass().isEnum()){
                    Enumerated enumerated = field.getAnnotation(Enumerated.class);

                    Enum valorEnum = (Enum) valor;
                    valor = enumerated.value() == EnumType.ORDINAL ? valorEnum.ordinal() : valorEnum.name();

                } else if(valor instanceof AbstractPersistable){
                    valor = ((AbstractPersistable) valor).getId();

                }

                valoresPorParametro.put(atributo, valor);
            }
        });

        sqlUpdate.delete(sqlUpdate.length()-2, sqlUpdate.length());
        sqlUpdate.append(" where ").append(getNameIdProperty()).append(" = :id");
        valoresPorParametro.put("id", entidade.getId());

        Query query = getEntityManager().createNativeQuery(sqlUpdate.toString());
        valoresPorParametro.forEach(query::setParameter);
        query.executeUpdate();
    }

    @Override
    public void salvar(E entidade)throws NegocioException {
        beforeSalvarAlterar(entidade);
        entidade.ativar();

        getEntityManager().persist(entidade);
        getEntityManager().flush();

        afterSalvarAlterar(entidade);
    }

    @Override
    public void alterar(E entidade)throws NegocioException{
        beforeSalvarAlterar(entidade);

        getEntityManager().merge(entidade);
        getEntityManager().flush();

        afterSalvarAlterar(entidade);
    }

    @Override
    public void excluir(UUID id)throws NegocioException {
        try {
            E entidade = this.entidadeClass().newInstance();
            entidade.setId(id);
            entidade.desativar();
            alterar(entidade);

        }catch (Exception e){
            throw NegocioException.build(MensagemI18N.getKey("operacao.excluir.falha"));
        }
    }

    @Override
    public void excluirDefinitivamente(Serializable id) {
        E entidade = getEntityManager().find(entidadeClass(), id);
        getEntityManager().remove(entidade);
        getEntityManager().flush();
    }

    private interface OnTransactionCallBack{
        void execute();
    }

    protected RegraNegocioList getRegrasBeforeSalvarAlterar(E entidade){
        return RegraNegocioList.build();
    }

    protected void afterSalvarAlterar(E entidade) throws NegocioException {}
}
