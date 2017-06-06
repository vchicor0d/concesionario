/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import com.atsistemas.concesionario.entidades.Factura;
import com.atsistemas.concesionario.entidades.Pedido;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author vchico
 */
@Controller
@RequestMapping("/factura")
public class FacturaController {

    @RequestMapping(path="/lista")
    public String lista(Model modelo){
        RestTemplate restTemplate = new RestTemplate();
        List<Factura> lista = restTemplate.getForObject("http://localhost:8080/con_rest/api/factura/lista", List.class);
        modelo.addAttribute("lista", lista);
        modelo.addAttribute("factura", new Factura());
        return "factura/facturas";
    }
    
    @RequestMapping(path = "/cierre/{id}")
    public String detalle(@PathVariable int id, Model modelo){
        RestTemplate restTemplate = new RestTemplate();
        Factura v = restTemplate.getForObject("http://localhost:8080/con_rest/api/factura/cobro/"+id, Factura.class);
        if(v!=null && v.getPedido()!=null){
            restTemplate.getForObject("http://localhost:8080/con_rest/pedido/entrega/"+v.getPedido().getId(), Pedido.class);
        }
        return "redirect:lista";
    }

}
