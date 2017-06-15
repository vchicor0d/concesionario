/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.persistencia;

import com.atsistemas.concesionario.entidades.Acceso;
import com.atsistemas.concesionario.entidades.Rol;
import java.util.List;

/**
 *
 * @author vchico
 */
public interface AccesoDAO {
    
    Acceso save(Acceso a);
    Acceso findOne(String usuario);
    Acceso findByUsuarioAndPassword(String usuario, String password);
    void delete(String usuario);
    List<Acceso> findAll();
    List<Rol> findRolesByUsuario(String usuario);
    
}
