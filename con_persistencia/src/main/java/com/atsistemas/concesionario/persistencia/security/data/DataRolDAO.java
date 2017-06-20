/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.persistencia.security.data;

import com.atsistemas.concesionario.entidades.security.Rol;
import com.atsistemas.concesionario.persistencia.security.RolDAO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vchico
 */
public interface DataRolDAO extends RolDAO, JpaRepository<Rol, String>{
    
}
