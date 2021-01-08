package br.com.cast.avaliacao.service.interfaces;

import br.com.cast.avaliacao.model.Entidade;
import java.util.List;
import java.util.UUID;

/**
 * Created by djeison.cassimiro on 27/12/2020
 */
public interface IServiceConsultar<E extends Entidade> {

    E get(UUID id);

    List<E> listar();
}
