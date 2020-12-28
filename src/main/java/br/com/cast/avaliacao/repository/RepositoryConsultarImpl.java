package br.com.cast.avaliacao.repository;

import br.com.cast.avaliacao.anotacoes.EntityProperties;
import br.com.cast.avaliacao.dto.PaginacaoResultado;
import br.com.cast.avaliacao.model.Entidade;
import br.com.cast.avaliacao.paginacao.Filtro;
import br.com.cast.avaliacao.paginacao.Operacao;
import br.com.cast.avaliacao.paginacao.Paginacao;
import br.com.cast.avaliacao.repository.interfaces.IRepositoryConsultar;
import br.com.cast.avaliacao.util.AliasToBeanNestedResultTransformer;
import br.com.cast.avaliacao.util.ColecaoUtil;
import br.com.cast.avaliacao.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.hibernate.property.access.internal.PropertyAccessStrategyFieldImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
@Slf4j
public abstract class RepositoryConsultarImpl<E extends Entidade> implements IRepositoryConsultar<E> {

    @Autowired
    private EntityManager entityManager;

    @Override
    public E get(Serializable id) {
        return get(getNameIdProperty(), id);
    }

    @Override
    public E get(String atributo, Object valor) {
        return get((Specification<E>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(atributo), valor));
    }

    public E get(Specification<E> specification) {
        return get(entidadeClass(), specification);
    }

    public <T extends AbstractPersistable> T get(Class<T> classe, Specification<T> specification) {
        T entidade;


            CriteriaBuilder criteriaBuilder = criteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(classe);
            Root<T> root = criteriaQuery.from(classe);

            List<Predicate> predicates = getPredicatesDefault(root, criteriaQuery, criteriaBuilder);
            predicates.add(specification.toPredicate(root, criteriaQuery, criteriaBuilder));

            criteriaQuery.where(predicates.toArray(new Predicate[0]));

            entidade = getSingleResult(criteriaQuery);


        return entidade;
    }

    public <T> T getValorAtributo(String atributo, Serializable id) {
        return getValorAtributo(atributo, (Specification<E>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(getNameIdProperty()), id));
    }

    public <T> T getValorAtributo(String atributo, Specification<E> specification) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        Root<E> root = criteriaQuery.from(entidadeClass());

        criteriaQuery.select(getPathByValue(root, atributo));
        criteriaQuery.where(specification.toPredicate(root, criteriaQuery, criteriaBuilder));

        TypedQuery<Object> typedQuery = getEntityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(1);

        T valorAtributo = null;

        try {
            valorAtributo = (T) typedQuery.getSingleResult();

        } catch (NoResultException ignored) { }

        return valorAtributo;
    }

    public <T> T getValoresAtributos(List<Selection> selecions, Specification<T> specification) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<T> root = criteriaQuery.from((Class<T>) entidadeClass());

        criteriaQuery.where(specification.toPredicate(root, criteriaQuery, criteriaBuilder));
        criteriaQuery.multiselect(selecions.toArray(new Selection[0]));

        TypedQuery<Tuple> typedQuery = getEntityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(1);

        T valorAtributo = null;

        try {
            Tuple tuple = typedQuery.getSingleResult();
            valorAtributo = AliasToBeanNestedResultTransformer.transformTuple(tuple, (Class<T>) entidadeClass());

        } catch (NoResultException ignored) {
        }

        return valorAtributo;
    }

    public boolean existe(Specification<E> specification) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root root = criteriaQuery.from(entidadeClass());

        criteriaQuery.select(criteriaBuilder.countDistinct(root.get(getNameIdProperty())));
        criteriaQuery.where(specification.toPredicate(root, criteriaQuery, criteriaBuilder));

        return getEntityManager().createQuery(criteriaQuery).getSingleResult() > 0;
    }

    @Override
    public List<E> listar() {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<E> root = criteriaQuery.from(entidadeClass());

        criarProjectionsConsultar(criteriaBuilder, criteriaQuery, root, entidadeClass());

        List<Tuple> tuples = getEntityManager().createQuery(criteriaQuery).getResultList();

        return tuples
                .stream()
                .map(tuple -> AliasToBeanNestedResultTransformer.transformTuple(tuple, entidadeClass())).peek(entidade -> {
                    preencherCollectionsWithProjections(entidade);
                    preencherInnerCollectionsWithProjections(entidade);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<E> listar(Specification<E> specification) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<E> root = criteriaQuery.from(entidadeClass());

        criarProjectionsConsultar(criteriaBuilder, criteriaQuery, root, entidadeClass());

        criteriaQuery.where(specification.toPredicate(root, criteriaQuery, criteriaBuilder));

        List<Tuple> tuples = getEntityManager().createQuery(criteriaQuery).getResultList();

        return tuples
                .stream()
                .map(tuple -> AliasToBeanNestedResultTransformer.transformTuple(tuple, entidadeClass())).peek(entidade -> {
                    preencherCollectionsWithProjections(entidade);
                    preencherInnerCollectionsWithProjections(entidade);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<E> listar(List<Selection<?>> selections,  Specification<E> specification) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<E> root = criteriaQuery.from(entidadeClass());

        criteriaQuery.where(specification.toPredicate(root, criteriaQuery, criteriaBuilder));
        criteriaQuery.multiselect(selections);

        TypedQuery<Tuple> typedQuery = getEntityManager().createQuery(criteriaQuery);

        return typedQuery.getResultList().stream().map(tuple -> AliasToBeanNestedResultTransformer.transformTuple(tuple, entidadeClass())).collect(Collectors.toList());
    }


    @Override
    public PaginacaoResultado<E> listar(Paginacao paginacao) {
        PaginacaoResultado<E> paginacaoResultado;

//        long contar = contar(paginacao);

//        if (contar > 0) {
            CriteriaBuilder criteriaBuilder = criteriaBuilder();
            CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entidadeClass());
            Root<E> root = criteriaQuery.from(entidadeClass());

            addWhereInCriteriaQuery(paginacao, criteriaBuilder, criteriaQuery, root);
            addOrdenacaoPorPaginacao(paginacao, criteriaBuilder, criteriaQuery, root);

            TypedQuery<E> typedQuery = getEntityManager().createQuery(criteriaQuery);
            addPaginacaoInTypeQuery(paginacao, typedQuery);

            paginacaoResultado = new PaginacaoResultado<>(typedQuery.getResultList(), 0L);

//        } else {
//            paginacaoResultado = PaginacaoResultado.paginacaoResultadoEmpty();
//        }

        return paginacaoResultado;
    }

    public <T> List<T> mapTypeQueryList(TypedQuery<Tuple> typedQuery, Class<T> classe){
        return typedQuery.getResultList().stream().map(tuple -> AliasToBeanNestedResultTransformer.transformTuple(tuple, classe)).collect(Collectors.toList());
    }

    public Long contar(Specification<E> specification){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root root = criteriaQuery.from(entidadeClass());

        criteriaQuery.select(criteriaBuilder.countDistinct(root.get(getNameIdProperty())));

        criteriaQuery.where(specification.toPredicate(root, criteriaQuery, criteriaBuilder));

        return getEntityManager().createQuery(criteriaQuery).getSingleResult();
    }

    private <T extends AbstractPersistable> void preencherCollectionsWithProjections(T entidade) {
        if (!Objects.isNull(entidade)) {
            preencherCollectionsWithProjections(entidade, FieldUtils.getAllFieldsList(entidade.getClass()).stream().filter(this::fieldsPermitidosCollections).collect(Collectors.toList()));
        }
    }

    private <T extends AbstractPersistable> void preencherInnerCollectionsWithProjections(T entidade) {
        if (!Objects.isNull(entidade)) {
            FieldUtils.getFieldsListWithAnnotation(entidade.getClass(), EntityProperties.class).stream()
                    .filter(field -> {
                        boolean isNotCollection = !Collection.class.isAssignableFrom(field.getType());
                        boolean hasCollections = field.getAnnotation(EntityProperties.class).collecions().length > 0;
                        return isNotCollection && hasCollections;

                    }).forEach(field -> {
                AbstractPersistable entidadeTemp = (AbstractPersistable) PropertyAccessStrategyFieldImpl.INSTANCE.buildPropertyAccess(entidade.getClass(), field.getName()).getGetter().get(entidade);
                preencherCollectionsWithProjections(entidadeTemp);
            });
        }
    }

    private <T extends AbstractPersistable> void preencherCollectionsWithProjections(T entidade, List<Field> fields) {
        fields.forEach(field -> {
            EntityProperties entityProperties = field.getAnnotation(EntityProperties.class);

            CriteriaBuilder criteriaBuilder = criteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
            Root root = criteriaQuery.from(entidade.getClass());

            createJoin(root, field.getName(), JoinType.INNER);

            criteriaQuery.where(criteriaBuilder.equal(root.get(getNameIdProperty(entidade)), entidade.getId()));

            List<Selection> selections = getProjectionsDoEntityProperties(field, root, false);
            criteriaQuery.select(criteriaBuilder.tuple(selections.toArray(new Selection[0])));

            TypedQuery<Tuple> typedQuery = getEntityManager().createQuery(criteriaQuery);
            Stream stream = typedQuery.getResultList().stream()
                    .map(tuple -> AliasToBeanNestedResultTransformer.transformTuple(tuple, entityProperties.targetEntity()));

            Collection<T> valor = (Collection<T>) (field.getType().equals(Set.class) ? stream.collect(Collectors.toSet()) : stream.collect(Collectors.toList()));

            if (entityProperties.collecions().length > 0) {
                List<String> collecionsFields = Arrays.asList(entityProperties.collecions());
                valor.forEach(valorTemp -> {
                    preencherCollectionsWithProjections(valorTemp, Arrays.stream(FieldUtils.getAllFields(valorTemp.getClass())).filter(fieldTemp -> collecionsFields.contains(fieldTemp.getName())).collect(Collectors.toList()));
                });
            }

            PropertyAccessStrategyFieldImpl.INSTANCE
                    .buildPropertyAccess(entidade.getClass(), field.getName())
                    .getSetter()
                    .set(entidade, valor, null);
        });
    }

    protected void addPaginacaoInTypeQuery(Paginacao paginacao, TypedQuery typedQuery) {
        if (!paginacao.getListarTodos()) {
            typedQuery.setFirstResult(paginacao.getPagina() * paginacao.getQuantidade()).setMaxResults(paginacao.getQuantidade());
        }
    }

    protected void addOrdenacaoPorPaginacao(Paginacao paginacao, CriteriaBuilder criteriaBuilder, CriteriaQuery criteriaQuery, Root root) {
        List<Order> orders = new ArrayList<>();

        if (ColecaoUtil.isNotEmptyNull(paginacao.getOrdenacoes())) {

            paginacao.getOrdenacoes().forEach(ordenacao -> {
                switch (ordenacao.getTipoOrdenacao()) {
                    case ASC:
                        orders.add(criteriaBuilder.asc(getPathByValue(root, ordenacao.getCampo())));
                        break;
                    case DESC:
                        orders.add(criteriaBuilder.desc(getPathByValue(root, ordenacao.getCampo())));
                        break;
                }
            });
        }

        if (!orders.isEmpty()) {
            criteriaQuery.orderBy(orders);
        }
    }

    protected void addWhereInCriteriaQuery(Paginacao paginacao, CriteriaBuilder criteriaBuilder, CriteriaQuery criteriaQuery, Root root) {
        List<Predicate> predicates = getPredicatesDefault(root, criteriaQuery, criteriaBuilder);
        List<Predicate> predicatesHaving = new ArrayList<>();

        if (ColecaoUtil.isNotEmptyNull(paginacao.getFiltros())) {

            paginacao.getFiltros().stream()
                    .filter(filtro -> !Objects.isNull(filtro))
                    .forEach(filtro -> {
                        if (Objects.nonNull(filtro.getOperacao()) && filtro.getOperacao().filtroHaving()) {

                            addfiltroNosPredicates(criteriaBuilder, root, filtro, predicatesHaving);

                        } else if (filtro.hasFiltrosOr()) {
                            List<Predicate> predicatesOr = new ArrayList<>();
                            filtro.getFiltrosOr().forEach(filtroOr -> addfiltroNosPredicates(criteriaBuilder, root, filtroOr, predicatesOr));

                            if (!predicatesOr.isEmpty()) {
                                predicates.add(criteriaBuilder.or(predicatesOr.toArray(new Predicate[0])));
                            }

                        } else if(filtro.hasFiltrosAnd()){
                            List<Predicate> predicatesAnd = new ArrayList<>();
                            filtro.getFiltrosAnd().forEach(filtroAnd -> addfiltroNosPredicates(criteriaBuilder, root, filtroAnd, predicatesAnd));

                            if (!predicatesAnd.isEmpty()) {
                                predicates.add(criteriaBuilder.and(predicatesAnd.toArray(new Predicate[0])));
                            }

                        } else {
                            addfiltroNosPredicates(criteriaBuilder, root, filtro, predicates);

                        }
                    });
        }

        if (paginacao.possuiSpecification()) {
            predicates.add(paginacao.getSpecification().toPredicate(root, criteriaQuery, criteriaBuilder));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        if (Objects.nonNull(predicatesHaving) && !predicatesHaving.isEmpty()) {

            criteriaQuery.having(predicatesHaving.toArray(new Predicate[0]));
        }
    }

    private void addfiltroNosPredicates(CriteriaBuilder criteriaBuilder, Root root, Filtro filtro, List<Predicate> predicates) {
        if(filtro.hasValor() || !filtro.getOperacao().necessitaDeValor()){
            if (filtro.hasJoins()) {
                filtro.getJoins().forEach(join -> createJoin(root, join.getAssociationPath(), join.getAlias(), join.getJoinTipo()));
            }

            Predicate predicate = getPredicateFromFiltro(criteriaBuilder, root, filtro);
            if (!Objects.isNull(predicate)) {
                predicates.add(predicate);
            }
        }
    }

    private Predicate getPredicateFromFiltro(CriteriaBuilder criteriaBuilder, Root root, Filtro filtro) {
        Operacao operacao = filtro.getOperacao();
        Object[] valor = filtro.getValorTransformado();

        return operacao.restricao(criteriaBuilder, getPathByValue(root, filtro.getCampo()), valor);
    }

    protected void criarProjectionsConsultar(CriteriaBuilder criteriaBuilder, CriteriaQuery<Tuple> criteriaQuery, From root, Class classeFields) {
        List<Selection> selecions = new ArrayList<>();

        FieldUtils.getAllFieldsList(classeFields).stream()
                .filter(this::fieldsPermitidosConsultarProjections)
                .forEach(field -> {
                    if (field.isAnnotationPresent(EntityProperties.class)) {
                        selecions.addAll(getProjectionsDoEntityProperties(field, root));

                    } else {
                        selecions.add(root.get(field.getName()).alias(field.getName()));
                    }
                });


        criteriaQuery.select(criteriaBuilder.tuple(selecions.toArray(new Selection[0])));
    }

    private List<Selection> getProjectionsDoEntityProperties(Field field, From root) {
        return getProjectionsDoEntityProperties(field, root, true);
    }

    private List<Selection> getProjectionsDoEntityProperties(Field field, From root, boolean adicionarFieldNameAoAlias) {
        List<Selection> selecions = new ArrayList<>();

        getPropertiesFromField(field).forEach(property -> {
            From join = createJoin(root, field.getName(), JoinType.LEFT);

            if (property.contains(".")) {
                selecions.add(getPathByValue(join, property, true).alias(adicionarFieldNameAoAlias ? field.getName() + "." + property : property));

            } else {
                Class<?> classType = field.getType();
                if (field.isAnnotationPresent(EntityProperties.class)) {
                    EntityProperties entityProperties = field.getAnnotation(EntityProperties.class);
                    if (entityProperties.targetEntity() != void.class) {
                        classType = entityProperties.targetEntity();
                    }
                }

                Field propertyField = FieldUtils.getField(classType, property, true);
                boolean isEntidade = propertyField != null && AbstractPersistable.class.isAssignableFrom(propertyField.getType());
                if (isEntidade) {
                    From innerRoot = createJoin(join, property, JoinType.LEFT);
                    selecions.addAll(getProjectionsDoEntityProperties(field, innerRoot));

                } else {
                    selecions.add(join.get(property).alias(adicionarFieldNameAoAlias ? field.getName() + "." + property : property));
                }
            }
        });

        return selecions;
    }

    protected Path getPathByValue(Path root, String value) {
        return getPathByValue(root, value, false);
    }

    protected Path getPathByValue(Path root, String value, boolean juntarAliasRootNoJoin) {
        Path path;

        if (value.contains(".")) {
            From from = (From) root;

            String[] campos = value.split("\\.");
            String ultimoCampo = campos[campos.length - 1];

            for (int index = 0; index < campos.length - 1; index++) {
                String joinAlias = juntarAliasRootNoJoin ? from.getAlias()+campos[index] : campos[index];
                from = createJoin(from, campos[index], joinAlias, JoinType.LEFT);
            }

            path = from.get(ultimoCampo);
        } else {

            path = root.get(value);

        }

        return path;
    }

    private List<String> getPropertiesFromField(Field field) {
        EntityProperties entityProperties = field.getAnnotation(EntityProperties.class);

        List<String> properties = new ArrayList<>();

        if (entityProperties.value().length > 0) {
            properties.addAll(Arrays.asList(entityProperties.value()));

        } else {
            Class<?> classe = Collection.class.isAssignableFrom(field.getType()) ? entityProperties.targetEntity() : field.getType();

            FieldUtils.getAllFieldsList(classe).forEach(fieldTemp -> {
                if (fieldTemp.isAnnotationPresent(EntityProperties.class) && !Collection.class.isAssignableFrom(fieldTemp.getType())) {
                    EntityProperties fieldTempEntityProperty = fieldTemp.getAnnotation(EntityProperties.class);
                    if (fieldTempEntityProperty.value().length > 0) {
                        Arrays.stream(fieldTempEntityProperty.value()).forEach(propertyTemp -> properties.add(fieldTemp.getName() + "." + propertyTemp));

                    } else {
                        FieldUtils.getAllFieldsList(fieldTemp.getType()).stream()
                                .filter(this::fieldsPermitidosConsultarProjections)
                                .forEach(item -> {

                                    if(AbstractPersistable.class.isAssignableFrom(item.getType())){
                                        getPropertiesFromField(item).forEach(innerFiel -> {
                                            properties.add(fieldTemp.getName() + "." + item.getName() + "." + innerFiel);
                                        });
                                    } else {
                                        properties.add(fieldTemp.getName() + "." + item.getName());
                                    }

                                });
                    }
                } else if (fieldsPermitidosConsultarProjections(fieldTemp)) {
                    properties.add(fieldTemp.getName());

                }
            });
        }

        return properties;
    }

    private boolean fieldsPermitidosConsultarProjections(Field field) {
        boolean isCollection = Collection.class.isAssignableFrom(field.getType());
        boolean isTransient = field.isAnnotationPresent(Transient.class);
        boolean isEntityWithoutEntityProperties = field.getType().isAnnotationPresent(Entity.class) && !field.isAnnotationPresent(EntityProperties.class);

        return !isCollection && !isTransient && !isEntityWithoutEntityProperties;
    }

    private boolean fieldsPermitidosCollections(Field field) {
        boolean isTransient = field.isAnnotationPresent(Transient.class);
        boolean isCollection = Collection.class.isAssignableFrom(field.getType());
        boolean isAnnotationEntityPropertiesPresent = field.isAnnotationPresent(EntityProperties.class);

        return !isTransient && isCollection && isAnnotationEntityPropertiesPresent;
    }

    protected final <T extends AbstractPersistable> T getSingleResult(CriteriaQuery<T> criteriaQuery) {
        return getSingleResult(getEntityManager().createQuery(criteriaQuery));
    }

    protected final <T> T getSingleResult(CriteriaQuery<Tuple> criteriaQuery, Class<T> classe) {
        T t = null;

        Tuple tuple = getSingleResult(getEntityManager().createQuery(criteriaQuery));

        if (!Objects.isNull(tuple)) {
            t = AliasToBeanNestedResultTransformer.transformTuple(tuple, classe);
        }

        return t;
    }

    protected final <T> T getSingleResult(TypedQuery<T> typedQuery) {
        T t;

        try {
            typedQuery.setMaxResults(1);
            t = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            t = null;
        }

        return t;
    }

    protected List<Predicate> getPredicatesDefault(Root<?> root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return new ArrayList<>();
    }

    protected final Class<E> entidadeClass() {
        return (Class<E>) Util.getTypes(this)[0];
    }

    protected String getNameIdProperty() {
        return getNameIdProperty(entidadeClass());
    }

    protected String getNameIdProperty(Object entidade) {
        return getNameIdProperty(entidade.getClass());
    }

    protected String getNameIdProperty(Class<?> classeEntidade) {
        Field field = FieldUtils.getFieldsWithAnnotation(classeEntidade, Id.class)[0];

        return field.getName();
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected final Join createJoin(From from, String field, JoinType joinType) {
        return createJoin(from, field, field, joinType);
    }

    protected final Join createJoin(From from, String field) {
        return createJoin(from, field, field);
    }

    protected final Join createJoin(From from, String field, String alias) {
        return createJoin(from, field, alias, JoinType.INNER);
    }

    protected final Join createJoin(From from, String field, String alias, JoinType joinType) {
        String[] fields = field.split("\\.");

        From nextFrom = from;

        if (fields.length > 0) {
            for (int index = 0; index < fields.length - 1; index++) {
                nextFrom = createJoin(nextFrom, fields[index]);
            }
            field = fields[fields.length - 1];
        }

        Join join = getJoinByAlias(nextFrom, alias);

        if (Objects.isNull(join)) {
            join = nextFrom.join(field, joinType);
            join.alias(alias);
        }

        return join;
    }

    protected Join getJoinByAlias(From from, String alias) {
        Join join = null;

        Set<Join> joins = from.getJoins();

        if (!Objects.isNull(joins) && !joins.isEmpty()) {
            for (Join joinTemp : joins) {
                if (alias.equals(joinTemp.getAlias())) {
                    join = joinTemp;

                } else {
                    join = getJoinByAlias(joinTemp, alias);

                }

                if (!Objects.isNull(join)) {
                    break;
                }
            }
        }

        return join;
    }

    protected final CriteriaBuilder criteriaBuilder() {
        return getEntityManager().getCriteriaBuilder();
    }
}
