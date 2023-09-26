/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author irati
 */
public class ResultadoCreacionEnunciado {
    private boolean enunciadoCorrecto;
    private boolean unidadesCorrecto;
    private Integer idEnunciado;
    
    public boolean isEnunciadoCorrecto() {
        return enunciadoCorrecto;
    }

    public void setEnunciadoCorrecto(boolean enunciadoCorrecto) {
        this.enunciadoCorrecto = enunciadoCorrecto;
    }

    public boolean isUnidadesCorrecto() {
        return unidadesCorrecto;
    }

    public void setUnidadesCorrecto(boolean unidadesCorrecto) {
        this.unidadesCorrecto = unidadesCorrecto;
    }

    public Integer getIdEnunciado() {
        return idEnunciado;
    }

    public void setIdEnunciado(Integer idEnunciado) {
        this.idEnunciado = idEnunciado;
    }
}
