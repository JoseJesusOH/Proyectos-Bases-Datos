
package objetosNegocio;

public class Lugar {

    private int lugar_id;
    private String nombre_estacion;
    private String estado;
    private String ciudad;

    public Lugar() {
    }

    public Lugar(int lugar_id) {
        this.lugar_id = lugar_id;
    }

    public Lugar(int lugar_id, String nombre_estacion, String estado, String ciudad) {
        this.lugar_id = lugar_id;
        this.nombre_estacion = nombre_estacion;
        this.estado = estado;
        this.ciudad = ciudad;
    }

    public Lugar(String nombre_estacion, String estado, String ciudad) {
        this.nombre_estacion = nombre_estacion;
        this.estado = estado;
        this.ciudad = ciudad;
    }

    public int getLugar_id() {
        return lugar_id;
    }

    public void setLugar_id(int lugar_id) {
        this.lugar_id = lugar_id;
    }

    public String getNombre_estacion() {
        return nombre_estacion;
    }

    public void setNombre_estacion(String nombre_estacion) {
        this.nombre_estacion = nombre_estacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.lugar_id;
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
        final Lugar other = (Lugar) obj;
        if (this.lugar_id != other.lugar_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LugarObjeto{" + "lugar_id=" + lugar_id + ", nombre_estacion=" + nombre_estacion + ", estado=" + estado + ", ciudad=" + ciudad + '}';
    }

}
