/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.servicio.security.impl;

import com.atsistemas.concesionario.entidades.security.Acceso;
import com.atsistemas.concesionario.persistencia.security.AccesoDAO;
import com.atsistemas.concesionario.servicio.security.ServicioSecurity;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private static final Logger LOG = Logger.getLogger(ServicioSecurityImpl.class.getName());
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Acceso acceso = adao.findOne(username);
        LOG.log(Level.INFO, "Buscando acceso por usuario");
        if (acceso == null){
            LOG.log(Level.WARNING, "Usuario {0} no encontrado", username);
            throw new UsernameNotFoundException("Usuario "+username+" no encontrado");
        } else {
            LOG.log(Level.INFO, "Usuario {0}, acceso permitido", username);
            return acceso;
        }
    }

    @Override
    public Acceso loadUserByUsernameAndPassword(String username, String password) {
        Acceso acceso = adao.findByUsuarioAndPassword(username, password);
        LOG.log(Level.INFO, "Buscando acceso por usuario y contraseña");
        if (acceso == null){
            LOG.log(Level.WARNING, "Usuario {0} no encontrado", username);
            throw new UsernameNotFoundException("Usuario "+username+" no encontrado");
        } else {
            LOG.log(Level.INFO, "Usuario {0}, acceso permitido", username);
            return acceso;
        }
    }
    
}
