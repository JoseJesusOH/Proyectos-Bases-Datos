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
 * Clase que representa una entidad de producto, la cual contiene los atributos
 * principales de un producto.Con sus respectivas relaciones.
 *
 * @author Luis Gonzalo y José Jesús
 */
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    /**
     * Id del producto.
     */
    @Id
    @Column(name = "id_producto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nombre dle producto.
     */
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    /**
     * Precio actual del producto.
     */
    @Column(name = "precioactual", nullable = false)
    private Float precioActual;
    /**
     * Stock del producto.
     */
    @Column(name = "stock", nullable = false)
    private Integer stock;
    /**
     * Lista la cual contiene en que ventas se ha vendido el producto.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<RelacionProductoVenta> ventas;

    /**
     * Constructor por defecto.
     */
    public Producto() {

    }

    /**
     * Constructor que inicializa el id del producto.
     *
     * @param id El id del producto.
     */
    public Producto(Long id) {
        this.id = id;
    }

    /**
     * Constructor que inicializa el nombre,precio actual, y stock del producto.
     *
     * @param nombre Nombre del producto.
     * @param precioActual Precio actual.
     * @param stock Stock del producto.
     */
    public Producto(String nombre, Float precioActual, Integer stock) {
        this.nombre = nombre;
        this.precioActual = precioActual;
        this.stock = stock;
    }

    /**
     * Constructor que inicializa todos los atributos del producto.
     *
     * @param id Id del producto.
     * @param nombre Nombre del producto.
     * @param precioActual Precio actual.
     * @param stock Stock del producto.
     */
    public Producto(Long id, String nombre, Float precioActual, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.precioActual = precioActual;
        this.stock = stock;
    }

    /**
     * Metodo get para obtener el id del producto.
     *
     * @return El id del producto.
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo set para establecer el id del producto.
     *
     * @param id El id del producto.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo get para obtener el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo set para establecer el nombre del producto.
     *
     * @param nombre El nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo get para obtener el precio actual del producto.
     *
     * @return El precio actual del producto.
     */
    public Float getPrecioActual() {
        return precioActual;
    }

    /**
     * Metodo set para establecer el precio del producto.
     *
     * @param precioActual El precio del producto.
     */
    public void setPrecioActual(Float precioActual) {
        this.precioActual = precioActual;
    }

    /**
     * Metodo get para obtener el stock del producto.
     *
     * @return El stock del producto.
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * Metodo set para establecer el stock del producto.
     *
     * @param stock El stock del producto.
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * Metodo para obtener las relaciones de producto con venta.
     *
     * @return La lista de relaciones.
     */
    public List<RelacionProductoVenta> getVentas() {
        return ventas;
    }

    /**
     * Metodo para establecer las relaciones de producto ventas.
     *
     * @param ventas La lista de relaciones.
     */
    public void setVentas(List<RelacionProductoVenta> ventas) {
        this.ventas = ventas;
    }

    /**
     * Metodo hash para establecer el hash del producto.
     *
     * @return El hash del producto.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Metodo para conocer si el producto es igual que otro.
     *
     * @param object El objeto a comparar.
     * @return true si el objeto es el mismo, false en caso contrario.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Metodo to string para obtener una cadena con todos los atributos del
     * producto.
     *
     * @return La cadena con todos los atributos del producto.
     */
    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", precioActual=" + precioActual + ", stock=" + stock + '}';
    }
}
