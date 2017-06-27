/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import com.atsistemas.concesionario.entidades.Factura;
import com.atsistemas.concesionario.entidades.Pedido;
import com.atsistemas.concesionario.tools.SecurityTools;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @RequestMapping(path = {"","/","/lista"})
    public String lista(Model modelo, HttpSession session){
        RestTemplate restTemplate = new RestTemplate();
        SecurityTools.setAuthority(restTemplate, (String)session.getAttribute("login"));
        List<Factura> lista = restTemplate.getForObject("http://localhost:8080/con_rest/api/factura/lista", List.class);
        modelo.addAttribute("lista", lista);
        modelo.addAttribute("factura", new Factura());
        return "factura/facturas";
    }
    
    @RequestMapping(path = "/cierre/{id}")
    public String cierre(@PathVariable int id, Model modelo, HttpServletRequest request, HttpSession session){
        RestTemplate restTemplate = new RestTemplate();
        SecurityTools.setAuthority(restTemplate, (String)session.getAttribute("login"));
        Factura v = restTemplate.getForObject("http://localhost:8080/con_rest/api/factura/cobro/"+id, Factura.class);
        if(v!=null && v.getPedido()!=null){
            restTemplate.getForObject("http://localhost:8080/con_rest/api/pedido/entrega/"+v.getPedido().getId(), Pedido.class);
        }
        String referencia = request.getHeader("Referer");
        if (referencia != null && !referencia.isEmpty()){
            int indice = referencia.lastIndexOf("/");
            if (indice != -1){
                referencia = referencia.substring(indice+1);
            } else {
                referencia = "lista";
            }
        }
        return "redirect:../"+referencia; //recojo el mapping que hace la referencia a este para volver al mismo (lista o id), por defecto lista
    }
    
    @RequestMapping(path = "{id}")
    public String detalle(@PathVariable int id, Model modelo, HttpSession session){
        RestTemplate restTemplate = new RestTemplate();
        SecurityTools.setAuthority(restTemplate, (String)session.getAttribute("login"));
        Factura f = restTemplate.getForObject("http://localhost:8080/con_rest/api/factura/"+id, Factura.class);
        modelo.addAttribute("factura",f);
        return "factura/detalle";
    }

}
