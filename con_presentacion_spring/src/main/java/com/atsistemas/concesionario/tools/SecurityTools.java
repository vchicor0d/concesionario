/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.tools;

import javax.servlet.http.HttpSession;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author vchico
 */
public class SecurityTools {
    
    /**
     * Configura la sesión de usuario en el restTemplate en base al token HttpBasic de sesión
     * @param restTemplate
     * @param session 
     */
    public static void setAuthority(RestTemplate restTemplate, HttpSession session){
        String login = (String)session.getAttribute("login");
        if (login != null) { //Si existe el login en sesión, lo utilizamos
            login = new String(Base64Utils.decodeFromString(login));
            if (login.contains(":")){
                String[] userPass = login.split(":");
                if (userPass.length==2){
                    restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(userPass[0], userPass[1]));
                }
            }
        }
    }
    
}
