/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author vchico
 */
@Entity //Marcamos como entidad de persistencia
@Table(schema = "Concesionario", name = "Clientes") //Le indicamos el esquema y el nombre de la tabla (Si fueran diferentes al nombre)
@Access(AccessType.FIELD) //Acceso a través de los métodos getter y setter, por defecto aplica directamente a la propiedad de la clase.
public class Cliente implements Serializable {

    @Id //Identifica el campo como ID de la tabla
    @GeneratedValue(strategy = GenerationType.AUTO) //Campo autogenerado
    private int id;

    @Column(nullable = false) //Columna not null en base de datos
    private String nombre;

    @Column(unique = true, nullable = false) //Columna que debe ser única
    private String telefono;

    @Column(unique = true, nullable = false)
    private String correo;

    @OneToMany(mappedBy = "cliente")
    @LazyCollection(LazyCollectionOption.FALSE) //Clientes es clave ajena de pedidos, se recuperan inmediatamente
    @JsonIgnoreProperties({"cliente"}) //Ignora el objeto cliente de pedidos en la serialización
    private List<Pedido> pedidos;

    @ManyToOne //Comercial es clave ajena de clientes
    @JsonIgnoreProperties("clientes")
    private Comercial comercial;

    public Cliente() {
    }

    public Cliente(int id, String nombre, String telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Cliente(int id, String nombre, String telefono, String correo, List<Pedido> pedidos) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.pedidos = pedidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Comercial getComercial() {
        return comercial;
    }

    public void setComercial(Comercial comercial) {
        this.comercial = comercial;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
