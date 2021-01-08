package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.model.Entidade;
import br.com.cast.avaliacao.service.interfaces.IServiceConsultar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by djeison.cassimiro on 27/12/2020
 */
public abstract class ServiceConsultarImpl<E extends Entidade> implements IServiceConsultar<E> {

    @Override
    public E get(UUID id) { return getRepository().findById(id).orElse(null); }

    @Override
    public List<E> listar() { return getRepository().findAll(); }


    protected abstract JpaRepository<E, UUID> getRepository();
}
