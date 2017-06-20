/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.servicio.security;

import com.atsistemas.concesionario.entidades.security.Acceso;

/**
 *
 * @author vchico
 */
public interface ServicioSecurity {
    
    Acceso loadUserByUsernameAndPassword(String username, String password);
    
}
