/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import com.atsistemas.concesionario.entidades.security.Acceso;
import com.atsistemas.concesionario.entidades.security.Rol;
import com.atsistemas.concesionario.tools.SecurityTools;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
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
        String login = (String)session.getAttribute("login");
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
        SecurityTools.setContentTypeJSON(headers);
        Acceso auth;
        String redirect = "login";
        if (acceso.getUsuario() != null && !acceso.getUsuario().isEmpty() && acceso.getPassword() != null && !acceso.getPassword().isEmpty()){
            auth = restTemplate.postForObject("http://localhost:8080/con_rest/api/login", acceso, Acceso.class, headers);
        } 
        else {
            auth = null;
        }
        if (auth!=null){
            String userPass = auth.getUsername()+":"+auth.getPassword();
            userPass = Base64Utils.encodeToString(userPass.getBytes());
            session.setAttribute("login", userPass);
            List<String> roles = new ArrayList<>();
            for (Rol rol : auth.getRoles()){
                roles.add(rol.toString());
            }
            session.setAttribute("authorities", roles);
            redirect = "inicio";
        }
        return "redirect:"+redirect;
    }
    
    @RequestMapping(path = {"/logout"})
    public String logout(HttpSession session){
        session.removeAttribute("login");
        session.removeAttribute("authorities");
        return "redirect:login";
    }
    
}
