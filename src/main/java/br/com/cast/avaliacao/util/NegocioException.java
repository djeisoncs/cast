package br.com.cast.avaliacao.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
@JsonIgnoreProperties({"message", "suppressed", "localizedMessage", "cause", "stackTrace"})
@Getter
public class NegocioException extends Exception {

    private List<String> mensagens;

    public static NegocioException build() {return new NegocioException();}

    public static NegocioException build(String mensagen) {return build().addMensagem(mensagen);}

    public static NegocioException build(Collection<String> mensagens) {return build().addMensagens(mensagens);}

    public NegocioException() {
        super("Negocio Exception");
        mensagens = new ArrayList<>();
    }

    public NegocioException(String mensagem) {
        this();
        addMensagem(mensagem);
    }

    public NegocioException addMensagem(String mensagem) {
        mensagens.add(mensagem);
        return this;
    }

    public NegocioException addMensagens(Collection<String> mensagens) {
        mensagens.addAll(mensagens);
        return this;
    }

    public String getMensagensSeparadosPorLinhas(){
        return mensagens.stream().reduce("", (valorReduzido,valor) -> valorReduzido+valor+"\n");
    }

    public void lancar() throws NegocioException {
        if (!mensagens.isEmpty()) {
            throw this;
        }
    }
}
