package br.com.cast.avaliacao.util;

import org.apache.commons.collections4.IterableUtils;

import java.util.Objects;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
public class ColecaoUtil {

    public static boolean isNotEmptyNull(Iterable lista) {

        return Objects.nonNull(lista) && IterableUtils.size(lista) > 0;
    }
}
