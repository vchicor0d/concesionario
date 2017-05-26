/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import com.atsistemas.concesionario.entidades.Factura;
import com.atsistemas.concesionario.entidades.Pedido;
import com.atsistemas.concesionario.servicio.Servicio;
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
@RequestMapping("/factura")
public class ServicioRestFacturaController {
    
    Servicio servicio;

    @Autowired
    public ServicioRestFacturaController(Servicio servicio) {
        this.servicio = servicio;
    }
    
    @Transactional
    @RequestMapping(path = "/cobro/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Factura> altaFactura(@PathVariable int id){
        Factura f = servicio.buscaFactura(id);
        f = servicio.cobroFactura(f);
        HttpStatus estado = f!=null?HttpStatus.OK:HttpStatus.NOT_MODIFIED;
        return new ResponseEntity<>(f, estado);
    }
    
    @Transactional
    @RequestMapping(path="/generarFactura", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Factura> generarFactura(@RequestBody Pedido p){
        Factura f = servicio.generarFactura(p);
        HttpStatus estado = f != null?HttpStatus.OK:HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(f,estado);
    }
    
}
