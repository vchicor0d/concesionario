/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.servicio.security.impl;

import com.atsistemas.concesionario.entidades.security.Acceso;
import com.atsistemas.concesionario.persistencia.security.AccesoDAO;
import com.atsistemas.concesionario.servicio.security.ServicioSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author vchico
 */
@Service
public class ServicioSecurityImpl implements ServicioSecurity{

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

    @Override
    public Acceso loadUserByUsernameAndPassword(String username, String password) {
        Acceso acceso = adao.findByUsuarioAndPassword(username, password);
        return acceso;
    }
    
}
