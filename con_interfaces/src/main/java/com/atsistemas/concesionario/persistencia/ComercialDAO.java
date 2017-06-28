/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.persistencia;

import com.atsistemas.concesionario.entidades.security.Acceso;
import com.atsistemas.concesionario.entidades.Comercial;
import java.util.List;

/**
 *
 * @author vchico
 */
public interface ComercialDAO {
    
    Comercial save(Comercial c);
    void delete(Comercial c);
    Comercial findOne(int id);
    Comercial findByCorreo(String correo);
    Comercial findByTelefono(String telefono);
    List<Comercial> findByNombre(String nombre);
    List<Comercial> findAll();
    Acceso findAccesoById(int id);
    
}
