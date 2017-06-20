/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import com.atsistemas.concesionario.entidades.Vehiculo;
import com.atsistemas.concesionario.tools.SecurityTools;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author vchico
 */
@Controller
@RequestMapping("/vehiculo")
public class VehiculoController {

    @RequestMapping(path = "/alta", method = RequestMethod.POST)
    public String alta(@ModelAttribute @Valid Vehiculo vehiculo, HttpSession session) {
        RestTemplate restTemplate = new RestTemplate();
        SecurityTools.setAuthority(restTemplate, session);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Vehiculo ve = restTemplate.postForObject("http://localhost:8080/con_rest/api/vehiculo/alta", vehiculo, Vehiculo.class, headers);
        return "redirect:lista";
    }
    
    @RequestMapping(path = {"","/","/lista"})
    public String lista(Model modelo, HttpSession session){
        RestTemplate restTemplate = new RestTemplate();
        SecurityTools.setAuthority(restTemplate, session);
        List<Vehiculo> lista = restTemplate.getForObject("http://localhost:8080/con_rest/api/vehiculo/lista", List.class);
        //Hay que añadir al modelo las variables que usará la plantilla, la lista que itera en la tabla y el vehículo que usará para el alta y la modificación
        modelo.addAttribute("lista", lista);
        modelo.addAttribute("vehiculo", new Vehiculo());
        return "vehiculo/vehiculos";
    }
    
    @RequestMapping(path="/baja")
    public String baja(@ModelAttribute @Valid Vehiculo vehiculo, HttpSession session){
        RestTemplate restTemplate = new RestTemplate();
        SecurityTools.setAuthority(restTemplate, session);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        restTemplate.put("http://localhost:8080/con_rest/api/vehiculo/baja", vehiculo, headers);
        return "redirect:lista";
    }
    
    @RequestMapping(path = "{id}")
    public String detalle(@PathVariable int id, Model modelo, HttpSession session){
        RestTemplate restTemplate = new RestTemplate();
        SecurityTools.setAuthority(restTemplate, session);
        Vehiculo v = restTemplate.getForObject("http://localhost:8080/con_rest/api/vehiculo/"+id, Vehiculo.class);
        modelo.addAttribute("vehiculo",v);
        return "vehiculo/detalle";
    }
    
    @RequestMapping(path = "/modifica")
    public String modifica(@ModelAttribute @Valid Vehiculo vehiculo, HttpSession session){
        RestTemplate restTemplate = new RestTemplate();
        SecurityTools.setAuthority(restTemplate, session);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        restTemplate.postForObject("http://localhost:8080/con_rest/api/vehiculo/actualizar", vehiculo, Vehiculo.class, headers);
        return "redirect:lista";
    }

}
