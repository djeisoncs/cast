package br.com.cast.avaliacao.service.interfaces;

import br.com.cast.avaliacao.util.NegocioException;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;

/**
 * Created by djeison.cassimiro on 27/12/2020
 */
public interface IService<E extends AbstractPersistable> extends IServiceConsultar<E> {

    void salvar(E entidade) throws NegocioException;

    void alterar(E entidade) throws NegocioException;

    void excluir(Serializable id) throws NegocioException;

    void alterarAtributos(E entidade, String... atributos);
}
