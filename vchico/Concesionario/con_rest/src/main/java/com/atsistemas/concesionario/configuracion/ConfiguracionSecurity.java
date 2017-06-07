/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author vchico
 */
@Configuration
@EnableWebSecurity
public class ConfiguracionSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/vehiculo/*").hasRole("ADMINISTRATIVO")
                .antMatchers(HttpMethod.PUT, "/vehiculo/*").hasRole("ADMINISTRATIVO")
                .antMatchers(HttpMethod.POST, "/clientes/*").hasRole("COMERCIAL")
                .antMatchers(HttpMethod.PUT, "/clientes/*").hasRole("COMERCIAL")
                .antMatchers(HttpMethod.POST, "/comercial/*").hasRole("GERENTE")
                .antMatchers(HttpMethod.PUT, "/comercial/*").hasRole("GERENTE")
                .antMatchers(HttpMethod.POST, "/factura/*").hasRole("COMERCIAL")
                .antMatchers(HttpMethod.PUT, "/factura/*").hasRole("COMERCIAL")
                .antMatchers(HttpMethod.POST, "/pedido/*").hasRole("ADMINISTRATIVO")
                .antMatchers(HttpMethod.PUT, "/pedido/*").hasRole("ADMINISTRATIVO")
                .anyRequest().permitAll()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMINISTRATIVO");
        auth.inMemoryAuthentication().withUser("comercial").password("comercial").roles("COMERCIAL");
        auth.inMemoryAuthentication().withUser("gerente").password("gerente").roles("GERENTE");
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("admin").password("admin").roles("ADMINISTRATIVO").build());
//        manager.createUser(User.withUsername("comercial").password("comercial").roles("COMERCIAL").build());
//        manager.createUser(User.withUsername("gerente").password("gerente").roles("GERENTE").build());
//        return manager;
//    }

}
