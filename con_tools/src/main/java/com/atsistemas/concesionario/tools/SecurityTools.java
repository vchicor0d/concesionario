/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.tools;

import com.atsistemas.concesionario.interceptors.BasicTokenInterceptor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author vchico
 */
public class SecurityTools {
    
    /**
     * Configura la sesión de usuario en el restTemplate en base al token HttpBasic de sesión
     * @param template
     * @param token 
     */
    public static void setAuthority(RestTemplate template, String token){
        template.getInterceptors().add(new BasicTokenInterceptor(token));
    }
    
    public static void setContentTypeJSON(HttpHeaders headers){
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }    
    
}
