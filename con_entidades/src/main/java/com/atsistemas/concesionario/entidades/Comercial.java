/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.entidades;

import com.atsistemas.concesionario.entidades.security.Acceso;
import com.atsistemas.concesionario.entidades.security.Rol;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.util.Base64Utils;

/**
 *
 * @author vchico
 */
@Entity
@Table(schema = "Concesionario", name = "Comerciales")
@Access(AccessType.FIELD)
public class Comercial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(unique = true, nullable = false)
    private String telefono;

    @OneToMany(mappedBy = "comercial")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnoreProperties({"comercial", "pedidos"})
    private List<Cliente> clientes;

    @OneToMany(mappedBy = "comercial")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnoreProperties({"comercial"})
    private List<Pedido> pedidos;

    @OneToOne
    @JsonIgnore
    private Acceso acceso;

    public Comercial() {
    }

    public Comercial(int id, String nombre, String correo, String telefono, List<Cliente> clientes, List<Pedido> pedidos, Acceso acceso) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.clientes = clientes;
        this.pedidos = pedidos;
        this.acceso = acceso;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Acceso getAcceso() {
        return acceso;
    }

    public void setAcceso(Acceso acceso) {
        if (acceso != null) {
            this.acceso = acceso;
        } else {
            Rol rol = new Rol("COMERCIAL", null);
            List<Rol> roles = new ArrayList<>();
            roles.add(rol);
            String password = Base64Utils.encodeToString(nombre.getBytes());
            this.acceso.setPassword(password);
            this.acceso = new Acceso(this.correo, password, false, 0, new Timestamp(System.currentTimeMillis()), roles);
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
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
        final Comercial other = (Comercial) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
