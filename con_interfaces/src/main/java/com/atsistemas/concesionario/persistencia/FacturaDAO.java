/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.persistencia;

import com.atsistemas.concesionario.entidades.Factura;
import java.util.List;

/**
 *
 * @author vchico
 */
public interface FacturaDAO {
    
    Factura save(Factura f);
    Factura findOne(int id);
    List<Factura> findAll();
    
}
