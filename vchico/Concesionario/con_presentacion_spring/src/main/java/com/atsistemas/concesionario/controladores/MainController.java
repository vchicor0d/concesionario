/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author vchico
 */
@Controller
public class MainController {
    
    @RequestMapping(path = {"/", "/inicio"})
    public String inicio(){
        return "index";
    }
    
}
