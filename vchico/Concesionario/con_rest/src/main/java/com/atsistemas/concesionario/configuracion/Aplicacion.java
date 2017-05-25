/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.configuracion;

import com.atsistemas.concesionario.entidades.Vehiculo;
import com.atsistemas.concesionario.persistencia.VehiculosDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author vchico
 */
public class Aplicacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AnnotationConfigApplicationContext contexto = new AnnotationConfigApplicationContext(ConfiguracionCore.class);
        
        VehiculosDAO vdao = contexto.getBean(VehiculosDAO.class);
        
        Vehiculo v = new Vehiculo(0, "corsa", "Amarillo", "225", 100000.0);
        
        vdao.save(v);
//        
//        Vehiculo v3 = vdao.findByColor(Color.gray).get(0);
//        
//        System.out.println(v3.getModelo());
//        
//        Vehiculo v4 = vdao.findByModelo("corsa").get(0);
//        
//        System.out.println(v4.getMotor());
//        
//        Vehiculo v5 = vdao.findByMotor("22CCV").get(0);
//        
//        System.out.println(v5.getModelo());
//        
//        Vehiculo v6 = vdao.findByPrecioLessThanEqual(11000.0).get(0);
//        
//        System.out.println(v6.getPrecio());
        
    }
    
}
