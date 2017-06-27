///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.atsistemas.concesionario.configuracion;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.session.web.http.HeaderHttpSessionStrategy;
//import org.springframework.session.web.http.HttpSessionStrategy;
//
///**
// *
// * @author vchico
// */
//@Configuration
//@EnableWebSecurity
//public class ConfiguracionSecurity extends WebSecurityConfigurerAdapter {
//
//    @Override 
//    protected void configure(HttpSecurity http) throws Exception {
//        
//        http    //autorizamos por http básico a cada rol a hacer modificaciones y a todos a consultar
//                .authorizeRequests()
//                    .mvcMatchers(HttpMethod.POST, "/vehiculo/*","/pedido/*").hasRole("ADMINISTRATIVO")
//                    .mvcMatchers(HttpMethod.PUT, "/vehiculo/*","/pedido/*").hasRole("ADMINISTRATIVO")
//                    .mvcMatchers(HttpMethod.GET, "/vehiculo/*","/pedido/*").authenticated()
//                    .mvcMatchers(HttpMethod.POST, "/cliente/*", "/factura/*").hasRole("COMERCIAL")
//                    .mvcMatchers(HttpMethod.PUT, "/cliente/*", "/factura/*").hasRole("COMERCIAL")
//                    .mvcMatchers(HttpMethod.GET, "/cliente/*", "/factura/*").authenticated()
//                    .mvcMatchers(HttpMethod.POST, "/comercial/*").hasRole("GERENTE")
//                    .mvcMatchers(HttpMethod.PUT, "/comercial/*").hasRole("GERENTE")
//                    .mvcMatchers(HttpMethod.GET, "/comercial/*").authenticated()
//                    .anyRequest().permitAll()
//                .and()
//                    .formLogin().loginPage("/login");
//    }
//    
//    @Bean
//    public HttpSessionStrategy httpSessionStrategy() {
//        return new HeaderHttpSessionStrategy();
//    }
//
//}
