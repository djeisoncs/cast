package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.dto.PaginacaoResultado;
import br.com.cast.avaliacao.model.Entidade;
import br.com.cast.avaliacao.paginacao.Paginacao;
import br.com.cast.avaliacao.repository.RepositoryConsultarImpl;
import br.com.cast.avaliacao.service.interfaces.IServiceConsultar;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

/**
 * Created by djeison.cassimiro on 27/12/2020
 */
public abstract class ServiceConsultarImpl<E extends Entidade> implements IServiceConsultar<E> {

    @Override
    public E get(Serializable id) { return getRepository().get(id); }

    @Override
    public E get(String atributo, Object object) { return getRepository().get(atributo, object); }

    @Override
    public E get(Specification<E> specification) { return getRepository().get(specification); }

    @Override
    public List<E> listar() { return getRepository().listar(); }

    @Override
    public PaginacaoResultado<E> listar(Paginacao paginacao) { return getRepository().listar(paginacao); }

    @Override
    public <T> T getValorAtributo(String atributo, Serializable id) { return getRepository().getValorAtributo(atributo, id); }

    @Override
    public <T> T getValorAtributo(String atributo, Specification<E> specification) { return getValorAtributo(atributo, specification); }

    protected abstract RepositoryConsultarImpl<E> getRepository();
}
