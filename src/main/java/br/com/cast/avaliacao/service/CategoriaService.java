package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.repository.CategoriaRepository;
import br.com.cast.avaliacao.repository.RepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by djeison.cassimiro on 27/12/2020
 */
@Service
public class CategoriaService extends ServiceImpl<Categoria> {

    @Autowired
    private CategoriaRepository repository;

    @Override
    protected RepositoryImpl<Categoria> getRepository() { return repository; }
}
