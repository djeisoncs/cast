package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.repository.RepositoryImpl;
import br.com.cast.avaliacao.service.interfaces.IService;
import br.com.cast.avaliacao.util.NegocioException;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;

/**
 * Created by djeison.cassimiro on 27/12/2020
 */
public abstract class ServiceImpl<E extends AbstractPersistable> extends ServiceConsultarImpl<E> implements IService<E> {

    @Override
    public void salvar(E entidade) throws NegocioException {
        beforeSalvarAlterar(entidade);

        getRepository().salvar(entidade);

        afterSalvarAlterar(entidade);
    }

    @Override
    public void alterar(E entidade) throws NegocioException {
        beforeSalvarAlterar(entidade);

        getRepository().alterar(entidade);

        afterSalvarAlterar(entidade);
    }

    @Override
    public void excluir(Serializable id) throws NegocioException {
        beforeExcluir(id);

        getRepository().excluir(id);
    }

    @Override
    public void alterarAtributos(E entidade, String... atributos) { getRepository().alterarAtributos(entidade, atributos); }

    protected void beforeSalvarAlterar(E entidade) throws NegocioException { getRegrasBeforeSalvarAlterar(entidade).lancar(); }

    protected NegocioException getRegrasBeforeSalvarAlterar(E entidade) { return NegocioException.build(); }

    protected void afterSalvarAlterar(E entidade) throws NegocioException {}

    protected void beforeExcluir(Serializable id) throws NegocioException { NegocioException.build().lancar(); }

    protected abstract RepositoryImpl<E> getRepository();
}
