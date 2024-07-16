/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase que representa una entidad de re relacion de venta- producto, la cual
 * contiene los atributos principales de esta relacion.
 *
 * @author Luis Gonzalo y José Jesús
 */
@Entity
@Table(name = "rel_productosventas")
public class RelacionProductoVenta implements Serializable {

    /**
     * Id de la relación.
     */
    @Id
    @Column(name = "id_producto_venta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Cantidad total.
     */
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
    /**
     * Precio el cual tenia el producto.
     */
    @Column(name = "precio", nullable = false)
    private Float precio;
    /**
     * Total.
     */
    @Column(name = "importe", nullable = false)
    private Float importe;
    /**
     * Producto de la relacion.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
    /**
     * Venta de la relacion.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_venta", nullable = false)
    private Venta venta;

    /**
     * Contructor por defecto de la relación.
     */
    public RelacionProductoVenta() {
    }

    /**
     * Constructor que inicializa la cantidad,importe y precio de la relación.
     *
     * @param cantidad Cantidad de la relacion.
     * @param precio Precio del producto.
     * @param importe Importe total.
     */
    public RelacionProductoVenta(Integer cantidad, Float precio, Float importe) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.importe = importe;
    }

    /**
     * Constructor que inicializa todos los atributos de la realcion.
     *
     * @param id Id de la relación.
     * @param cantidad Cantidad de la relación.
     * @param precio Precio del producto.
     * @param importe Importe de la relación.
     */
    public RelacionProductoVenta(Long id, Integer cantidad, Float precio, Float importe) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio = precio;
        this.importe = importe;
    }

    /**
     * Metodo get para obtener el id de la relacion.
     *
     * @return El id de la relación.
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo set para establecer el id de la relación.
     *
     * @param id El de la relación.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo get para obtener la cantidad de la relación.
     *
     * @return La cantidad de la relación.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Metodo set para establecer la cantidad de la relacion.
     *
     * @param cantidad La cantidad de la relación.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Metodo get para obtener el precio de la relación.
     *
     * @return El precio de la relación.
     */
    public Float getPrecio() {
        return precio;
    }

    /**
     * Metodo set para establecer el precio de la relación.
     *
     * @param precio El precio de la relación.
     */
    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    /**
     * Metodo get para obtener el importe de la relación.
     *
     * @return El importe de la relación.
     */
    public Float getImporte() {
        return importe;
    }

    /**
     * Metodo set para establecer el importe de la relación.
     *
     * @param importe El importe de la relación.
     */
    public void setImporte(Float importe) {
        this.importe = importe;
    }

    /**
     * Metodo get para obtener el producto de la relación.
     *
     * @return El producto de la relación.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Metodo set para establecer el producto de la relación.
     *
     * @param producto El producto de la realación.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Metodo get para obtener la venta de la relación.
     *
     * @return La venta de la relación.
     */
    public Venta getVenta() {
        return venta;
    }

    /**
     * Metodo set para establecer la venta de la relación.
     *
     * @param venta La venta de la relación.
     */
    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    /**
     * Metodo hash para establecer el hash de la relacion venta-producto.
     *
     * @return El hash de la relación venta-producto.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Metodo para conocer si la relacion es igual que el objeto.
     *
     * @param object El objeto a comparar.
     * @return true si el objeto es el mismo, false en caso contrario.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelacionProductoVenta)) {
            return false;
        }
        RelacionProductoVenta other = (RelacionProductoVenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Metodo to string para obtener una cadena con todos los atributos de la
     * relacion venta-producto.
     *
     * @return La cadena con todos los atributos de la relación venta-producto.
     */
    @Override
    public String toString() {
        return "RelacionProductoVenta{" + "id=" + id + ", cantidad=" + cantidad + ", precio=" + precio + ", importe=" + importe + ", producto=" + producto + ", venta=" + venta + '}';
    }
}
