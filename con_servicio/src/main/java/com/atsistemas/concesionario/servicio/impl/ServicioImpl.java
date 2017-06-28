/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.servicio.impl;

import com.atsistemas.concesionario.entidades.Cliente;
import com.atsistemas.concesionario.entidades.Comercial;
import com.atsistemas.concesionario.entidades.EstadoFactura;
import com.atsistemas.concesionario.entidades.EstadoPedido;
import com.atsistemas.concesionario.entidades.Factura;
import com.atsistemas.concesionario.entidades.Pedido;
import com.atsistemas.concesionario.entidades.Vehiculo;
import com.atsistemas.concesionario.persistencia.ClienteDAO;
import com.atsistemas.concesionario.persistencia.ComercialDAO;
import com.atsistemas.concesionario.persistencia.FacturaDAO;
import com.atsistemas.concesionario.persistencia.PedidoDAO;
import com.atsistemas.concesionario.persistencia.VehiculosDAO;
import com.atsistemas.concesionario.servicio.Servicio;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vchico
 */
@Service
public class ServicioImpl implements Servicio {

    private ClienteDAO clidao;
    private ComercialDAO comdao;
    private VehiculosDAO vehidao;
    private FacturaDAO factdao;
    private PedidoDAO pedidao;

    @Autowired
    public ServicioImpl(ClienteDAO clidao, ComercialDAO comdao, VehiculosDAO vehidao, FacturaDAO factdao, PedidoDAO pedidao) {
        this.clidao = clidao;
        this.comdao = comdao;
        this.vehidao = vehidao;
        this.factdao = factdao;
        this.pedidao = pedidao;
    }

    @Override
    public Cliente altaCliente(Cliente c) {
        return clidao.save(c);
    }

    @Override
    public void bajaCliente(Cliente c) {
        clidao.delete(c);
    }

    @Override
    public Cliente buscaCliente(int id) {
        return clidao.findOne(id);
    }

    @Override
    public List<Cliente> buscaClientes() {
        return clidao.findAll();
    }

    @Override
    public Cliente actualizaCliente(Cliente c) {
        return clidao.save(c);
    }

    @Override
    public Comercial altaComercial(Comercial c) {
        return comdao.save(c);
    }

    @Override
    public void bajaComercial(Comercial c) {
        comdao.delete(c);
    }

    @Override
    public Comercial buscaComercial(int id) {
        return comdao.findOne(id);
    }

    @Override
    public List<Comercial> buscaComerciales() {
        return comdao.findAll();
    }

    @Override
    public Comercial actualizaComercial(Comercial c) {
        return comdao.save(c);
    }

    @Override
    public Vehiculo altaVehiculo(Vehiculo v) {
        return vehidao.save(v);
    }

    @Override
    public void bajaVehiculo(Vehiculo v) {
        vehidao.delete(v);
    }

    @Override
    public Vehiculo buscaVehiculo(int id) {
        return vehidao.findOne(id);
    }

    @Override
    public List<Vehiculo> buscaVehiculos() {
        return vehidao.findAll();
    }

    @Override
    public Vehiculo actualizaVehiculo(Vehiculo v) {
        return vehidao.save(v);
    }

    @Override
    public Pedido altaPedido(Pedido p) {
        return pedidao.save(p);
    }

    @Override
    public EstadoPedido estadoPedido(int id) {
        return pedidao.findEstadoById(id);
    }

    @Override
    public Pedido buscaPedido(int id) {
        return pedidao.findOne(id);
    }

    @Override
    public Pedido recepcionPedido(Pedido p) {
        p.setEstado(EstadoPedido.ENPROCESO);
        return pedidao.save(p);
    }

    @Override
    public Factura generarFactura(Pedido p) {
        double total = 0.0;
        p = pedidao.findOne(p.getId());
        if (p.getVehiculos() != null && !p.getVehiculos().isEmpty()) {
            for (Vehiculo v : p.getVehiculos()) {
                total += v.getPrecio();
            }
        }
        Factura f = new Factura(0, new Date(System.currentTimeMillis()), total, p, EstadoFactura.NOCOBRADA);
        p.setFactura(f);
        pedidao.save(p);
        return factdao.save(f);
    }

    @Override
    public Factura cobroFactura(Factura f) {
        f.setEstado(EstadoFactura.COBRADA);
        return factdao.save(f);
    }

    @Override
    public Pedido entregarPedido(Pedido p) {
        p.setEstado(EstadoPedido.ENTREGADO);
        return pedidao.save(p);
    }

    @Override
    public Factura buscaFactura(int id) {
        return factdao.findOne(id);
    }

    @Override
    public List<Pedido> buscaPedidos() {
        return pedidao.findAll();
    }

    @Override
    public List<Factura> buscaFacturas() {
        return factdao.findAll();
    }

}
