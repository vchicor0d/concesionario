/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import com.atsistemas.concesionario.entidades.security.Acceso;
import com.atsistemas.concesionario.servicio.security.ServicioSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vchico
 */
@RestController
public class ServicioRestMainController {
    
    @Autowired
    ServicioSecurity servicio;
    
    @RequestMapping(path = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDetails verificarAcceso(@RequestBody Acceso login){
        UserDetails acceso;
        
        if (login.getUsuario() != null && login.getPassword() != null){
            
            acceso = servicio.loadUserByUsernameAndPassword(login.getUsuario(), login.getPassword());
            
        } else{
            acceso = null;
        }
        
        return acceso;
    }
    
}
