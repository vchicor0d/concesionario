/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import com.atsistemas.concesionario.entidades.Comercial;
import com.atsistemas.concesionario.entidades.Factura;
import com.atsistemas.concesionario.entidades.Pedido;
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
@RequestMapping("/pedido")
public class PedidoController {

    @RequestMapping(path = "/alta", method = RequestMethod.POST)
    public String alta(@ModelAttribute @Valid Pedido pedido) {
        //Copiado de StackOverflow, creo los headers para que envíe el vehículo en JSON
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(list);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        restTemplate.postForObject("http://localhost:8080/con_rest/api/pedido/alta", pedido, Pedido.class, headers);
        return "redirect:lista";
    }
    
    @RequestMapping(path="/lista")
    public String lista(Model modelo){
        RestTemplate restTemplate = new RestTemplate();
        List<Comercial> lista = restTemplate.getForObject("http://localhost:8080/con_rest/api/pedido/lista", List.class);
        //Hay que añadir al modelo las variables que usará la plantilla, la lista que itera en la tabla y el vehículo que usará para el alta y la modificación
        modelo.addAttribute("lista", lista);
        modelo.addAttribute("pedido", new Pedido());
        return "pedido/pedidos";
    }
    
    @RequestMapping(path = "{id}")
    public String detalle(@PathVariable int id, Model modelo){
        RestTemplate restTemplate = new RestTemplate();
        Pedido p = restTemplate.getForObject("http://localhost:8080/con_rest/api/pedido/"+id, Pedido.class);
        modelo.addAttribute("pedido",p);
        return "pedido/detalle";
    }
    
    @RequestMapping("/recepcion/{id}")
    public String recepcion(@PathVariable int id) {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(list);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Pedido p = restTemplate.getForObject("http://localhost:8080/con_rest/api/pedido/recepcion/"+id, Pedido.class);
        if(p!=null){
            restTemplate.postForObject("http://localhost:8080/con_rest/api/factura/generarFactura", p, Factura.class, headers);
        }
        return "redirect:lista";
    }

}
