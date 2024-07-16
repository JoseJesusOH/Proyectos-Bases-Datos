
package entidades;

import java.util.Objects;

/**
 * Clase que representa un entidad de lugar.
 * @author José Jesús Orozco Hernández ID; 00000229141
 */
public class Lugar {
/**
 * ID del lugar
 */
    private Integer idLugar;
    /**
     * Nombre del lugar
     */
    private String nombre;
/**
 * Dirección del lugar
 */
    private String direccion;
    /**
     * Latitud del lugar
     */
    private Float latitud;
    /**
     * Longitud del lugar
     */
    private Float longitd;
/**
 * Constructor por omision 
 */
    public Lugar() {
    }
/**
 * Constructor que inicializa el id del lugar
 * @param idLugar EL ID del lugar
 */
    public Lugar(Integer idLugar) {
        this.idLugar = idLugar;
    }
/**
 * Constructor que inicializa los atribtos del lugar, nombre,dirección, latitud y longitud.
 * @param nombre Nombre del lugar
 * @param direccion Dirección del lugar
 * @param latitud Latitud del lugar
 * @param longitd Longitud del lugar
 */
    public Lugar(String nombre, String direccion, Float latitud, Float longitd) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitd = longitd;
    }
/**
 * Constructor que inicializa todos los atributos del lugar.
 * @param idLugar ID del lugar
 * @param nombre Nombre dle lugar
 * @param direccion Dirección del lugar 
 * @param latitud Latitud del lugar
 * @param longitd Longitud del lugar
 */
    public Lugar(Integer idLugar, String nombre, String direccion, Float latitud, Float longitd) {
        this.idLugar = idLugar;
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitd = longitd;
    }
/**
 * Metodo que tegresa el id del lugar
 * @return El id del lugar 
 */
    public Integer getIdLugar() {
        return idLugar;
    }
/**
 * Metodo que establece el id del lugar
 * @param idLugar EL id del lugar
 */
    public void setIdLugar(Integer idLugar) {
        this.idLugar = idLugar;
    }
/**
 * Metodo para obtener el nombre del lugar
 * @return El nombre del lugar
 */
    public String getNombre() {
        return nombre;
    }
/**
 * Metodo para establecer el nombre del lugar
 * @param nombre El nombre del lugar
 */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
/**
 * Metodo para obtener la dirección del lugar
 * @return La dirección del lugar
 */
    public String getDireccion() {
        return direccion;
    }
/**
 * Metodo para establecer la dirección del lugar.
 * @param direccion La dirección del lugar
 */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
/**
 * Metodo para obtener la latitud del lugar
 * @return La latitud del lugar
 */
    public Float getLatitud() {
        return latitud;
    }
/**
 * Metodo para establecer la latitud del lugar
 * @param latitud La latitud del lugar
 */
    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }
/**
 * Metodo para establecer la longitud del lugar 
 * @return La longitud del lugar
 */
    public Float getLongitd() {
        return longitd;
    }
/**
 * Metodo para establecer la longitud del lugar
 * @param longitd La longitud del lugar
 */
    public void setLongitd(Float longitd) {
        this.longitd = longitd;
    }
 /**
     * Metodo hashCode que permite conocer un lugar
     *
     * @return El hashCode del lugar
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.idLugar);
        return hash;
    }
    /**
     * Metodo equals de la clase lugar
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
        final Lugar other = (Lugar) obj;
        if (!Objects.equals(this.idLugar, other.idLugar)) {
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
        return "Lugar{" + "idLugar=" + idLugar + ", nombre=" + nombre + ", direccion=" + direccion + ", latitud=" + latitud + ", longitd=" + longitd + '}';
    }

}
