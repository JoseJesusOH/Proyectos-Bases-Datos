/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase que representa una entidad de clientes, la cual contiene los atributos
 * principales de un cliennte.Con sus respectivas relaciones.
 *
 * @author Luis Gonzalo y José Jesús
 */
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    /**
     * Id del cliente.
     */
    @Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Dirección del cliente.
     */
    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;
    /**
     * Nombre del cliente.
     */
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    /**
     * RFC del Cliente
     */
    @Column(name = "rfc", nullable = false, length = 15, unique = true)
    private String rfc;
    /**
     * Telefono del cliente.
     */
    @Column(name = "telefono", nullable = true, length = 15)
    private String telefono;

    /**
     * Ventas que ha realizado el cliente.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Venta> ventas;

    /**
     * Constructor por defecto.
     */
    public Cliente() {
    }

    /**
     * Constructor que inicializa el id del cliente.
     *
     * @param id ID del cliente.
     */
    public Cliente(Long id) {
        this.id = id;
    }

    /**
     * Constructor que inicializa todos los atributos dirección,nombre,rfc del
     * cliente.
     *
     * @param direccion Dirección del cliente.
     * @param nombre Nombre del cliente.
     * @param rfc RFC del cliente.
     */
    public Cliente(String direccion, String nombre, String rfc) {
        this.direccion = direccion;
        this.nombre = nombre;
        this.rfc = rfc;
    }

    /**
     * Constructor que inicializa todos los atributos del cliente nombre,
     * direccion, RFC, teléfono.
     *
     * @param direccion Dirección del cliente.
     * @param nombre Nombre del cliente.
     * @param rfc RFC del cliente.
     * @param telefono Telefono del cliente.
     */
    public Cliente(String direccion, String nombre, String rfc, String telefono) {
        this.direccion = direccion;
        this.nombre = nombre;
        this.rfc = rfc;
        this.telefono = telefono;
    }

    /**
     * Constructor que inicializa todos los atributos del cliente.
     *
     * @param id ID del cliente.
     * @param direccion Direccion del cliente.
     * @param nombre Nombre del cliente.
     * @param rfc RFC del cliente.
     * @param telefono Telefono del cliente.
     */
    public Cliente(Long id, String direccion, String nombre, String rfc, String telefono) {
        this.id = id;
        this.direccion = direccion;
        this.nombre = nombre;
        this.rfc = rfc;
        this.telefono = telefono;
    }

    /**
     * Metodo get para obtener el id del cliente.
     *
     * @return El id del cliente.
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo set para establecer el id del cliente.
     *
     * @param id El id del cliente.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo get para obtener la dirección del cliente.
     *
     * @return La dirección del cliente.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Metodo set para establecer la dirección del cliente.
     *
     * @param direccion La dirección del cliente.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Metodo get para obtener el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo set para establecer el nombre del cliente.
     *
     * @param nombre El nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo get para obtener el RFC del cliente.
     *
     * @return El RFC del cliente.
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Metodo set para establecer el RFC del cliente.
     *
     * @param rfc El RFC del cliente.
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * Metodo get para obtener el telefono del cliente.
     *
     * @return El telefono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Metodo set para establecer el telefono del cliente.
     *
     * @param telefono El telefono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Metodo get para obtener las ventas que ha hecho el cliente.
     *
     * @return Las ventas del cliente.
     */
    public List<Venta> getVentas() {
        return ventas;
    }

    /**
     * Metodo set para establecer las ventas del cliente.
     *
     * @param ventas Las ventas del cliente.
     */
    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    /**
     * Metodo hash para establecer el hash del cliente.
     *
     * @return El hash del cliente.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Metodo para conocer si el cliente es igual que otro.
     *
     * @param object El objeto a comparar.
     * @return true si el objeto es el mismo, false en caso contrario.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Metodo to string para btener una cadena con todos los atributos del
     * cliente.
     *
     * @return La cadena con todos los atributos del cliente.
     */
    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", direccion=" + direccion + ", nombre=" + nombre + ", rfc=" + rfc + ", telefono=" + telefono + '}';
    }
}
