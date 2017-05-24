/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.persistencia;

import com.atsistemas.concesionario.entidades.Cliente;
import java.util.List;

/**
 *
 * @author vchico
 */
public interface ClienteDAO {
    
    Cliente save(Cliente c);
    void delete(Cliente c);
    Cliente findOne(int id);
    Cliente findByCorreo(String correo);
    Cliente findByTelefono(String telefono);
    List<Cliente> findByNombre(String nombre);
    List<Cliente> findAll();
    
}
