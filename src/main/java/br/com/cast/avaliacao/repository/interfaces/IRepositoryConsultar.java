package br.com.cast.avaliacao.repository.interfaces;

import br.com.cast.avaliacao.dto.PaginacaoResultado;
import br.com.cast.avaliacao.paginacao.Paginacao;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Selection;
import java.io.Serializable;
import java.util.List;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
public interface IRepositoryConsultar<O extends AbstractPersistable>  {

    O get(Serializable id);

    O get(String atributo, Object valor);

    O get(Specification<O> specification);

    List<O> listar();

    List<O> listar(Specification<O> specification);

    List<O> listar(List<Selection<?>> selections, Specification<O> specification);

    PaginacaoResultado<O> listar(Paginacao paginacao);

    boolean existe(Specification<O> specification);

    <T> T getValorAtributo(String atributo, Serializable id);

    <T> T getValorAtributo(String atributo, Specification<O> specification);
}
