///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.atsistemas.concesionario.configuracion;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
//                    .mvcMatchers("/vehiculo/*","/pedido/*").authenticated()
//                    .mvcMatchers(HttpMethod.POST, "/cliente/*", "/factura/*").hasRole("COMERCIAL")
//                    .mvcMatchers(HttpMethod.PUT, "/cliente/*", "/factura/*").hasRole("COMERCIAL")
//                    .mvcMatchers("/cliente/*", "/factura/*").authenticated()
//                    .mvcMatchers(HttpMethod.POST, "/comercial/*").hasRole("GERENTE")
//                    .mvcMatchers(HttpMethod.PUT, "/comercial/*").hasRole("GERENTE")
//                    .mvcMatchers("/comercial/*").authenticated()
//                    .anyRequest().permitAll()
//                .and()
//                    .httpBasic()
//                .and()
//                    .csrf().disable();
//    }
//
//    @Autowired
//    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("admin").roles("ADMINISTRATIVO");
//        auth.inMemoryAuthentication()
//                .withUser("comercial").password("comercial").roles("COMERCIAL");
//        auth.inMemoryAuthentication()
//                .withUser("gerente").password("gerente").roles("GERENTE");
//    }
//
//}
