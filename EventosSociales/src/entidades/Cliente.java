package entidades;

/**
 * Clase la cual representa una entidad de cliente.
 */
import java.util.Objects;

public class Cliente {

    /**
     * ID del cliente
     */
    private Integer idCliente;
    /**
     * Nombre del cliente
     */
    private String nombre;
    /**
     * Dirección del cliente
     */
    private String direccion;
    /**
     * Teléfono del cliente
     */
    private String telefono;

    /**
     * Constructor por omision
     */
    public Cliente() {
    }

    /**
     * Constructor que inicializa el id del cliente
     *
     * @param idCliente ID del cliente
     */
    public Cliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Constructor que inicializa el nombre,dirección y teléfono del cliente.
     *
     * @param nombre Nombre del cliente
     * @param direccion Direccion del cliente
     * @param telefono Teléfono del cliente
     */
    public Cliente(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    /**
     * Constructor que inicializa todos los atributos del cliente.
     *
     * @param idCliente ID del cliente
     * @param nombre Nombre del cliente
     * @param direccion Dirección del cliente
     * @param telefono Teléfono del cliente
     */
    public Cliente(Integer idCliente, String nombre, String direccion, String telefono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    /**
     * Metodo que obtiene el id del cliente.
     *
     * @return El id del cliente
     */
    public Integer getIdCliente() {
        return idCliente;
    }

    /**
     * Metodo que establece el id del cliente
     *
     * @param idCliente ID del cliente
     */
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Metodo get que regresa el nombre del cliente
     *
     * @return El nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que establece el nombre del cliente
     *
     * @param nombre EL nombre del cliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que obtiene la dirección del cliente
     *
     * @return La dirección del cliente
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Metodo que establece la direccion del cliente
     *
     * @param direccion La dirección del cliente
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Metodo que regresa el teléfono del cliente
     *
     * @return El telefono del cliente
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Metodo que estable el teléfono del cliente
     *
     * @param telefono El teléfono del cliente
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Metodo hashCode que permite conocer un cliente
     *
     * @return El hashCode del cliente
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.idCliente);
        return hash;
    }

    /**
     * Metodo equals de la clase cliente
     *
     * @param obj El objeto a comparar
     * @return true si el objeto es el mismo, false en caso contrario
     */
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
        if (!Objects.equals(this.idCliente, other.idCliente)) {
            return false;
        }
        return true;
    }

    /**
     * Metodo toString() que presenta todos los atributos del cliente.
     *
     * @return La cadena con los atributos del cliente.
     */
    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + '}';
    }

}
