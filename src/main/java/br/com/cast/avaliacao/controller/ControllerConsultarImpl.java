package br.com.cast.avaliacao.controller;

import br.com.cast.avaliacao.dto.PaginacaoResultado;
import br.com.cast.avaliacao.i18n.MensagemI18N;
import br.com.cast.avaliacao.model.Entidade;
import br.com.cast.avaliacao.paginacao.Paginacao;
import br.com.cast.avaliacao.service.ServiceConsultarImpl;
import br.com.cast.avaliacao.util.NegocioException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by djeison.cassimiro on 02/01/2021
 */
public abstract class ControllerConsultarImpl<T extends Entidade, ID extends Serializable> extends BaseController {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<T> listar() { return getService().listar(); }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PaginacaoResultado<T> listar(Paginacao paginacao) { return getService().listar(paginacao); }

    @SuppressWarnings("RestParamTypeInspection")
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response dtoGet(@PathParam("id") ID id) {
        T entidade = getService().get(id);

        return createRespondeGetEntity(entidade);
    }

    public Response createRespondeGetEntity(T entidade) {
        Response response;

        if (Objects.isNull(entidade)) {
            NegocioException negocioException = new NegocioException(MensagemI18N.getKey("operacao.consultar.falha"));
            response = gerarResponseDeRegraNegocioException(Response.Status.NOT_FOUND, negocioException);

        } else {
            afterGet(entidade);
            response = Response.ok(entidade).build();
        }
        return response;
    }

    public void afterGet(T entidade) {}

    protected abstract ServiceConsultarImpl<T> getService();
}
