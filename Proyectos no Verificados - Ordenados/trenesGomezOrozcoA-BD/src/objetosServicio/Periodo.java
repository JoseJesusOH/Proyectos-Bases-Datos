package objetosServicio;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author josej
 */
public class Periodo {

    /**
     * Atributos de la clase periodo dos objetos del tipo fecha
     */
    private Fecha desde;
    private Fecha hasta;

    /**
     * Constructor de la clase en el cual establecemos el objeto del tipo fecha
     * desde,hasta con el valor de los atributos de esta clase.
     *
     * @param desde Obeto del tipo fecha que establece el inicio
     * @param hasta Objeto del tipo fecha que establece el fin
     */
    public Periodo(Fecha desde, Fecha hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }

    /**
     * Metodo para obtner el objeto desde del tipo fecha
     *
     * @return El objeto desde
     */
    public Fecha getDesde() {
        return desde;
    }

    /**
     * Metodo para establecer el atributo de la clase con su parametro
     *
     * @param desde Objeto del tipo desde
     */
    public void setDesde(Fecha desde) {
        this.desde = desde;
    }

    /**
     * Metodo para obtener el objeto hasta
     *
     * @return El objeto hasta
     */
    public Fecha getHasta() {
        return hasta;
    }

    /**
     * Metodo para establecer el atributo hasta
     *
     * @param hasta EL objeto hasta del tipo fecha
     */
    public void setHasta(Fecha hasta) {
        this.hasta = hasta;
    }

    /**
     * Metodo para obtener si una fecha se encuentra dentro del rango
     * establecido
     *
     * @param fecha Fecha de prueba
     * @return
     */
    public boolean contiene(Fecha fecha) {
//        int diaD=getDesde().getDay();
//        int anhoD = getDesde().getYear();
//        int mesD=getDesde().getMonth();
//        
//        
//        int anhoH=getHasta().getYear();
//        int mesH=getHasta().getMonth();
//        int diaH=getHasta().getDay();
//                
//                
//        int fDia=fecha.getDay();
//        int fMes=fecha.getMonth();
//        int fAnho=fecha.getYear();
        if (desde.getDay() == hasta.getDay() && desde.getMonth() == hasta.getMonth() && desde.getYear() == hasta.getYear()) {
            if (desde.getDay() == fecha.getDay() && desde.getMonth() == fecha.getMonth() && desde.getYear() == fecha.getYear()) {
                return true;
            } else {
                return false;
            }
        }
        if (desde.getDay() == fecha.getDay() && desde.getMonth() == fecha.getMonth() && desde.getYear() == fecha.getYear()) {
            return true;
        }
         if (hasta.getDay() == fecha.getDay() && hasta.getMonth() == fecha.getMonth() && hasta.getYear() == fecha.getYear()) {
            return true;
        }
        
//        if(diaD=diaH){
//        }
//        (validarHora(campoTextoHora.getText()) < 0 || validarHora(campoTextoHora.getText()) > 24
        if ((fecha.before(hasta) && fecha.after(desde))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    /**
     * Devuelve los valores de desde y hasta en un formato DD/MM/AAAA a
     * DD/MM/AAAA
     */
    public String toString() {
        return desde.toString() + " a " + hasta.toString();
    }
}
