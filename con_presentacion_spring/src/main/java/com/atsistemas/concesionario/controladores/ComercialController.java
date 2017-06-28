/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import com.atsistemas.concesionario.entidades.Comercial;
import com.atsistemas.concesionario.tools.SecurityTools;
import java.util.List;
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
@RequestMapping("/comercial")
public class ComercialController {

    @RequestMapping(path = "/alta", method = RequestMethod.POST)
    public String alta(@ModelAttribute @Valid Comercial comercial, HttpSession session) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        SecurityTools.setAuthority(restTemplate, (String)session.getAttribute("login"));
        SecurityTools.setContentTypeJSON(headers);
        restTemplate.postForObject("http://localhost:8080/con_rest/api/comercial/alta", comercial, Comercial.class, headers);
        return "redirect:lista";
    }
    
    @RequestMapping(path = {"","/","/lista"})
    public String lista(Model modelo, HttpSession session){
        RestTemplate restTemplate = new RestTemplate();
        SecurityTools.setAuthority(restTemplate, (String)session.getAttribute("login"));
        List<Comercial> lista = restTemplate.getForObject("http://localhost:8080/con_rest/api/comercial/lista", List.class);
        //Hay que añadir al modelo las variables que usará la plantilla, la lista que itera en la tabla y el vehículo que usará para el alta y la modificación
        modelo.addAttribute("lista", lista);
        modelo.addAttribute("comercial", new Comercial());
        return "comercial/comerciales";
    }
    
    @RequestMapping(path="/baja")
    public String baja(@ModelAttribute @Valid Comercial comercial, HttpSession session){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        SecurityTools.setAuthority(restTemplate, (String)session.getAttribute("login"));
        SecurityTools.setContentTypeJSON(headers);
        restTemplate.put("http://localhost:8080/con_rest/api/comercial/baja", comercial, headers);
        return "redirect:lista";
    }
    
    @RequestMapping(path = "{id}")
    public String detalle(@PathVariable int id, Model modelo, HttpSession session){
        RestTemplate restTemplate = new RestTemplate();
        SecurityTools.setAuthority(restTemplate, (String)session.getAttribute("login"));
        Comercial c = restTemplate.getForObject("http://localhost:8080/con_rest/api/comercial/"+id, Comercial.class);
        modelo.addAttribute("comercial",c);
        return "comercial/detalle";
    }
    
    @RequestMapping(path = "/modifica")
    public String modifica(@ModelAttribute @Valid Comercial comercial, HttpSession session){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        SecurityTools.setAuthority(restTemplate, (String)session.getAttribute("login"));
        SecurityTools.setContentTypeJSON(headers);
        restTemplate.postForObject("http://localhost:8080/con_rest/api/comercial/actualizar", comercial, Comercial.class, headers);
        return "redirect:lista";
    }

}
