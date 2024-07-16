package entidades;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;
/**
 * Clase que representa una entidad de evento.
 * @author José Jesús Orozco Hernández ID; 00000229141
 */
public class Evento {
/**
 * ID del evento
 */
    private Integer idEvento;
    /**
     * Nombre del evento
     */
    private String nombre;
    /**
     * fecha del evento
     */
    private Date fechaHora;
    /**
     * Hora del evento
     */
    private Time hora;
    /**
     * Numero de asistentes
     */
    private Integer numeroAsistentes;
    /**
     * ID del cliente 
     */
    private Integer idCliente;
    /**
     * Nombre del cliente
     */
    private String nombreCliente;
    /**
     * ID del lugar
     */
    private Integer idLugar;
    /**
     * Nombre del lugar
     */
    private String nombreLugar;
    /**
     * Constructor por omision 
     */
    public Evento() {
    }
/**
 * Constructor que inicializa el atributo idEvento.
 * @param idEvento ID del evento
 */
    public Evento(Integer idEvento) {
        this.idEvento = idEvento;
    }
/**
 * Constructor que inicializa los atributos del evento, los cuales tienen mas semanjanza con su entidad.
 * @param idEvento ID del evento
 * @param nombre Nombre del evento
 * @param fechaHora Fecha del evento
 * @param hora Hora del evento
 * @param numeroAsistentes Numero de asistentes
 * @param idCliente ID del cliente
 * @param idLugar ID del lugar
 */
    public Evento(Integer idEvento, String nombre, Date fechaHora, Time hora, Integer numeroAsistentes, Integer idCliente, Integer idLugar) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.fechaHora = fechaHora;
        this.hora = hora;
        this.numeroAsistentes = numeroAsistentes;
        this.idCliente = idCliente;
        this.idLugar = idLugar;
    }
/**
 * Constructor 2 que inicializa los atributos del evento, los cuales tienen mas semanjanza con su entidad.
 * @param nombre Nombre del evento
 * @param fechaHora Fecha del evento
 * @param hora Hora del evento
 * @param numeroAsistentes Numero de asistentes
 * @param idCliente ID del cliente
 * @param idLugar ID del lugar
 */
    public Evento(String nombre, Date fechaHora, Time hora, Integer numeroAsistentes, Integer idCliente, Integer idLugar) {
        this.nombre = nombre;
        this.fechaHora = fechaHora;
        this.hora = hora;
        this.numeroAsistentes = numeroAsistentes;
        this.idCliente = idCliente;
        this.idLugar = idLugar;
    }
/**
 * Construcutor que inicializa todos los atributos del evento.
 * @param idEvento ID del evento
 * @param nombre Nombre del evento
 * @param fechaHora Fecha del evento
 * @param hora Hora del evento
 * @param numeroAsistentes Numero de asistentes
 * @param idCliente ID del cliente 
 * @param nombreCliente Nombre del cliente
 * @param idLugar ID del lugar
 * @param nombreLugar Nombre del lugar
 */
    public Evento(Integer idEvento, String nombre, Date fechaHora, Time hora, Integer numeroAsistentes, Integer idCliente, String nombreCliente, Integer idLugar, String nombreLugar) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.fechaHora = fechaHora;
        this.hora = hora;
        this.numeroAsistentes = numeroAsistentes;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.idLugar = idLugar;
        this.nombreLugar = nombreLugar;
    }
/**
 * Metodo que obtiene el nombre del cliente
 * @return El nombre del cliente
 */
    public String getNombreCliente() {
        return nombreCliente;
    }
/**
 * Metodo para establecer el nombre del cliente
 * @param nombreCliente E nombre del cliente
 */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
/**
 * Metodo para obtener el nombre del lugar
 * @return El nombre del lugar
 */
    public String getNombreLugar() {
        return nombreLugar;
    }
/**
 * Metodo para establecer el nombre del lugar
 * @param nombreLugar 
 */
    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }
/**
 * Metodo para obtener el id del evento
 * @return El ID del evento
 */
    public Integer getIdEvento() {
        return idEvento;
    }
/**
 * Metodo para establecer el ID del evento
 * @param idEvento El ID del evento
 */
    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }
/**
 * Metodo para obtener el nombre del evento
 * @return El nombre del evento
 */
    public String getNombre() {
        return nombre;
    }
/**
 * Metodo para establecer el nombre del evento
 * @param nombre El nombre del evento
 */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
/**
 * Metodo para obtener la fecha del evento
 * @return La fecha del evento
 */
    public Date getFechaHora() {
        return fechaHora;
    }
/**
 * Metodo para establecer la fecha del evento
 * @param fechaHora La fecha del evento
 */
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
/**
 * Metodo para obtener la hora del evento.
 * @return La hora del evento
 */
    public Time getHora() {
        return hora;
    }
/**
 * Metodo para establecer la hora del evento
 * @param hora La hora del evento
 */
    public void setHora(Time hora) {
        this.hora = hora;
    }
/**
 * Metodo para obtener el numero de asistentes del evento
 * @return El numero de asistentes de un evento
 */
    public Integer getNumeroAsistentes() {
        return numeroAsistentes;
    }
/**
 * Metodo para establecer el numero de asistentes del evento
 * @param numeroAsistentes El numero de asitentes del evento
 */
    public void setNumeroAsistentes(Integer numeroAsistentes) {
        this.numeroAsistentes = numeroAsistentes;
    }
/**
 * Metodo para obtener el id del cliente.
 * @return El ID del cliente
 */
    public Integer getIdCliente() {
        return idCliente;
    }
/**
 * Metodo para establecer el ID del cliente
 * @param idCliente El ID del cliente
 */
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
/**
 * Metodo para obtener el ID del lugar
 * @return El ID del lugar
 */
    public Integer getIdLugar() {
        return idLugar;
    }
/**
 * Metodo para establecer el ID del lugar
 * @param idLugar El ID del lugar
 */
    public void setIdLugar(Integer idLugar) {
        this.idLugar = idLugar;
    }

   /**
    * Metodo hashCode para identificar el evento
    * @return El hashCode del evento
    */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.idEvento);
        return hash;
    }

    /**
     * Metodo para comparar eventos y determinar si son iguales
     * @param obj El objeto a comparar
     * @return true si son el mismo, false en caso contrario
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
        final Evento other = (Evento) obj;
        if (!Objects.equals(this.idEvento, other.idEvento)) {
            return false;
        }
        return true;
    }

    /**
     * Metodo que muestra una cadena con todos los atributos del evento
     * @return Cadena con todos los atributos del evento
     */
    @Override
    public String toString() {
        return "Evento{" + "idEvento=" + idEvento + ", nombre=" + nombre + ", fechaHora=" + fechaHora + ", numeroAsistentes=" + numeroAsistentes + ", idCliente=" + idCliente + ", idLugar=" + idLugar + '}';
    }

}
