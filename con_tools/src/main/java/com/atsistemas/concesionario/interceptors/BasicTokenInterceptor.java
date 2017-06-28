/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.interceptors;

import java.io.IOException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;

/**
 *
 * @author vchico
 */
public class BasicTokenInterceptor implements ClientHttpRequestInterceptor {

    private String token;

    public BasicTokenInterceptor(String token) {
        Assert.hasLength(token, "El token no debe estar vacío");
        this.token = token;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("Authorization", "Basic " + token);
        return execution.execute(request, body);
    }

}
