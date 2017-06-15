/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import com.atsistemas.concesionario.entidades.Acceso;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String login(@ModelAttribute @Valid Acceso acceso){
        return "redirect:inicio";
    }
    
}
