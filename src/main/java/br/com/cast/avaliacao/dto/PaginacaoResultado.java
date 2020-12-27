package br.com.cast.avaliacao.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
public class PaginacaoResultado<T> {

    private List<T> entidades;
    private long quantidade;

    private Map<String, List<MenuAcao>> acoesTabelaListagem = new HashMap<>();

    public PaginacaoResultado(List<T> entidades, long quantidade){
        this.entidades = entidades;
        this.quantidade = quantidade;
    }

    public static <T> PaginacaoResultado<T> paginacaoResultadoEmpty(){
        return new PaginacaoResultado<>(new ArrayList<>(), 0);
    }

    public void addAcoesTabelaListagem(String identificador, List<MenuAcao> acoes){
        acoesTabelaListagem.put(identificador, acoes);
    }
}
