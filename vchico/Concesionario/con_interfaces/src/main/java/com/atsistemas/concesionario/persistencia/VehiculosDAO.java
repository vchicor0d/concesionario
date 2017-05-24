/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.persistencia;

import com.atsistemas.concesionario.entidades.Vehiculo;
import java.awt.Color;
import java.util.List;

/**
 *
 * @author vchico
 */
public interface VehiculosDAO {
    
    Vehiculo save(Vehiculo v);
    Vehiculo findOne(int id);
    void delete(Vehiculo v);
    List<Vehiculo> findByModelo(String modelo);
    List<Vehiculo> findByColor(Color color);
    List<Vehiculo> findByMotor(String motor);
    List<Vehiculo> findByPrecioLessThanEqual(Double precio);
    List<Vehiculo> findAll();
    
}
