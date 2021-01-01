package br.com.cast.avaliacao.controller.interfaces;

import br.com.cast.avaliacao.util.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

/**
 * Created by djeison.cassimiro on 28/12/2020
 */
public class IController {

    @Autowired
    private HttpServletResponse response;

    public Response getResponseExcepetion(NegocioException e) {

    }

    public Response getResponseExcepetion(Response.)
}
