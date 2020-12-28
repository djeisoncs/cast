package br.com.cast.avaliacao.repository.interfaces;

import br.com.cast.avaliacao.model.Entidade;
import br.com.cast.avaliacao.util.NegocioException;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
public interface IRepository<T extends Entidade> extends IRepositoryConsultar<T> {

    void salvar(T entidade) throws NegocioException;

    void alterar(T entidade) throws NegocioException;

    void excluir(UUID id) throws NegocioException;

    void excluirDefinitivamente(Serializable id);

    void alterarAtributos(T entidade, String... atributos);

}
