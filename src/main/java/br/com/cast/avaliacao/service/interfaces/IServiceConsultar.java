package br.com.cast.avaliacao.service.interfaces;

import br.com.cast.avaliacao.dto.PaginacaoResultado;
import br.com.cast.avaliacao.paginacao.Paginacao;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

/**
 * Created by djeison.cassimiro on 27/12/2020
 */
public interface IServiceConsultar<E extends AbstractPersistable> {

    E get(Serializable id);

    E get(String atributo, Object object);

    E get(Specification<E> specification);

    List<E> listar();

    PaginacaoResultado<E> listar(Paginacao paginacao);

    <T> T getValorAtributo(String atributo, Serializable id);

    <T> T getValorAtributo(String atributo, Specification<E> specification);
}
