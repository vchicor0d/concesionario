/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.persistencia;

import com.atsistemas.concesionario.entidades.EstadoFactura;
import com.atsistemas.concesionario.entidades.Pedido;
import java.util.List;

/**
 *
 * @author vchico
 */
public interface PedidoDAO {
    
    Pedido save(Pedido p);
    Pedido findOne(int id);
    EstadoFactura findEstadoById(int id);
    List<Pedido> findAll();
    
}
