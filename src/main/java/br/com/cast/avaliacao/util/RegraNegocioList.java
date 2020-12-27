package br.com.cast.avaliacao.util;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
public class RegraNegocioList implements Iterable<RegraNegocio> {

    private List<RegraNegocio> regraNegocios;

    public static RegraNegocioList build(){
        return new RegraNegocioList();
    }

    private RegraNegocioList(){
        regraNegocios = new ArrayList<>();
    }

    public RegraNegocioList add(String mensagem){
        return add(RegraNegocio.build(mensagem));
    }

    public RegraNegocioList add(String messageKey, Specification<?> specification){
        return add(RegraNegocio.build(messageKey, specification));
    }

    public RegraNegocioList add(RegraNegocio regraNegocio){
        regraNegocios.add(regraNegocio);
        return this;
    }

    @Override
    public Iterator<RegraNegocio> iterator() {
        return new Iterator<RegraNegocio>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < regraNegocios.size();
            }

            @Override
            public RegraNegocio next() {
                return regraNegocios.get(index++);
            }
        };
    }
}
