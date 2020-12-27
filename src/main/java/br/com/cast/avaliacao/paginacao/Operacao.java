package br.com.cast.avaliacao.paginacao;

import org.hibernate.criterion.MatchMode;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
public enum Operacao {

    IN {
        public Predicate restricao(CriteriaBuilder criteriaBuilder, Path path, Object[] valor) {
            return path.in(valor);
        }

        @Override
        public boolean necessitaDeValor() {
            return true;
        }

        @Override
        public boolean filtroHaving() {
            return false;
        }
    },

    NOT_IN {
        public Predicate restricao(CriteriaBuilder criteriaBuilder,Path path, Object[] valor) {
            return criteriaBuilder.not(path.in(valor));
        }

        @Override
        public boolean necessitaDeValor() {
            return true;
        }

        @Override
        public boolean filtroHaving() {
            return false;
        }
    },

    EQ_SUM {
        @Override
        public Predicate restricao(CriteriaBuilder criteriaBuilder,Path path, Object[] valor) {
            return criteriaBuilder.equal(criteriaBuilder.sum(path), valor[0]);
        }

        @Override
        public boolean necessitaDeValor(){
            return true;
        }

        @Override
        public boolean filtroHaving() {
            return true;
        }
    },

    EQ {
        @Override
        public Predicate restricao(CriteriaBuilder criteriaBuilder,Path path, Object[] valor) {
            return criteriaBuilder.equal(path, valor[0]);
        }

        @Override
        public boolean necessitaDeValor(){
            return true;
        }

        @Override
        public boolean filtroHaving() {
            return false;
        }
    },

    NOT_EQ {
        @Override
        public Predicate restricao(CriteriaBuilder criteriaBuilder,Path path, Object[] valor) {
            return criteriaBuilder.notEqual(path, valor[0]);
        }

        @Override
        public boolean necessitaDeValor(){
            return true;
        }

        @Override
        public boolean filtroHaving() {
            return false;
        }
    },

    GE {
        @Override
        public Predicate restricao(CriteriaBuilder criteriaBuilder,Path path, Object[] valor) {
            if(valor[0] instanceof Date){
                return criteriaBuilder.greaterThanOrEqualTo(path, (Date)valor[0]);

            } else if(valor[0] instanceof Integer) {
                return criteriaBuilder.greaterThanOrEqualTo(path, (Integer)valor[0]);

            } else if(valor[0] instanceof Long) {
                return criteriaBuilder.greaterThanOrEqualTo(path, (Long)valor[0]);

            } else if(valor[0] instanceof Float) {
                return criteriaBuilder.greaterThanOrEqualTo(path, (Float)valor[0]);

            } else if(valor[0] instanceof Double) {
                return criteriaBuilder.greaterThanOrEqualTo(path, (Double)valor[0]);

            } else {
                return criteriaBuilder.greaterThanOrEqualTo(path, (BigDecimal)valor[0]);

            }
        }

        @Override
        public boolean necessitaDeValor(){
            return true;
        }

        @Override
        public boolean filtroHaving() {
            return false;
        }
    },

    LE {
        @Override
        public Predicate restricao(CriteriaBuilder criteriaBuilder,Path path, Object[] valor) {
            if(valor[0] instanceof Date){
                return criteriaBuilder.lessThanOrEqualTo(path, (Date)valor[0]);

            } else if(valor[0] instanceof Integer) {
                return criteriaBuilder.lessThanOrEqualTo(path, (Integer)valor[0]);

            } else if(valor[0] instanceof Long) {
                return criteriaBuilder.lessThanOrEqualTo(path, (Long)valor[0]);

            } else if(valor[0] instanceof Float) {
                return criteriaBuilder.lessThanOrEqualTo(path, (Float)valor[0]);

            } else if(valor[0] instanceof Double) {
                return criteriaBuilder.lessThanOrEqualTo(path, (Double)valor[0]);

            } else {
                return criteriaBuilder.lessThanOrEqualTo(path, (BigDecimal)valor[0]);

            }
        }

        @Override
        public boolean necessitaDeValor(){
            return true;
        }

        @Override
        public boolean filtroHaving() {
            return false;
        }
    },

    GT {
        @Override
        public Predicate restricao(CriteriaBuilder criteriaBuilder,Path path, Object[] valor) {
            if(valor[0] instanceof Date){
                return criteriaBuilder.greaterThan(path, (Date)valor[0]);

            } else if(valor[0] instanceof Integer) {
                return criteriaBuilder.greaterThan(path, (Integer)valor[0]);

            } else if(valor[0] instanceof Long) {
                return criteriaBuilder.greaterThan(path, (Long)valor[0]);

            } else if(valor[0] instanceof Float) {
                return criteriaBuilder.greaterThan(path, (Float)valor[0]);

            } else if(valor[0] instanceof Double) {
                return criteriaBuilder.greaterThan(path, (Double)valor[0]);

            } else {
                return criteriaBuilder.greaterThan(path, (BigDecimal)valor[0]);

            }
        }

        @Override
        public boolean necessitaDeValor(){
            return true;
        }

        @Override
        public boolean filtroHaving() {
            return false;
        }
    },

    LT {
        @Override
        public Predicate restricao(CriteriaBuilder criteriaBuilder,Path path, Object[] valor) {
            if(valor[0] instanceof Date){
                return criteriaBuilder.lessThan(path, (Date)valor[0]);

            } else if(valor[0] instanceof Integer) {
                return criteriaBuilder.lessThan(path, (Integer)valor[0]);

            } else if(valor[0] instanceof Long) {
                return criteriaBuilder.lessThan(path, (Long)valor[0]);

            } else if(valor[0] instanceof Float) {
                return criteriaBuilder.lessThan(path, (Float)valor[0]);

            } else if(valor[0] instanceof Double) {
                return criteriaBuilder.lessThan(path, (Double)valor[0]);

            } else {
                return criteriaBuilder.lessThan(path, (BigDecimal)valor[0]);

            }
        }

        @Override
        public boolean necessitaDeValor(){
            return true;
        }

        @Override
        public boolean filtroHaving() {
            return false;
        }
    },

    LIKE {
        @Override
        public Predicate restricao(CriteriaBuilder criteriaBuilder,Path path, Object[] valor) {
            return criteriaBuilder.like(path, "%" + valor[0].toString() + "%");
        }

        @Override
        public boolean necessitaDeValor(){
            return true;
        }

        @Override
        public boolean filtroHaving() {
            return false;
        }
    },

    LIKE_START {
        @Override
        public Predicate restricao(CriteriaBuilder criteriaBuilder,Path path, Object[] valor) {
            return criteriaBuilder.like(path, MatchMode.START.toMatchString(valor[0].toString()));
        }

        @Override
        public boolean necessitaDeValor(){
            return true;
        }

        @Override
        public boolean filtroHaving() {
            return false;
        }
    },

    ILIKE {
        @Override
        public Predicate restricao(CriteriaBuilder criteriaBuilder,Path path, Object[] valor) {
            return criteriaBuilder.like(criteriaBuilder.lower(path), valor[0].toString().toLowerCase());
        }

        @Override
        public boolean necessitaDeValor(){
            return true;
        }

        @Override
        public boolean filtroHaving() {
            return false;
        }
    },

    ILIKE_ANYWHERE {
        @Override
        public Predicate restricao(CriteriaBuilder criteriaBuilder,Path path, Object[] valor) {
            return criteriaBuilder.like(criteriaBuilder.lower(path), MatchMode.ANYWHERE.toMatchString(valor[0].toString().toLowerCase()));
        }

        @Override
        public boolean necessitaDeValor(){
            return true;
        }

        @Override
        public boolean filtroHaving() {
            return false;
        }
    },

    ILIKE_START {
        @Override
        public Predicate restricao(CriteriaBuilder criteriaBuilder,Path path, Object[] valor) {
            return criteriaBuilder.like(criteriaBuilder.lower(path), MatchMode.START.toMatchString(valor[0].toString().toLowerCase()));
        }

        @Override
        public boolean necessitaDeValor(){
            return true;
        }

        @Override
        public boolean filtroHaving() {
            return false;
        }
    },

    IS_EMPTY {
        @Override
        public Predicate restricao(CriteriaBuilder criteriaBuilder,Path path, Object[] valor) {
            return criteriaBuilder.isEmpty(path);
        }

        @Override
        public boolean necessitaDeValor(){
            return false;
        }

        @Override
        public boolean filtroHaving() {
            return false;
        }
    },

    IS_NOT_EMPTY {
        @Override
        public Predicate restricao(CriteriaBuilder criteriaBuilder,Path path, Object[] valor) {
            return criteriaBuilder.isNotEmpty(path);
        }

        @Override
        public boolean necessitaDeValor(){
            return false;
        }

        @Override
        public boolean filtroHaving() {
            return false;
        }
    },

    IS_NULL {
        @Override
        public Predicate restricao(CriteriaBuilder criteriaBuilder,Path path, Object[] valor) {
            return criteriaBuilder.isNull(path);
        }

        @Override
        public boolean necessitaDeValor(){
            return false;
        }

        @Override
        public boolean filtroHaving() {
            return false;
        }
    },

    IS_NOT_NULL {
        @Override
        public Predicate restricao(CriteriaBuilder criteriaBuilder,Path path, Object[] valor) {
            return criteriaBuilder.isNotNull(path);
        }

        @Override
        public boolean necessitaDeValor(){
            return false;
        }

        @Override
        public boolean filtroHaving() {
            return false;
        }
    },

    BETWEEN {
        @Override
        public Predicate restricao(CriteriaBuilder criteriaBuilder,Path path, Object[] valor) {
            if(valor[0] instanceof Date){
                return criteriaBuilder.between(path, (Date)valor[0], (Date) valor[1]);

            } else if(valor[0] instanceof Integer) {
                return criteriaBuilder.between(path, (Integer)valor[0], (Integer)valor[0]);

            } else if(valor[0] instanceof Long) {
                return criteriaBuilder.between(path, (Long)valor[0], (Long)valor[0]);

            } else if(valor[0] instanceof Float) {
                return criteriaBuilder.between(path, (Float)valor[0], (Float)valor[0]);

            } else if(valor[0] instanceof Double) {
                return criteriaBuilder.between(path, (Double)valor[0], (Double)valor[0]);

            } else {
                return criteriaBuilder.between(path, (BigDecimal)valor[0], (BigDecimal)valor[0]);

            }
        }

        @Override
        public boolean necessitaDeValor(){
            return true;
        }

        @Override
        public boolean filtroHaving() {
            return false;
        }
    };

    public abstract Predicate restricao(CriteriaBuilder criteriaBuilder, Path path, Object[] valor);

    public abstract boolean necessitaDeValor();

    public abstract boolean filtroHaving();
}
