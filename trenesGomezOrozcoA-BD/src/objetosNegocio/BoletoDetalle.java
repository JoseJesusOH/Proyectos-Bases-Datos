/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosNegocio;

/**
 *
 * @author josej
 */
public class BoletoDetalle {
    int boleto_id;
    int pasajero_id;
    String nombre_pasajero;
    int salida_id;
    int origen_id;
    String origen_nombre;
    int destino_id;
    String destino_nombre;
    int tren_id;
    String tren_nombre;
    String tipo_boleto;
    String fecha_boleto;
    String estatus;

    public BoletoDetalle() {
    }

    public BoletoDetalle(int boleto_id) {
        this.boleto_id = boleto_id;
    }

    public BoletoDetalle(int pasajero_id, int salida_id, String tipo_boleto, String fecha_boleto, String estatus) {
        this.pasajero_id = pasajero_id;
        this.salida_id = salida_id;
        this.tipo_boleto = tipo_boleto;
        this.fecha_boleto = fecha_boleto;
        this.estatus = estatus;
    }

    public BoletoDetalle(int pasajero_id, String nombre_pasajero, int salida_id, int origen_id, String origen_nombre, int destino_id, String destino_nombre, int tren_id, String tren_nombre, String tipo_boleto, String fecha_boleto, String estatus) {
        this.pasajero_id = pasajero_id;
        this.nombre_pasajero = nombre_pasajero;
        this.salida_id = salida_id;
        this.origen_id = origen_id;
        this.origen_nombre = origen_nombre;
        this.destino_id = destino_id;
        this.destino_nombre = destino_nombre;
        this.tren_id = tren_id;
        this.tren_nombre = tren_nombre;
        this.tipo_boleto = tipo_boleto;
        this.fecha_boleto = fecha_boleto;
        this.estatus = estatus;
    }
    public BoletoDetalle(int boleto_id,int salida_id,int pasajero_id, String tipo_boleto,String estatus, String fecha_boleto ) {
        this.boleto_id=boleto_id;
        this.pasajero_id = pasajero_id;
        this.salida_id = salida_id;
        this.tipo_boleto = tipo_boleto;
        this.fecha_boleto = fecha_boleto;
        this.estatus = estatus;
    }
    public int getBoleto_id() {
        return boleto_id;
    }

    public void setBoleto_id(int boleto_id) {
        this.boleto_id = boleto_id;
    }

    public int getPasajero_id() {
        return pasajero_id;
    }

    public void setPasajero_id(int pasajero_id) {
        this.pasajero_id = pasajero_id;
    }

    public String getNombre_pasajero() {
        return nombre_pasajero;
    }

    public void setNombre_pasajero(String nombre_pasajero) {
        this.nombre_pasajero = nombre_pasajero;
    }

    public int getSalida_id() {
        return salida_id;
    }

    public void setSalida_id(int salida_id) {
        this.salida_id = salida_id;
    }

    public int getOrigen_id() {
        return origen_id;
    }

    public void setOrigen_id(int origen_id) {
        this.origen_id = origen_id;
    }

    public String getOrigen_nombre() {
        return origen_nombre;
    }

    public void setOrigen_nombre(String origen_nombre) {
        this.origen_nombre = origen_nombre;
    }

    public int getDestino_id() {
        return destino_id;
    }

    public void setDestino_id(int destino_id) {
        this.destino_id = destino_id;
    }

    public String getDestino_nombre() {
        return destino_nombre;
    }

    public void setDestino_nombre(String destino_nombre) {
        this.destino_nombre = destino_nombre;
    }

    public int getTren_id() {
        return tren_id;
    }

    public void setTren_id(int tren_id) {
        this.tren_id = tren_id;
    }

    public String getTren_nombre() {
        return tren_nombre;
    }

    public void setTren_nombre(String tren_nombre) {
        this.tren_nombre = tren_nombre;
    }

    public String getTipo_boleto() {
        return tipo_boleto;
    }

    public void setTipo_boleto(String tipo_boleto) {
        this.tipo_boleto = tipo_boleto;
    }

    public String getFecha_boleto() {
        return fecha_boleto;
    }

    public void setFecha_boleto(String fecha_boleto) {
        this.fecha_boleto = fecha_boleto;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.boleto_id;
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
        final BoletoDetalle other = (BoletoDetalle) obj;
        if (this.boleto_id != other.boleto_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BoletoDetalle{" + "boleto_id=" + boleto_id + ", pasajero_id=" + pasajero_id + ", nombre_pasajero=" + nombre_pasajero + ", salida_id=" + salida_id + ", origen_id=" + origen_id + ", origen_nombre=" + origen_nombre + ", destino_id=" + destino_id + ", destino_nombre=" + destino_nombre + ", tren_id=" + tren_id + ", tren_nombre=" + tren_nombre + ", tipo_boleto=" + tipo_boleto + ", fecha_boleto=" + fecha_boleto + ", estatus=" + estatus + '}';
    }

}
