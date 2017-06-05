/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import com.atsistemas.concesionario.entidades.Comercial;
import com.atsistemas.concesionario.servicio.Servicio;
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
@RequestMapping("/comercial")
public class ServicioRestComercialController {
    
    Servicio servicio;

    @Autowired
    public ServicioRestComercialController(Servicio servicio) {
        this.servicio = servicio;
    }
    
    @Transactional
    @RequestMapping(path = "/alta", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Comercial> altaComercial(@RequestBody Comercial c){
        Comercial nuevo = servicio.altaComercial(c);
        HttpStatus estado = nuevo!=null?HttpStatus.OK:HttpStatus.NOT_MODIFIED;
        return new ResponseEntity<>(nuevo, estado);
    }
    
    @Transactional
    @RequestMapping(path="/baja", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public HttpStatus bajaComercial(@RequestBody Comercial c){
        servicio.bajaComercial(c);
        return HttpStatus.ACCEPTED;
    }
    
    @Transactional
    @RequestMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Comercial> buscarComercial(@PathVariable int id){
        Comercial c = servicio.buscaComercial(id);
        HttpStatus estado = c!=null?HttpStatus.FOUND:HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(c,estado);
    }
    
    @Transactional
    @RequestMapping(path="/lista", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<Comercial>> listarComerciales(){
        List<Comercial> comercials = servicio.buscaComerciales();
        HttpStatus estado = comercials != null?HttpStatus.FOUND:HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(comercials,estado);
    }
    
    @Transactional
    @RequestMapping(path = "/actualizar", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Comercial> actualizarComercial(@RequestBody Comercial c){
        Comercial act = servicio.actualizaComercial(c);
        HttpStatus estado = act!=null?HttpStatus.OK:HttpStatus.NOT_MODIFIED;
        return new ResponseEntity<>(act, estado);
    }
    
}
