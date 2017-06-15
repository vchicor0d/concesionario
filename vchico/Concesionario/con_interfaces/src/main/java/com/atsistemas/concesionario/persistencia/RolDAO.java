/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.persistencia;

import com.atsistemas.concesionario.entidades.Rol;
import java.util.List;

/**
 *
 * @author vchico
 */
public interface RolDAO {
    
    Rol save(String rol);
    List<Rol> findAll();
    void delete(String rol);
    
}
