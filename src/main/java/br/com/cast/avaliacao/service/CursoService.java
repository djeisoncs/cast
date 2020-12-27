package br.com.cast.avaliacao.service;

import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.repository.CursoRepository;
import br.com.cast.avaliacao.repository.RepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by djeison.cassimiro on 27/12/2020
 */
@Service
public class CursoService extends ServiceImpl<Curso> {

    @Autowired
    private CursoRepository repository;

    @Override
    protected RepositoryImpl<Curso> getRepository() { return repository; }
}
