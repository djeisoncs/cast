package br.com.cast.avaliacao.controller;

import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.service.CursoService;
import br.com.cast.avaliacao.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Path;
import java.util.UUID;

/**
 * Created by djeison.cassimiro on 03/01/2021
 */
@Path("/cast/curso")
public class CursoController extends ControllerImpl<Curso, UUID> {

    @Autowired
    private CursoService service;

    @Override
    protected ServiceImpl<Curso> getService() { return service; }
}
