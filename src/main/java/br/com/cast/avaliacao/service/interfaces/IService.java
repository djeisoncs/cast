package br.com.cast.avaliacao.service.interfaces;

import br.com.cast.avaliacao.model.Entidade;
import br.com.cast.avaliacao.util.NegocioException;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by djeison.cassimiro on 27/12/2020
 */
public interface IService<E extends Entidade> extends IServiceConsultar<E> {

    void salvar(E entidade) throws NegocioException;

    void editar(E entidade) throws NegocioException;

    void excluir(UUID id) throws NegocioException;

    void excluirDefinitivamente(Serializable id) throws NegocioException;

    void alterarAtributos(E entidade, String... atributos);
}
