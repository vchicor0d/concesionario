package com.atsistemas.concesionario.boot;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ConRestBootApplication.class);
    }

    protected String[] getServletMappings() {
        return new String[]{"/api/*"};
    }
}
