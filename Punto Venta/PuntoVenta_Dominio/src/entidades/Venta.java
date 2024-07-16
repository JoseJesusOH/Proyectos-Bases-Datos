/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase que representa una entidad de ventas, la cual contiene los atributos
 * principales de una venta.Con sus respectivas relaciones.
 *
 * @author Luis Gonzalo y José Jesús
 */
@Entity
@Table(name = "ventas")
public class Venta implements Serializable {

    /**
     * El id de la venta.
     */
    @Id
    @Column(name = "id_venta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * El descuento de la venta.
     */
    @Column(name = "descuento", nullable = false)
    private Float descuento;
    /**
     * La fecha de la venta.
     */
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fecha;
    /**
     * Monto total de la venta.
     */
    @Column(name = "montototal", nullable = false)
    private Float montoTotal;
    /**
     * Cliente que realizo la venta.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    /**
     * Lista de productos de la venta.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venta")
    private List<RelacionProductoVenta> productos;

    /**
     * Contructor por defecto de venta.
     */
    public Venta() {

    }

    /**
     * Contructor que inicializa todos descuento, fecha, y monto total de la
     * venta.
     *
     * @param descuento Descuento de la venta.
     * @param fecha Fecha de la venta.
     * @param montoTotal Monto total de la venta.
     */
    public Venta(Float descuento, Calendar fecha, Float montoTotal) {
        this.descuento = descuento;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
    }

    /**
     * Constructor que inicializa todos los atributos de la venta.
     *
     * @param id Id de la venta.
     * @param descuento Descuento de la venta.
     * @param fecha Fecha de la venta.
     * @param montoTotal Monto total de la venta.
     */
    public Venta(Long id, Float descuento, Calendar fecha, Float montoTotal) {
        this.id = id;
        this.descuento = descuento;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
    }

    /**
     * Metodo get para obtener el id de la venta.
     *
     * @return El id de la venta.
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo set para establecer el id de la venta.
     *
     * @param id El id de la venta.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo get para obtener el descuento de la venta.
     *
     * @return El descuento de la venta.
     */
    public Float getDescuento() {
        return descuento;
    }

    /**
     * Metodo set para establecer el descuento de la venta.
     *
     * @param descuento El descuento de la venta.
     */
    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    /**
     * Metodo get para obtener el la fecha de la venta.
     *
     * @return La fecha de la venta.
     */
    public Calendar getFecha() {
        return fecha;
    }

    /**
     * Metodo set para establecer la fecha de la venta.
     *
     * @param fecha La fecha de la venta.
     */
    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    /**
     * Metodo get para obtener el monto total de la venta.
     *
     * @return El monto total de la venta.
     */
    public Float getMontoTotal() {
        return montoTotal;
    }

    /**
     * Metodo set para establecer el monto total de la venta.
     *
     * @param montoTotal El monto total de la venta.
     */
    public void setMontoTotal(Float montoTotal) {
        this.montoTotal = montoTotal;
    }

    /**
     * Metodo get para obtener el cliente de la venta.
     *
     * @return El cliente de la venta.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Metodo set para establecer el cliente de la venta.
     *
     * @param cliente El cliente de la venta.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Metodo get para obtener el los productos de la venta.
     *
     * @return La lista de productos de la venta.
     */
    public List<RelacionProductoVenta> getProductos() {
        return productos;
    }

    /**
     * * Metodo set para establecer la lista de productos de la venta.
     *
     * @param productos
     */
    public void setProductos(List<RelacionProductoVenta> productos) {
        this.productos = productos;
    }

    /**
     * Metodo hash para establecer el hash de la venta.
     *
     * @return El hash de la venta.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Metodo para conocer si la venta es igual que el objeto.
     *
     * @param object El objeto a comparar.
     * @return true si el objeto es el mismo, false en caso contrario.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Metodo to string para obtener una cadena con todos los atributos de la
     * venta.
     *
     * @return La cadena con todos los atributos de la venta.
     */
    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", descuento=" + descuento + ", fecha=" + fecha + ", montoTotal=" + montoTotal + ", cliente=" + cliente + '}';
    }
}
