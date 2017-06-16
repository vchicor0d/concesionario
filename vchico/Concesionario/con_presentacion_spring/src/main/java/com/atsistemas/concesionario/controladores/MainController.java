/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import com.atsistemas.concesionario.entidades.Acceso;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author vchico
 */
@Controller
public class MainController {
    
    @RequestMapping(path= {"/inicio"})
    public String inicio(){
        //TODO: Página por defecto, si no está logueado redirigimos a login
        return "index";
    }
    
    @RequestMapping(method = RequestMethod.GET, path = {"/", "/login"})
    public String login(Model modelo){
        modelo.addAttribute("acceso", new Acceso());
        return "login";
    }
    
    @RequestMapping(method = RequestMethod.POST, path = {"/login"})
    public String login(@ModelAttribute @Valid Acceso acceso, Principal principal){
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(list);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        UserDetails login;
        String redirect = "login";
        if (acceso.getUsuario() != null && !acceso.getUsuario().isEmpty() && acceso.getPassword() != null && !acceso.getPassword().isEmpty()){
            login = restTemplate.postForObject("http://localhost:8080/con_rest/api/login", acceso, Acceso.class, headers);
        } else {
            login = null;
        }
        if (login!=null){
            redirect = "inicio";
        }
        return "redirect:"+redirect;
    }
    
}
