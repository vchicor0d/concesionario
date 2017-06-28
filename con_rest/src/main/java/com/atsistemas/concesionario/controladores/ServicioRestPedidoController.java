/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import com.atsistemas.concesionario.entidades.EstadoPedido;
import com.atsistemas.concesionario.entidades.Pedido;
import com.atsistemas.concesionario.servicio.Servicio;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vchico
 */
@RestController
@RequestMapping("/pedido")
public class ServicioRestPedidoController {
    
    Servicio servicio;

    @Autowired
    public ServicioRestPedidoController(Servicio servicio) {
        this.servicio = servicio;
    }
    
    @RequestMapping(path = "/alta", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Pedido> altaPedido(@RequestBody Pedido p){
        p.setFecha(new Date(System.currentTimeMillis()));
        p.setEstado(EstadoPedido.SINSTOCK);
        Pedido nuevo = servicio.altaPedido(p);
        HttpStatus estado = nuevo!=null?HttpStatus.OK:HttpStatus.NOT_MODIFIED;
        return new ResponseEntity<>(nuevo, estado);
    }
    
    @RequestMapping(path="/estado/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    public ResponseEntity<EstadoPedido> estado(@PathVariable int id){
        EstadoPedido estado = servicio.estadoPedido(id);
        return new ResponseEntity<>(estado, HttpStatus.OK);
    }
    
    @Transactional
    @RequestMapping(path="/recepcion/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Pedido> recibirPedido(@PathVariable int id){
        Pedido p = servicio.buscaPedido(id);
        p = servicio.recepcionPedido(p);
        HttpStatus estado = p!=null?HttpStatus.OK:HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(p,estado);
    }
    
    @RequestMapping(path = "/entrega/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Pedido> entregaPedido(@PathVariable int id){
        Pedido p = servicio.buscaPedido(id);
        p = servicio.entregarPedido(p);
        HttpStatus estado = p!=null?HttpStatus.OK:HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(p, estado);
    }
    
    @Transactional
    @RequestMapping(path="/lista", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<Pedido>> listarPedidos(){
        List<Pedido> lista = servicio.buscaPedidos();
        HttpStatus estado = lista != null?HttpStatus.FOUND:HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(lista, estado);
    }
    
    @RequestMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Pedido> buscarPedido(@PathVariable int id){
        Pedido p = servicio.buscaPedido(id);
        HttpStatus estado = p!=null?HttpStatus.FOUND:HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(p,estado);
    }
    
}
