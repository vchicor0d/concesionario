/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.entidades.security;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author vchico
 */
@Entity
@Table(schema = "Concesionario", name = "Roles")
@Access(AccessType.FIELD)
public class Rol implements Serializable, GrantedAuthority {
    
    @Id
    private String rol;

    @ManyToMany(mappedBy = "roles")
    private List<Acceso> accesos;

    public Rol() {
    }

    public Rol(String rol, List<Acceso> accesos) {
        this.rol = rol;
        this.accesos = accesos;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<Acceso> getAccesos() {
        return accesos;
    }

    public void setAccesos(List<Acceso> accesos) {
        this.accesos = accesos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.rol);
        hash = 17 * hash + Objects.hashCode(this.accesos);
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
        final Rol other = (Rol) obj;
        if (!Objects.equals(this.rol, other.rol)) {
            return false;
        }
        return true;
    }

    @Override
    public String getAuthority() {
        return this.rol;
    }
    
}
