package objetosNegocio;

public class Salida {

    int salida_id;
    int tren_id;
    int origen_id;
    int destino_id;
    String fecha;
    String hora;
    String precio_boleto_general;
    String precio_boleto_p_clase;
    short disponibilidad_a_primera_c;
    short disponibilidad_general;
    short disponibilidad_espera;

    public Salida() {
    }

    public Salida(int salida_id) {
        this.salida_id = salida_id;
    }

    public Salida(int salida_id, int tren_id, int origen_id, int destino_id, String fecha, String hora, String precio_boleto_general, String precio_boleto_p_clase, short disponibilidad_a_primera_c, short disponibilidad_general, short disponibilidad_espera) {
        this.salida_id = salida_id;
        this.tren_id = tren_id;
        this.origen_id = origen_id;
        this.destino_id = destino_id;
        this.fecha = fecha;
        this.hora = hora;
        this.precio_boleto_general = precio_boleto_general;
        this.precio_boleto_p_clase = precio_boleto_p_clase;
        this.disponibilidad_a_primera_c = disponibilidad_a_primera_c;
        this.disponibilidad_general = disponibilidad_general;
        this.disponibilidad_espera = disponibilidad_espera;
    }

    public Salida(int tren_id, int origen_id, int destino_id, String fecha, String hora, String precio_boleto_general, String precio_boleto_p_clase, short disponibilidad_a_primera_c, short disponibilidad_general, short disponibilidad_espera) {
        this.tren_id = tren_id;
        this.origen_id = origen_id;
        this.destino_id = destino_id;
        this.fecha = fecha;
        this.hora = hora;
        this.precio_boleto_general = precio_boleto_general;
        this.precio_boleto_p_clase = precio_boleto_p_clase;
        this.disponibilidad_a_primera_c = disponibilidad_a_primera_c;
        this.disponibilidad_general = disponibilidad_general;
        this.disponibilidad_espera = disponibilidad_espera;
    }

    public Salida(int salida_id, int tren_id, int origen_id, int destino_id, String fecha, String hora, String precio_boleto_general, String precio_boleto_p_clase) {
        this.salida_id = salida_id;
        this.tren_id = tren_id;
        this.origen_id = origen_id;
        this.destino_id = destino_id;
        this.fecha = fecha;
        this.hora = hora;
        this.precio_boleto_general = precio_boleto_general;
        this.precio_boleto_p_clase = precio_boleto_p_clase;
    }

    public Salida(int tren_id, int origen_id, int destino_id, String fecha, String hora, String precio_boleto_general, String precio_boleto_p_clase) {
        this.tren_id = tren_id;
        this.origen_id = origen_id;
        this.destino_id = destino_id;
        this.fecha = fecha;
        this.hora = hora;
        this.precio_boleto_general = precio_boleto_general;
        this.precio_boleto_p_clase = precio_boleto_p_clase;
    }



    public int getSalida_id() {
        return salida_id;
    }

    public void setSalida_id(int salida_id) {
        this.salida_id = salida_id;
    }

    public int getTren_id() {
        return tren_id;
    }

    public void setTren_id(int tren_id) {
        this.tren_id = tren_id;
    }

    public int getOrigen_id() {
        return origen_id;
    }

    public void setOrigen_id(int origen_id) {
        this.origen_id = origen_id;
    }

    public int getDestino_id() {
        return destino_id;
    }

    public void setDestino_id(int destino_id) {
        this.destino_id = destino_id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPrecio_boleto_general() {
        return precio_boleto_general;
    }

    public void setPrecio_boleto_general(String precio_boleto_general) {
        this.precio_boleto_general = precio_boleto_general;
    }

    public String getPrecio_boleto_p_clase() {
        return precio_boleto_p_clase;
    }

    public void setPrecio_boleto_p_clase(String precio_boleto_p_clase) {
        this.precio_boleto_p_clase = precio_boleto_p_clase;
    }

    public short getDisponibilidad_a_primera_c() {
        return disponibilidad_a_primera_c;
    }

    public void setDisponibilidad_a_primera_c(short disponibilidad_a_primera_c) {
        this.disponibilidad_a_primera_c = disponibilidad_a_primera_c;
    }

    public short getDisponibilidad_general() {
        return disponibilidad_general;
    }

    public void setDisponibilidad_general(short disponibilidad_general) {
        this.disponibilidad_general = disponibilidad_general;
    }

    public short getDisponibilidad_espera() {
        return disponibilidad_espera;
    }

    public void setDisponibilidad_espera(short disponibilidad_espera) {
        this.disponibilidad_espera = disponibilidad_espera;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.salida_id;
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
        final Salida other = (Salida) obj;
        if (this.salida_id != other.salida_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Salida{" + "salida_id=" + salida_id + ", tren_id=" + tren_id + ", origen_id=" + origen_id + ", destino_id=" + destino_id + ", fecha=" + fecha + ", hora=" + hora + ", precio_boleto_general=" + precio_boleto_general + ", precio_boleto_p_clase=" + precio_boleto_p_clase + ", disponibilidad_a_primera_c=" + disponibilidad_a_primera_c + ", disponibilidad_general=" + disponibilidad_general + ", disponibilidad_espera=" + disponibilidad_espera + '}';
    }

}
