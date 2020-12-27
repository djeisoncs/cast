package br.com.cast.avaliacao.repository.interfaces;

import br.com.cast.avaliacao.util.NegocioException;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
public interface IRepository<T extends AbstractPersistable> extends IRepositoryConsultar<T> {

    void salvar(T entidade)throws NegocioException;

    void alterar(T entidade)throws NegocioException;

    void excluir(Serializable id)throws NegocioException;

    void alterarAtributos(T entidade, String... atributos);

}
