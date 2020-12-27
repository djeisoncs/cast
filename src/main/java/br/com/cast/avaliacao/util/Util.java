package br.com.cast.avaliacao.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
public class Util {

    public static Type[] getTypes(Object object) {
        ParameterizedType parameterizedType;

        try {
            parameterizedType = (ParameterizedType) object.getClass().getGenericSuperclass();

        } catch (ClassCastException e) {
            try {
                parameterizedType = (ParameterizedType) Class.forName(object.getClass().getGenericSuperclass().getTypeName()).getGenericSuperclass();

            } catch (ClassNotFoundException classNotFountexception) {
                throw new RuntimeException(classNotFountexception);
            }

        }

        return parameterizedType.getActualTypeArguments();
    }

    public static String encriptSHA256(String valor) {
        return encript(valor, "SHA-256");
    }

    public static String encript(String valor, String algorithm) {
        return Hex.encodeHexString(DigestUtils.getDigest(algorithm).digest(valor.getBytes()));
    }

    public static <T> T getValorFromField(Object entidade, Field field) {
        T valor = null;

        try {
            valor = (T) FieldUtils.readField(field, entidade, true);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return valor;
    }
}
