package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.model.Entidade;
import br.com.cast.avaliacao.model.Status;
import br.com.cast.avaliacao.service.interfaces.IService;
import br.com.cast.avaliacao.util.NegocioException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by djeison.cassimiro on 27/12/2020
 */
public abstract class ServiceImpl<E extends Entidade> extends ServiceConsultarImpl<E> implements IService<E> {

    @Override
    public void salvar(E entidade) throws NegocioException {
        beforeSalvarAlterar(entidade);

        getRepository().save(entidade);

        afterSalvarAlterar(entidade);
    }

    @Override
    public void editar(E entidade) throws NegocioException {
        beforeSalvarAlterar(entidade);

        getRepository().save(entidade);

        afterSalvarAlterar(entidade);
    }

    @Override
    public void excluir(UUID id) throws NegocioException {
        beforeExcluir(id);

        getRepository().deleteById(id);
    }

    protected void beforeSalvarAlterar(E entidade) throws NegocioException {
        entidade.setStatus(Status.ATIVO);
        getRegrasBeforeSalvarAlterar(entidade).lancar();
    }

    protected NegocioException getRegrasBeforeSalvarAlterar(E entidade) { return NegocioException.build(); }

    protected void afterSalvarAlterar(E entidade) throws NegocioException {}

    protected void beforeExcluir(Serializable id) throws NegocioException { NegocioException.build().lancar(); }

    protected abstract JpaRepository<E, UUID> getRepository();
}
