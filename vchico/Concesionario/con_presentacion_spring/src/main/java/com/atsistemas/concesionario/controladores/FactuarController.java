/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import com.atsistemas.concesionario.entidades.Factura;
import com.atsistemas.concesionario.entidades.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author vchico
 */
@Controller
@RequestMapping("/factura")
public class FactuarController {

    @RequestMapping(path="/lista")
    public String lista(Model modelo){
        RestTemplate restTemplate = new RestTemplate();
        List<Factura> lista = restTemplate.getForObject("http://localhost:8080/con_rest/api/factura/lista", List.class);
        modelo.addAttribute("lista", lista);
        modelo.addAttribute("factura", new Factura());
        return "factura/facturas";
    }
    
    @RequestMapping(path = "/cobro/{id}")
    public String detalle(@PathVariable int id, Model modelo){
        RestTemplate restTemplate = new RestTemplate();
        Factura v = restTemplate.getForObject("http://localhost:8080/con_rest/api/factura/cobro/"+id, Factura.class);
        modelo.addAttribute("factura",v);
        return "vehiculo/detalle";
    }
    
    @RequestMapping(path = "/modifica")
    public String modifica(@ModelAttribute @Valid Vehiculo vehiculo){
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(list);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        restTemplate.postForObject("http://localhost:8080/con_rest/api/vehiculo/actualizar", vehiculo, Vehiculo.class, headers);
        return "redirect:lista";
    }

}
