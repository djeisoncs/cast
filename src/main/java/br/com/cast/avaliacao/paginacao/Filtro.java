package br.com.cast.avaliacao.paginacao;

import br.com.cast.avaliacao.util.JoinCriteria;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Filtro {

    private String campo;

    private Operacao operacao;

    private String[] valor;

    private TipoFiltro tipo;

    private List<JoinCriteria> joins = new ArrayList<>();

    private List<Filtro> filtrosOr = new ArrayList<>();

    private List<Filtro> filtrosAnd = new ArrayList<>();

    public boolean hasJoins(){
        return !joins.isEmpty();
    }

    public boolean hasFiltrosOr(){
        return !filtrosOr.isEmpty();
    }

    public boolean hasFiltrosAnd(){
        return !filtrosAnd.isEmpty();
    }

    public boolean hasValor(){
        return !Objects.isNull(valor) && Arrays.stream(valor).anyMatch(valorTemp -> !StringUtils.isEmpty(valorTemp));
    }

    public Object[] getValorTransformado(){
        Object[] valor = new Object[getValor().length];

        if (operacao.necessitaDeValor()) {
            int indice = 0;

            for (final String string : getValor()) {
                valor[indice++] = getTipo().converter(string);
            }
        }

        return valor;
    }

    public static Filtro buildEQ(TipoFiltro tipo, String campo, String valor){
        return build(tipo, Operacao.EQ, campo, valor);
    }

    public static Filtro build(TipoFiltro tipo, Operacao operacao, String campo, String valor){
        Filtro filtro = new Filtro();
        filtro.setTipo(tipo);
        filtro.setOperacao(operacao);
        filtro.setCampo(campo);
        filtro.setValor(new String[]{valor});

        return filtro;
    }
}
