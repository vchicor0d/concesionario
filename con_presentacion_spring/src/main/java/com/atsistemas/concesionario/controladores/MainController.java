/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import com.atsistemas.concesionario.entidades.security.Acceso;
import java.security.Principal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
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
    
    @RequestMapping("/")
    public String entrada(HttpSession session){
        Acceso login = (Acceso)session.getAttribute("login");
        return login==null?"redirect:login":"redirect:inicio";
    }
    
    @RequestMapping(path= {"/inicio"})
    public String inicio(){
        return "index";
    }
    
    @RequestMapping(method = RequestMethod.GET, path = {"/login"})
    public String login(Model modelo){
        modelo.addAttribute("acceso", new Acceso());
        return "login";
    }
    
    @RequestMapping(method = RequestMethod.POST, path = {"/login"})
    public String login(@ModelAttribute @Valid Acceso acceso, HttpSession session, HttpServletRequest request) throws ServletException{
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        UserDetails login;
        String redirect = "login";
        if (acceso.getUsuario() != null && !acceso.getUsuario().isEmpty() && acceso.getPassword() != null && !acceso.getPassword().isEmpty()){
            login = restTemplate.postForObject("http://localhost:8080/con_rest/api/login", acceso, Acceso.class, headers);
            request.login(acceso.getUsername(), acceso.getPassword());
            Principal p = request.getUserPrincipal();
        } else {
            login = null;
        }
        if (login!=null){
            String userPass = login.getUsername()+":"+login.getPassword();
            userPass = Base64Utils.encodeToString(userPass.getBytes());
            session.setAttribute("login", userPass);
            redirect = "inicio";
        }
        return "redirect:"+redirect;
    }
    
}
