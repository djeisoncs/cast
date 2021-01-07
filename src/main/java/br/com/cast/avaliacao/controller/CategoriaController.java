package br.com.cast.avaliacao.controller;

import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.service.CategoriaService;
import br.com.cast.avaliacao.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Path;
import java.util.UUID;

/**
 * Created by djeison.cassimiro on 03/01/2021
 */
@Path("/cast/categoria")
public class CategoriaController extends ControllerImpl<Categoria, UUID> {

    @Autowired
    private CategoriaService service;

    @Override
    protected ServiceImpl<Categoria> getService() { return service; }
}
