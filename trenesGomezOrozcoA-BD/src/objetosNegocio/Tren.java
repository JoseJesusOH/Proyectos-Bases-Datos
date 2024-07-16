package objetosNegocio;

public class Tren {

    int tren_id;
    String nomdre;
    String modelo;
    short año;
    short numero_pasajeros;
    short asientos_primera;
    String dias_operacion;

    public Tren() {
    }

    public Tren(int tren_id) {
        this.tren_id = tren_id;
    }

    public Tren(int tren_id, String nomdre, String modelo, short año, short numero_pasajeros,short asientos_primera, String dias_operacion) {
        this.tren_id = tren_id;
        this.nomdre = nomdre;
        this.modelo = modelo;
        this.año = año;
        this.numero_pasajeros = numero_pasajeros;
        this.asientos_primera = asientos_primera;
        this.dias_operacion = dias_operacion;
    }

    public Tren(String nomdre, String modelo, short año, short numero_pasajeros, short asientos_primera, String dias_operacion) {
        this.nomdre = nomdre;
        this.modelo = modelo;
        this.año = año;
        this.numero_pasajeros = numero_pasajeros;
        this.asientos_primera = asientos_primera;
        this.dias_operacion = dias_operacion;
    }

   
    public int getTren_id() {
        return tren_id;
    }

    public void setTren_id(int tren_id) {
        this.tren_id = tren_id;
    }

    public String getNomdre() {
        return nomdre;
    }

    public void setNomdre(String nomdre) {
        this.nomdre = nomdre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public short getAño() {
        return año;
    }

    public void setAño(short año) {
        this.año = año;
    }

    public short getNumero_pasajeros() {
        return numero_pasajeros;
    }

    public void setNumero_pasajeros(short numero_pasajeros) {
        this.numero_pasajeros = numero_pasajeros;
    }

    public short getAsientos_primera() {
        return asientos_primera;
    }

    public void setAsientos_primera(short asientos_primera) {
        this.asientos_primera = asientos_primera;
    }

    public String getDias_operacion() {
        return dias_operacion;
    }

    public void setDias_operacion(String dias_operacion) {
        this.dias_operacion = dias_operacion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.tren_id;
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
        final Tren other = (Tren) obj;
        if (this.tren_id != other.tren_id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TrenObjeto{" + "tren_id=" + tren_id + ", nomdre=" + nomdre + ", modelo=" + modelo + ", a\u00f1o=" + año + ", numero_pasajeros=" + numero_pasajeros + ", asientos_primera=" + asientos_primera + ", dias_operacion=" + dias_operacion + '}';
    }
}
