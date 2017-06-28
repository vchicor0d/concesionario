/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.persistencia.data;

import com.atsistemas.concesionario.entidades.Cliente;
import com.atsistemas.concesionario.persistencia.ClienteDAO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vchico
 */
public interface DataClienteDAO extends ClienteDAO, JpaRepository<Cliente, Integer>{
    
}
