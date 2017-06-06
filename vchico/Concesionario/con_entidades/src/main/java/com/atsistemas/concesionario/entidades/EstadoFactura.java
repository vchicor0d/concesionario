/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.entidades;

/**
 *
 * @author vchico
 */
public enum EstadoFactura {
    COBRADA("Cobrada"),
    NOCOBRADA("No cobrada"),
    FALLIDO("Cobro fallido");
    
    private final String valor;
    
    EstadoFactura(String valor){
        this.valor=valor;
    }
    
    @Override
    public String toString(){
        return valor;
    }
}
