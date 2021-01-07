package br.com.cast.avaliacao.controller;

import br.com.cast.avaliacao.i18n.MensagemI18N;
import br.com.cast.avaliacao.model.Entidade;
import br.com.cast.avaliacao.service.ServiceImpl;
import br.com.cast.avaliacao.util.NegocioException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.*;

/**
 * Created by djeison.cassimiro on 28/12/2020
 */
public abstract class ControllerImpl<T extends Entidade, ID extends Serializable> extends ControllerConsultarImpl<T, ID> {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response salvar(T entidade) { return onExecute(entidade, () -> getService().salvar(entidade)); }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editar(T entidade) { return onExecute(entidade, () -> getService().editar(entidade)); }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluir(@PathParam("id") UUID id) { return onExecute(null, () -> getService().excluir(id)); }

    protected Map<String, Object> getResposta(T entidade, Collection<String> mensagens) {
        Map<String, Object> map = new HashMap<>();

        map.put("entidade", entidade);
        map.put("mensagens", mensagens);

        return map;
    }

    protected Map<String, Object> getResposta(T entidade, String mensagem, String... parametros){
        return getResposta(entidade, Objects.isNull(mensagem) ? Collections.emptyList() : Collections.singletonList(MensagemI18N.getKey(mensagem, parametros)));
    }

    public Response onExecute(T entidade, OnExecuteServiceCallBack callBack) {
        Response response;

        try {
            callBack.executar();
            response = callBack.getResponse();

            if (Objects.isNull(response)) {
                response = Response.ok(getResposta(entidade, callBack.getMensagens())).build();
            }
        } catch (NegocioException e) {
            response = gerarResponseDeRegraNegocioException(e);
        }

        return response;
    }

    protected abstract ServiceImpl<T> getService();

    protected interface OnExecuteServiceCallBack {
        void executar() throws NegocioException;

        default Response getResponse() { return null; }

        default String getMensagens() { return "operacao.realizada.sucesso"; }
    }
}
