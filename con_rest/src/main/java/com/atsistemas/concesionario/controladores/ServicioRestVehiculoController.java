/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import com.atsistemas.concesionario.entidades.Vehiculo;
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
@RequestMapping(path="/vehiculo")
public class ServicioRestVehiculoController {
    
    Servicio servicio;

    @Autowired
    public ServicioRestVehiculoController(Servicio servicio) {
        this.servicio = servicio;
    }
    
    @RequestMapping(path = "/alta", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Vehiculo> altaVehiculo(@RequestBody Vehiculo v){ //Hay que poner RequestBody para identificar que lo que viene es un vehículo
        Vehiculo nuevo = servicio.altaVehiculo(v);
        HttpStatus estado = nuevo!=null?HttpStatus.OK:HttpStatus.NOT_MODIFIED;
        return new ResponseEntity<>(nuevo, estado);
    }
    
    @RequestMapping(path="/baja", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.PUT)
    public HttpStatus bajaVehiculo(@RequestBody Vehiculo v){
        servicio.bajaVehiculo(v);
        return HttpStatus.ACCEPTED;
    }
    
    @RequestMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Vehiculo> buscarVehiculo(@PathVariable int id){
        Vehiculo v = servicio.buscaVehiculo(id);
        HttpStatus estado = v!=null?HttpStatus.FOUND:HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(v,estado);
    }
    
    @RequestMapping(path="/lista", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<Vehiculo>> listarVehiculos(){
        List<Vehiculo> clientes = servicio.buscaVehiculos();
        HttpStatus estado = clientes != null?HttpStatus.FOUND:HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(clientes,estado);
    }
    
    @RequestMapping(path = "/actualizar", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Vehiculo> actualizarVehiculo(@RequestBody Vehiculo v){
        Vehiculo act = servicio.actualizaVehiculo(v);
        HttpStatus estado = act!=null?HttpStatus.OK:HttpStatus.NOT_MODIFIED;
        return new ResponseEntity<>(act, estado);
    }
    
}
