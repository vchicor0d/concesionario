/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import com.atsistemas.concesionario.entidades.Cliente;
import com.atsistemas.concesionario.servicio.Servicio;
import java.util.List;
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
@RequestMapping("/cliente")
public class ServicioRestClienteController {
    
    Servicio servicio;

    @Autowired
    public ServicioRestClienteController(Servicio servicio) {
        this.servicio = servicio;
    }
    
    @RequestMapping(path = "/alta", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Cliente> altaCliente(@RequestBody Cliente c){
        Cliente nuevo = servicio.altaCliente(c);
        HttpStatus estado = nuevo!=null?HttpStatus.OK:HttpStatus.NOT_MODIFIED;
        return new ResponseEntity<>(nuevo, estado);
    }
    
    @RequestMapping(path="/baja", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public HttpStatus bajaCliente(@RequestBody Cliente c){
        servicio.bajaCliente(c);
        return HttpStatus.ACCEPTED;
    }
    
    @RequestMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Cliente> buscarCliente(@PathVariable int id){
        Cliente c = servicio.buscaCliente(id);
        HttpStatus estado = c!=null?HttpStatus.FOUND:HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(c,estado);
    }
    
    @RequestMapping(path="/lista", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> listarClientes(){
        List<Cliente> clientes = servicio.buscaClientes();
        HttpStatus estado = clientes != null && clientes.size() > 0?HttpStatus.FOUND:HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(clientes,estado);
    }
    
    @RequestMapping(path = "/actualizar", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Cliente> actualizarCliente(@RequestBody Cliente c){
        Cliente act = servicio.actualizaCliente(c);
        HttpStatus estado = act!=null?HttpStatus.OK:HttpStatus.NOT_MODIFIED;
        return new ResponseEntity<>(act, estado);
    }
    
}
