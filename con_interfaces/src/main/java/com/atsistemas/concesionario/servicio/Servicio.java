/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.servicio;

import com.atsistemas.concesionario.entidades.Cliente;
import com.atsistemas.concesionario.entidades.Comercial;
import com.atsistemas.concesionario.entidades.EstadoPedido;
import com.atsistemas.concesionario.entidades.Factura;
import com.atsistemas.concesionario.entidades.Pedido;
import com.atsistemas.concesionario.entidades.Vehiculo;
import java.util.List;

/**
 *
 * @author vchico
 */
public interface Servicio {
    
    Cliente altaCliente(Cliente c);
    void bajaCliente(Cliente c);
    Cliente buscaCliente(int id);
    List<Cliente> buscaClientes();
    Cliente actualizaCliente(Cliente c);
    
    Comercial altaComercial(Comercial c);
    void bajaComercial(Comercial c);
    Comercial buscaComercial(int id);
    List<Comercial> buscaComerciales();
    Comercial actualizaComercial(Comercial c);
    
    Vehiculo altaVehiculo(Vehiculo v);
    void bajaVehiculo(Vehiculo v);
    Vehiculo buscaVehiculo(int id);
    List<Vehiculo> buscaVehiculos();
    Vehiculo actualizaVehiculo(Vehiculo v);
    
    Pedido altaPedido(Pedido p);
    EstadoPedido estadoPedido(int id);
    Pedido buscaPedido(int id);
    Pedido recepcionPedido(Pedido p);
    Factura generarFactura(Pedido p);
    List<Pedido> buscaPedidos();
    
    Factura cobroFactura(Factura f);
    Pedido entregarPedido(Pedido p);
    Factura buscaFactura(int id);
    List<Factura> buscaFacturas();
    
}
