/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.persistencia.data;

import com.atsistemas.concesionario.entidades.Factura;
import com.atsistemas.concesionario.persistencia.FacturaDAO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vchico
 */
public interface DataFacturaDAO extends FacturaDAO, JpaRepository<Factura, Integer>{
    
}
