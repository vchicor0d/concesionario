/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.servicio.security;

import com.atsistemas.concesionario.entidades.security.Acceso;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author vchico
 */
public interface ServicioSecurity extends UserDetailsService {
    
    Acceso loadUserByUsernameAndPassword(String username, String password);
    
}
