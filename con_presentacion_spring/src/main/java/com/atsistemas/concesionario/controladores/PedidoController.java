/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import com.atsistemas.concesionario.entidades.Cliente;
import com.atsistemas.concesionario.entidades.Comercial;
import com.atsistemas.concesionario.entidades.Factura;
import com.atsistemas.concesionario.entidades.Pedido;
import com.atsistemas.concesionario.entidades.Vehiculo;
import com.atsistemas.concesionario.tools.SecurityTools;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
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
    public String alta(@ModelAttribute @Valid Pedido pedido, HttpSession session) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        SecurityTools.setAuthority(restTemplate, (String)session.getAttribute("login"));
        SecurityTools.setContentTypeJSON(headers);
        restTemplate.postForObject("http://localhost:8080/con_rest/api/pedido/alta", pedido, Pedido.class, headers);
        return "redirect:lista";
    }
    
    @RequestMapping(path = {"","/","/lista"})
    public String lista(Model modelo, HttpSession session){
        RestTemplate restTemplate = new RestTemplate();
        SecurityTools.setAuthority(restTemplate, (String)session.getAttribute("login"));
        List<Comercial> lista = restTemplate.getForObject("http://localhost:8080/con_rest/api/pedido/lista", List.class);
        //Hay que añadir al modelo las variables que usará la plantilla, la lista que itera en la tabla y el vehículo que usará para el alta y la modificación
        modelo.addAttribute("lista", lista);
        modelo.addAttribute("pedido", new Pedido());
        List<Cliente> clientes = restTemplate.getForObject("http://localhost:8080/con_rest/api/cliente/lista", List.class);
        modelo.addAttribute("clientes", clientes);
        List<Comercial> comerciales = restTemplate.getForObject("http://localhost:8080/con_rest/api/comercial/lista", List.class);
        modelo.addAttribute("comerciales", comerciales);
        List<Vehiculo> vehiculos = restTemplate.getForObject("http://localhost:8080/con_rest/api/vehiculo/lista", List.class);
        modelo.addAttribute("vehi", vehiculos);
        modelo.addAttribute("vehiculo", new Vehiculo());
        return "pedido/pedidos";
    }
    
    @RequestMapping(path = "{id}")
    public String detalle(@PathVariable int id, Model modelo, HttpSession session){
        RestTemplate restTemplate = new RestTemplate();
        SecurityTools.setAuthority(restTemplate, (String)session.getAttribute("login"));
        Pedido p = restTemplate.getForObject("http://localhost:8080/con_rest/api/pedido/"+id, Pedido.class);
        modelo.addAttribute("pedido",p);
        return "pedido/detalle";
    }
    
    @RequestMapping("/recepcion/{id}")
    public String recepcion(@PathVariable int id, HttpServletRequest request, HttpSession session) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        SecurityTools.setAuthority(restTemplate, (String)session.getAttribute("login"));
        SecurityTools.setContentTypeJSON(headers);
        Pedido p = restTemplate.getForObject("http://localhost:8080/con_rest/api/pedido/recepcion/"+id, Pedido.class);
        if(p!=null){
            restTemplate.postForObject("http://localhost:8080/con_rest/api/factura/generarFactura", p, Factura.class, headers);
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

}
