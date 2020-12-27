package br.com.cast.avaliacao.paginacao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Paginacao implements Serializable {

    private int pagina;

    private int quantidade;

    private Specification specification;

    private List<Filtro> filtros = new ArrayList<>();

    private List<Ordenacao> ordenacoes = new ArrayList<>();

    private Boolean listarTodos = false;

    private Boolean preencherAcoes = false;

    private String dtoClass;

    public Paginacao clonar(){
        Paginacao clone = new Paginacao();
        clone.setPagina(pagina);
        clone.setQuantidade(quantidade);
        clone.setSpecification(specification);
        clone.setListarTodos(listarTodos);
        clone.setPreencherAcoes(preencherAcoes);
        clone.getFiltros().addAll(filtros);
        clone.getOrdenacoes().addAll(ordenacoes);

        return clone;
    }

    public static Paginacao build(){
        return new Paginacao();
    }

    public Paginacao addFiltro(TipoFiltro tipoFiltro, Operacao operacao, String campo, String valor){
        return addFiltro(Filtro.build(tipoFiltro, operacao, campo, valor));
    }

    public Paginacao addFiltro(Filtro filtro){
        filtros.add(filtro);
        return this;
    }

    public Paginacao addOrdenacao(String campo, TipoOrdenacao tipoOrdenacao){
        return addOrdenacao(Ordenacao.build(campo, tipoOrdenacao));
    }

    public Paginacao addOrdenacao(Ordenacao ordenacao){
        ordenacoes.add(ordenacao);
        return this;
    }

    public boolean possuiOrdenacoes(){
        return ordenacoes != null && !ordenacoes.isEmpty();
    }

    public boolean possuiFiltros(){
        return filtros != null && !filtros.isEmpty();
    }

    public boolean possuiSpecification(){
        return !Objects.isNull(specification);
    }
}
