package br.com.cast.avaliacao.paginacao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.criterion.Order;

import java.io.Serializable;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
@Getter
@Setter
public class Ordenacao implements Serializable {

    private String campo;

    private TipoOrdenacao tipoOrdenacao;

    public Order toOrder(){
        Order order;

        switch (tipoOrdenacao){
            case DESC:
                order = Order.desc(campo);
                break;
            default:
                order = Order.asc(campo);
                break;
        }

        return order;
    }

    public static Ordenacao build(String campo, TipoOrdenacao tipoOrdenacao){
        Ordenacao ordenacao = new Ordenacao();
        ordenacao.setCampo(campo);
        ordenacao.setTipoOrdenacao(tipoOrdenacao);

        return ordenacao;
    }
}
