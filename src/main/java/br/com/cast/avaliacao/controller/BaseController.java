package br.com.cast.avaliacao.controller;

import br.com.cast.avaliacao.util.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.BufferedOutputStream;

/**
 * Created by djeison.cassimiro on 28/12/2020
 */
public class BaseController {
    @Autowired
    private HttpServletRequest request;

    public Response gerarResponseDeRegraNegocioException(NegocioException e){
        return gerarResponseDeRegraNegocioException(Response.Status.BAD_REQUEST, e);
    }

    public Response gerarResponseDeRegraNegocioException(Response.Status status, NegocioException e){
        return Response.status(status).entity(e).type(MediaType.APPLICATION_JSON).build();
    }

    public HttpServletRequest getRequest() {
        return request;
    }


    public Response downloadArquivo(String nome, byte[] bytes){
        return downloadArquivo(nome, MediaType.APPLICATION_OCTET_STREAM, bytes);
    }

    public Response downloadArquivo(String nome, String type, byte[] bytes){
        StreamingOutput streamingOutput = output -> {
            try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(output)) {
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.flush();

            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        return downloadArquivo(nome, type, streamingOutput);
    }

    public Response downloadArquivo(String nome, String type, StreamingOutput streamingOutput){
        return Response.ok(streamingOutput, type).header("Content-Disposition", "attachment;filename=\""+nome+"\"").build();
    }
}
