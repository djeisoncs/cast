package br.com.cast.avaliacao.dto;

import lombok.Getter;

/**
 * Created by djeison.cassimiro on 26/12/2020
 */
@Getter
public class MenuAcao {

    private String label;
    private String icon;
    private String styleClassBotao;
    private String funcao;

    private Boolean submit;
    private Boolean exibirComoBotao;
    private Boolean desabilitado;

    private MenuAcao() {
        submit = false;
        exibirComoBotao = false;
        desabilitado = false;
    }

    public static MenuAcao build(){
        return new MenuAcao();
    }

    public MenuAcao setLabel(String label) {
        this.label = label;
        return this;
    }

    public MenuAcao setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public MenuAcao setStyleClassBotao(String styleClassBotao) {
        this.styleClassBotao = styleClassBotao;
        return this;
    }

    public MenuAcao setFuncao(String funcao) {
        this.funcao = funcao;
        return this;
    }

    public MenuAcao setExibirComoBotao(Boolean exibirComoBotao) {
        this.exibirComoBotao = exibirComoBotao;
        return this;
    }

    public MenuAcao setSubmit(Boolean submit) {
        this.submit = submit;
        return this;
    }

    public MenuAcao setDesabilitado(Boolean desabilitado) {
        this.desabilitado = desabilitado;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals;

        try{
            equals = label.equals(((MenuAcao) obj).getLabel());

        }catch (Exception e){
            equals = super.equals(obj);

        }

        return equals;
    }

    @Override
    public int hashCode() {
        int hashCode;

        try{
            hashCode = label.hashCode();

        }catch (Exception e){
            hashCode = super.hashCode();

        }

        return hashCode;
    }
}
