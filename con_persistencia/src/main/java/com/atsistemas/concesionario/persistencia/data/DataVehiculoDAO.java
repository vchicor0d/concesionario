/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.persistencia.data;

import com.atsistemas.concesionario.entidades.Vehiculo;
import com.atsistemas.concesionario.persistencia.VehiculosDAO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vchico
 */
public interface DataVehiculoDAO extends VehiculosDAO, JpaRepository<Vehiculo, Integer> {
    
    //No hace falta indicar aquí los métodos ya que están puestos con la nomenclatura adecuada en la interfaz principal (VehiculosDAO)
    
}
