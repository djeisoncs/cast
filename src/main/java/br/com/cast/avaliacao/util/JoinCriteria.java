package br.com.cast.avaliacao.util;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.JoinType;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
@Getter
@Setter
public class JoinCriteria {

    private String associationPath;

    private String alias;

    private JoinType joinTipo;

    public JoinCriteria(){}

    public JoinCriteria(String associationPath, String alias, JoinType joinTipo){
        this();
        this.associationPath = associationPath;
        this.alias = alias;
        this.joinTipo = joinTipo;
    }

    public JoinType getJoinTipo(){
        if(!possuiJoinTipo()){
            definirJoinTipoInnerJoin();
        }

        return joinTipo;
    }

    public boolean possuiJoinTipo(){
        return joinTipo != null;
    }

    public void definirJoinTipoInnerJoin(){
        joinTipo = JoinType.INNER;
    }

    @Override
    public int hashCode() {
        int hashCode;

        try{
            hashCode = (associationPath+"."+alias).hashCode();

        }catch (Exception e){
            hashCode = super.hashCode();

        }

        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals;

        try{
            JoinCriteria joinCriteria = (JoinCriteria) obj;
            equals = joinCriteria.getAssociationPath().equals(associationPath) && joinCriteria.getAlias().equals(alias);

        }catch (Exception e){
            equals = super.equals(obj);
        }

        return equals;
    }

    @Override
    public String toString() {
        return getJoinTipo() + " " + associationPath + " as " + alias;
    }
}
