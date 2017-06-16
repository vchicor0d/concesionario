/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.servicio;

import com.atsistemas.concesionario.entidades.Acceso;

/**
 *
 * @author vchico
 */
public interface ServicioSecurity {
    
    public Acceso loadUserByUsernameAndPassword(String username, String password);
    
}
