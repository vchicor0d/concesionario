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
//        http.authorizeRequests().mvcMatchers(HttpMethod.GET, "/vehiculo").authenticated();
//        http.authorizeRequests().mvcMatchers(HttpMethod.GET, "/pedido").authenticated();
//        http.authorizeRequests().mvcMatchers("/vehiculo", "/pedido").hasRole("ADMINISTRATIVO");
//        http.authorizeRequests().mvcMatchers(HttpMethod.GET, "/cliente").authenticated();
//        http.authorizeRequests().mvcMatchers(HttpMethod.GET, "/factura").authenticated();
//        http.authorizeRequests().mvcMatchers("/cliente", "/factura").hasRole("COMERCIAL");
//        http.authorizeRequests().mvcMatchers(HttpMethod.GET, "/comercial").authenticated();
//        http.authorizeRequests().mvcMatchers("/comercial").hasRole("GERENTE");
//        http.authorizeRequests().antMatchers("/**").authenticated();
//        
//        http.csrf().disable();
//        
//        http.httpBasic();
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
