package objetosNegocio;

public class Pasajero {

    int pasajero_id;
    String nombre;
    String apellido_paterno;
    String apellido_materno;
    String fecha_nacimiento;
    char genero;
    String telefono;
    String direccion;

    public Pasajero() {
    }

    public Pasajero(int pasajero_id) {
        this.pasajero_id = pasajero_id;
    }

    public Pasajero(int pasajero_id, String nombre, String apellido_paterno, String apellido_materno, String fecha_nacimiento, char genero, String telefono, String direccion) {
        this.pasajero_id = pasajero_id;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.fecha_nacimiento = fecha_nacimiento;
        this.genero = genero;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Pasajero(String nombre, String apellido_paterno, String apellido_materno, String fecha_nacimiento, char genero, String telefono, String direccion) {
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.fecha_nacimiento = fecha_nacimiento;
        this.genero = genero;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public int getPasajero_id() {
        return pasajero_id;
    }

    public void setPasajero_id(int pasajero_id) {
        this.pasajero_id = pasajero_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.pasajero_id;
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
        final Pasajero other = (Pasajero) obj;
        if (this.pasajero_id != other.pasajero_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PasajeroObjeto{" + "pasajero_id=" + pasajero_id + ", nombre=" + nombre + ", apellido_paterno=" + apellido_paterno + ", apellido_materno=" + apellido_materno + ", fecha_nacimiento=" + fecha_nacimiento + ", genero=" + genero + ", telefono=" + telefono + ", direccion=" + direccion + '}';
    }
}
