package br.com.cast.avaliacao.paginacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
public enum TipoFiltro {

    INTEIRO {
        @Override
        public Integer converter(String valor) {
            return Integer.parseInt(valor);
        }
    },

    LONG {
        @Override
        public Long converter(String valor) {
            return Long.parseLong(valor);
        }
    },

    DECIMAL {
        @Override
        public Double converter(String valor) {
            return Double.parseDouble(valor);
        }
    },

    DATA {
        @Override
        public Date converter(String valor) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse(valor);

            } catch (ParseException e) {
                throw new RuntimeException(e.getMessage(), e);

            }
        }
    },

    DATA_HORA {
        @Override
        public Date converter(String valor) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(valor);

            } catch (ParseException e) {
                throw new RuntimeException(e.getMessage(), e);

            }
        }
    },

    STRING {
        @Override
        public String converter(String valor) {
            return valor;
        }

    },

    BOOLEAN {
        public Boolean converter(String valor) {
            return Boolean.valueOf(valor);
        }
    },

    ENUM {
        @Override
        public Object converter(String valor) {
            Enum<?> enumerate = null;

            if(!Objects.isNull(valor)){
                try {
                    int ultimoIndexPonto = valor.lastIndexOf(".");

                    String classEnum = valor.substring(0, ultimoIndexPonto);
                    valor = valor.substring(ultimoIndexPonto+1);

                    Class<? extends Enum> clazz = (Class<? extends Enum>) Class.forName(classEnum);
                    enumerate = Enum.valueOf(clazz, valor);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    enumerate = null;

                }
            }

            return enumerate;
        }
    },

    UUID {
        @Override
        public Object converter(String valor) {
            return java.util.UUID.fromString(valor);
        }
    };

    public abstract Object converter(String valor);
}
