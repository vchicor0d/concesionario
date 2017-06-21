/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.entidades.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author vchico
 */
@Entity
@Table(schema = "Concesionario", name = "Accesos")
@Access(AccessType.FIELD)
public class Acceso implements Serializable, UserDetails {

    private static final Logger LOG = Logger.getLogger(Acceso.class.getName());
    
    @Id
    private String usuario;
    
    @Column(nullable = false)
    private String password;
    
    @Column(columnDefinition = "boolean default false")
    private boolean bloqueo;
    
    @Column
    private int accesos;
    
    @Column
    private Timestamp ultimoAcceso;
    
    @ManyToMany
    @JoinTable(schema = "Concesionario", name = "Autorizaciones")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnoreProperties({"accesos"})
    private List<Rol> roles;

    public Acceso() {
        LOG.info("Creando Acceso");
    }

    public Acceso(String usuario, String password, boolean bloqueo, int accesos, Timestamp ultimoAcceso, List<Rol> roles) {
        this.usuario = usuario;
        this.password = password;
        this.bloqueo = bloqueo;
        this.accesos = accesos;
        this.ultimoAcceso = ultimoAcceso;
        this.roles = roles;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBloqueo() {
        return bloqueo;
    }

    public void setBloqueo(boolean bloqueo) {
        this.bloqueo = bloqueo;
    }

    public int getAccesos() {
        return accesos;
    }

    public void setAccesos(int accesos) {
        this.accesos = accesos;
    }

    public Timestamp getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Timestamp ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.usuario);
        hash = 83 * hash + Objects.hashCode(this.password);
        hash = 83 * hash + (this.bloqueo ? 1 : 0);
        hash = 83 * hash + this.accesos;
        hash = 83 * hash + Objects.hashCode(this.ultimoAcceso);
        hash = 83 * hash + Objects.hashCode(this.roles);
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
        final Acceso other = (Acceso) obj;
        if (this.bloqueo != other.bloqueo) {
            return false;
        }
        if (this.accesos != other.accesos) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.ultimoAcceso, other.ultimoAcceso)) {
            return false;
        }
        return true;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> autorizaciones = new ArrayList<>();
        LOG.log(Level.INFO, "Recuperando autorizaciones para {0}", this.getUsuario());
        List<Rol> roles = this.getRoles();
        if (roles != null && !roles.isEmpty()){
            for(Rol rol: roles){
                SimpleGrantedAuthority autorizacion = new SimpleGrantedAuthority(rol.getRol());
                autorizaciones.add(autorizacion);
                LOG.log(Level.INFO, "Rol: {0}", rol.getRol());
            }
        }
        return autorizaciones;
    }

    @Override
    public String getUsername() {
        return getUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isBloqueo();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
    
}
