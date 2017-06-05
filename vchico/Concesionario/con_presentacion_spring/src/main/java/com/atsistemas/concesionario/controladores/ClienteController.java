/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import com.atsistemas.concesionario.entidades.Cliente;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author vchico
 */
@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @RequestMapping(path = "/alta", method = RequestMethod.POST)
    public String alta(@ModelAttribute @Valid Cliente cliente) {
        //Copiado de StackOverflow, creo los headers para que envíe el vehículo en JSON
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(list);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        restTemplate.postForObject("http://localhost:8080/con_rest/api/cliente/alta", cliente, Cliente.class, headers);
        return "redirect:lista";
    }
    
    @RequestMapping(path="/lista")
    public String lista(Model modelo){
        RestTemplate restTemplate = new RestTemplate();
        List<Cliente> lista = restTemplate.getForObject("http://localhost:8080/con_rest/api/cliente/lista", List.class);
        //Hay que añadir al modelo las variables que usará la plantilla, la lista que itera en la tabla y el vehículo que usará para el alta y la modificación
        modelo.addAttribute("lista", lista);
        modelo.addAttribute("cliente", new Cliente());
        //modelo.addAttribute("comercial", restTemplate.getForObject("http://localhost:8080/con_rest/api/comercial/2", Comercial.class));
        modelo.addAttribute("comerciales", restTemplate.getForObject("http://localhost:8080/con_rest/api/comercial/lista", List.class));
        return "cliente/clientes";
    }
    
    @RequestMapping(path="/baja")
    public String baja(@ModelAttribute @Valid Cliente cliente){
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(list);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        restTemplate.put("http://localhost:8080/con_rest/api/cliente/baja", cliente, headers);
        return "redirect:lista";
    }
    
    @RequestMapping(path = "{id}")
    public String detalle(@PathVariable int id, Model modelo){
        RestTemplate restTemplate = new RestTemplate();
        Cliente c = restTemplate.getForObject("http://localhost:8080/con_rest/api/cliente/"+id, Cliente.class);
        modelo.addAttribute("cliente",c);
        return "cliente/detalle";
    }
    
    @RequestMapping(path = "/modifica")
    public String modifica(@ModelAttribute @Valid Cliente cliente){
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(list);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        restTemplate.postForObject("http://localhost:8080/con_rest/api/cliente/actualizar", cliente, Cliente.class, headers);
        return "redirect:lista";
    }

}
