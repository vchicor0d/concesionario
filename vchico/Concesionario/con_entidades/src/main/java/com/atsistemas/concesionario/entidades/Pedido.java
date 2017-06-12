/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atsistemas.concesionario.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author vchico
 */
@Entity
@Table(schema = "Concesionario", name = "Pedidos")
@Access(AccessType.FIELD)
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JsonIgnoreProperties("pedidos")
    private Cliente cliente;

    @ManyToOne
    @JsonIgnoreProperties({"pedidos","clientes"})
    private Comercial comercial;

    @ManyToMany
    @JoinTable(schema = "concesionario", name = "vehiculo_pedido")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Vehiculo> vehiculos;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    @Column(nullable = false)
    private EstadoPedido estado;

    @OneToOne
    @JsonIgnoreProperties("pedido")
    private Factura factura;

    @JsonIgnore //Creamos este campo para recibir los valores por Javascript desde la vista
    private String svehiculos;

    public Pedido() {}

    public Pedido(int id, Cliente cliente, Comercial comercial, List<Vehiculo> vehiculos, Date fecha, EstadoPedido estado, Factura factura) {
        this.id = id;
        this.cliente = cliente;
        this.comercial = comercial;
        this.vehiculos = vehiculos;
        this.fecha = fecha;
        this.estado = estado;
        this.factura = factura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Comercial getComercial() {
        return comercial;
    }

    public void setComercial(Comercial comercial) {
        this.comercial = comercial;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public String getSvehiculos() {
        return svehiculos;
    }

    public void setSvehiculos(String svehiculos) {
        this.svehiculos = svehiculos;
        if (svehiculos != null && !svehiculos.isEmpty()) {
            List<Vehiculo> vehiculosAux = new ArrayList<>();
            String[] vehi = svehiculos.split(":");
            if (vehi != null && vehi.length > 0) {
                for (String v : vehi) {
                    if (v.matches("[0-9]+")){
                        Vehiculo ve = new Vehiculo();
                        ve.setId(Integer.parseInt(v));
                        vehiculosAux.add(ve);
                    }
                }
                this.vehiculos = vehiculosAux;
            }
        }
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.fecha);
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
        final Pedido other = (Pedido) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.comercial, other.comercial)) {
            return false;
        }
        if (!Objects.equals(this.vehiculos, other.vehiculos)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.factura, other.factura)) {
            return false;
        }
        return true;
    }

}
