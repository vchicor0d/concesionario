/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.configuracion;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author vchico
 */
public class InicializadoraWeb extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ConfiguracionRest.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/api/*"};
    }
    
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        //Se define el contexto de spring mediante clases anotadas
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.setConfigLocation("com.atsistemas.concesionario.configuracion");
//        
//        //Conectamos el servlet de entrada con el contexto de spring
//        DispatcherServlet ds = new DispatcherServlet(context);
//        
//        //Interfaz que cumple un servlet que nos permite añadir mapeos
//        ServletRegistration.Dynamic addServlet = servletContext.addServlet("ds", ds);
//        
//        //Configuramos Spring Security
//        FilterRegistration.Dynamic addFilter = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"));
//        
//        //Lo mapeamos
//        addFilter.addMappingForUrlPatterns(null, false, "/api/*");
//        
//        addServlet.setLoadOnStartup(0);
//        addServlet.addMapping("/api/*");
//    }
    
}
