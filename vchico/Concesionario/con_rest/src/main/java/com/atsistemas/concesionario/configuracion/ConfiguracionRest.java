/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.configuracion;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author vchico
 */
public class ConfiguracionRest implements WebApplicationInitializer {
    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //Se define el contexto de spring mediante clases anotadas
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("com.atsistemas.concesionario.configuracion");
        
        //Configuramos Spring Security
        servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain")).addMappingForUrlPatterns(null, true, "/*");
        
        //Conectamos el servlet de entrada con el contexto de spring
        DispatcherServlet ds = new DispatcherServlet(context);
        
        //Interfaz que cumple un servlet que nos permite añadir mapeos
        ServletRegistration.Dynamic addServlet = servletContext.addServlet("ds", ds);
        
        //Lo mapeamos
        addServlet.setLoadOnStartup(0);
        addServlet.addMapping("/api/*");
    }
}
