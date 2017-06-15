/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.servicio.impl;

import com.atsistemas.concesionario.entidades.Acceso;
import com.atsistemas.concesionario.persistencia.AccesoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author vchico
 */
@Service
public class ServicioSecurity implements UserDetailsService{

    @Autowired
    private AccesoDAO adao;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Acceso acceso = adao.findOne(username);
        if (acceso == null){
            throw new UsernameNotFoundException("Usuario "+username+" no encontrado");
        } else {
            return acceso;
        }
    }
    
}
