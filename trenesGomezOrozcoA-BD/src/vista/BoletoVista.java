package vista;

import com.sun.org.apache.bcel.internal.generic.F2D;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import objetosNegocio.BoletoDetalle;
import objetosNegocio.Lugar;
import objetosNegocio.Pasajero;
import objetosNegocio.Salida;
import objetosNegocio.Tren;
import objetosServicio.Fecha;
import persistencia.BoletoPersistencia;
import persistencia.LugarPersistencia;
import persistencia.PasajeroPersistencia;
import persistencia.SalidaPersistencia;
import persistencia.TrenPersistencia;
import static vista.PasajeroVista.validaEntero;
import static vista.PasajeroVista.validaFecha;
import static vista.PasajeroVista.validarDias;

public class BoletoVista {

    private String[] arregloDias = {"LU", "MA", "MI", "JU", "VI", "SA", "DO"};
    private boolean boleto = true;
    private BoletoPersistencia boletoPersistencia;
    private TrenPersistencia trenPersistencia;
    private SalidaPersistencia salidaPersistencia;
    private PasajeroPersistencia pasajeroPersistencia;
    private LugarPersistencia lugarPersistencia;
    private Auxiliares as = new Auxiliares();

    public BoletoVista() {
        boletoPersistencia = new BoletoPersistencia();
        trenPersistencia = new TrenPersistencia();
        salidaPersistencia = new SalidaPersistencia();
        pasajeroPersistencia = new PasajeroPersistencia();
        lugarPersistencia = new LugarPersistencia();
    }

    public void menuBoleto() {
        System.out.println("");
        Scanner teclado = new Scanner(System.in);
        while (boleto) {
            System.out.println("\033[0;34m---------------------------------------------");
            System.out.println("\033[0;34m|♥♦♣♠         Menu Boleto             ♥♦♣♠|");
            System.out.println("\033[0;34m---------------------------------------------");
            System.out.println("\033[0;34m|     Opciones           |      Tecla        |");
            System.out.println("\033[0;34m----------------------------------------------");
            System.out.println("\033[0;34m| 1. Reservar  Boleto    |          1        | ");
            System.out.println("\033[0;34m| 2. Cancelar  Boleto    |          2        | ");
            System.out.println("\033[0;34m| 3. Boletos Cancelados  |          3        | ");
            System.out.println("\033[0;34m| 4. Boletos Espera      |          4        | ");
            System.out.println("\033[0;34m| 5. Boletos Vendidos    |          5        | ");
            System.out.println("\033[0;34m| 6. Salir               |          0        | ");
            System.out.println("\033[0;34m----------------------------------------------");
            System.out.println("");
            Integer auxiliar = as.opcionesMenuRangos(5);
            switch (auxiliar) {
                case 1:
                    opcion1();
                    break;
                case 2:
                    opcion2();
                    break;
                case 3:
                    opcion3();
                    break;
                case 4:
                    opcion4();
                    break;
                case 5:
                    opcion5();
                    break;
                case 0:
                    boleto = false;
                    break;
                default:
                    break;
            }
        }
        teclado.nextLine();
        System.out.println("");
    }

    public void opcion1() {
        System.out.println("");
        Scanner teclado = new Scanner(System.in);
        boolean opcion1 = true;
        //Codigo extra para pedir la Fecha y hora actual antes de comenzar;
        while (opcion1) {
            String fechaActual = "";
            Fecha actual = null;
            do {
                System.out.println("");
                System.out.println("Ingrese la fecha actual antes de comenzar DD/MM/AAAA (10 caracteres).");
                String cadenaD = teclado.nextLine();
                if (cadenaD.length() != 0 && cadenaD.length() <= 10) {
                    if (as.validaFecha(cadenaD)) {
                        String fecha = as.validarDias(cadenaD);
                        if (fecha == null) {
                            System.out.println("La fecha contiene dias invalidos,verifique el mes o dia");
                        } else {
                            actual = new Fecha(cadenaD);
                            fechaActual = fecha;
                            break;
                        }
                    } else {
                        System.out.println("La fecha no concuerda con el formato");
                    }
                } else {
                    if (cadenaD.length() == 0) {
                        System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                    } else {
                        System.out.println("El maximo de caracteres permitos es de 10");
                    }
                }
            } while (true);
            String hora = "";
            teclado.nextLine();
            do {
                System.out.println("Porfavor ingrese la hora de la salida HH:MM Formato 24(5 caracteres)");
                String cadenaD = teclado.nextLine();
                if (cadenaD.length() != 0 && cadenaD.length() <= 5) {
                    if (as.validarHora(cadenaD)) {
                        hora = cadenaD;
                        break;
                    } else {
                        System.out.println("Porfavor digite solo numeros, no letras");
                    }
                } else {
                    if (cadenaD.length() == 0) {
                        System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                    } else {
                        System.out.println("El maximo de caracteres permitos es de 5");
                    }
                }
            } while (true);
            String filtro = String.format("SELECT salida_id,tren_id,origen_id,destino_id, (CONVERT(date,fecha_y_hora)) AS Fecha, CONCAT(DATEPART(HOUR,fecha_y_hora),':',DATEPART(MINUTE,fecha_y_hora)) AS Hora ,precio_boleto_general,precio_boleto_p_clase,disponibilidad_general, disponibilidad_a_primera_c,disponibilidad_espera FROM Salidas WHERE YEAR(fecha_y_hora)=%s AND MONTH(fecha_y_hora)=%s AND (%s>=(DAY(fecha_y_hora)-3) AND %s<=(DAY(fecha_y_hora))) AND CONCAT(DATEPART(HOUR,fecha_y_hora),':',DATEPART(MINUTE,fecha_y_hora))>'%s' AND (disponibilidad_general>0 OR disponibilidad_a_primera_c>0 OR disponibilidad_espera>0)", actual.getYear(), actual.getMonth(), actual.getDay(), actual.getDay(), hora);
            ArrayList<Salida> listaSalida = salidaPersistencia.consultarTodosFiltro(filtro);
            if (listaSalida.size() > 0) {
                System.out.println("");
                System.out.println("---------------------------------------------");
                System.out.println("|             Menu Reservar                  |");
                System.out.println("----------------------------------------------");
                System.out.println("|♥♦♣♠       Buscar salidas            ♥♦♣♠|");
                System.out.println("----------------------------------------------");
                System.out.println("|     Opciones           |      Tecla        |");
                System.out.println("----------------------------------------------");
                System.out.println("| 1. Todas               |          1        | ");
                System.out.println("| 2. Por Fecha           |          2        | ");
                System.out.println("| 3. Por Hora            |          3        | ");
                System.out.println("| 4. Origen a Destino    |          4        | ");
                System.out.println("----------------------------------------------");
                System.out.println("");
                Integer auxiliar = null;
                while (true) {
                    try {
                        System.out.println("Ingrese la opcion");
                        int n = teclado.nextInt();
                        if (n > 4 || n <= 0) {
                            System.out.println("El digito ingresado no corresponde a una de las "
                                    + "opciones del menu, ");
                        } else {
                            auxiliar = n;
                            break;
                        }
                    } catch (Exception e) {
                        teclado.nextLine();
                        System.out.println("Porfavor ingrese digitos");
                    }
                }
                switch (auxiliar) {
                    case 1:
                        System.out.println("");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|                                                      Tabla de Salidas                                                              |");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |Tren |Origen |Destino |Fecha       |Hora    |Precio B.General |Precio BPrimClas |Dispo. General|Dospo. Primera |Dispo. Espera |");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        for (int i = 0; i < listaSalida.size(); i++) {
                            System.out.printf("|%-4s |%-4s |%-6s |%-7s |%-11s |%-4s |%-16s |%-16s |%-13s |%-13s |%-13s |", listaSalida.get(i).getSalida_id(), listaSalida.get(i).getTren_id(),
                                    listaSalida.get(i).getOrigen_id(), listaSalida.get(i).getDestino_id(), listaSalida.get(i).getFecha(),
                                    (listaSalida.get(i).getHora().length() == 6 ? listaSalida.get(i).getHora() + "0" : listaSalida.get(i).getHora()), listaSalida.get(i).getPrecio_boleto_general(), listaSalida.get(i).getPrecio_boleto_p_clase(),
                                    listaSalida.get(i).getDisponibilidad_general(), listaSalida.get(i).getDisponibilidad_a_primera_c(), listaSalida.get(i).getDisponibilidad_espera());
                            System.out.println("");
                        }
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        break;
                    case 2:
                        String fechaFiltro = "";
                        do {
                            System.out.println("");
                            System.out.println("Ingrese la fecha actual por la que desea realizar el filtro DD/MM/AAAA (10 caracteres).");
                            String cadenaD = teclado.nextLine();
                            if (cadenaD.length() != 0 && cadenaD.length() <= 10) {
                                if (validaFecha(cadenaD)) {
                                    String fecha = validarDias(cadenaD);
                                    if (fecha == null) {
                                        System.out.println("La fecha contiene dias invalidos,verifique el mes o dia");
                                    } else {
                                        fechaFiltro = fecha;
                                        break;
                                    }
                                } else {
                                    System.out.println("La fecha no concuerda con el formato");
                                }
                            } else {
                                if (cadenaD.length() == 0) {
                                    System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                } else {
                                    System.out.println("El maximo de caracteres permitos es de 10");
                                }
                            }
                        } while (true);
                        filtro = String.format("SELECT salida_id,tren_id,origen_id,destino_id, (CONVERT(date,fecha_y_hora)) AS Fecha, CONCAT(DATEPART(HOUR,fecha_y_hora),':',DATEPART(MINUTE,fecha_y_hora)) AS Hora ,precio_boleto_general,precio_boleto_p_clase,disponibilidad_general, disponibilidad_a_primera_c,disponibilidad_espera FROM Salidas WHERE YEAR(fecha_y_hora)=%s AND MONTH(fecha_y_hora)=%s AND (%s>=(DAY(fecha_y_hora)-3) AND %s<=(DAY(fecha_y_hora))) AND CONCAT(DATEPART(HOUR,fecha_y_hora),':',DATEPART(MINUTE,fecha_y_hora))>'%s' AND (disponibilidad_general>0 OR disponibilidad_a_primera_c>0 OR disponibilidad_espera>0) AND CONVERT(date,fecha_y_hora)='%s'", actual.getYear(), actual.getMonth(), actual.getDay(), actual.getDay(), hora, fechaFiltro);
                        listaSalida = salidaPersistencia.consultarTodosFiltro(filtro);
                        System.out.println("");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|                                                      Tabla de Salidas                                                              |");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |Tren |Origen |Destino |Fecha       |Hora    |Precio B.General |Precio BPrimClas |Dispo. General|Dospo. Primera |Dispo. Espera |");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        for (int i = 0; i < listaSalida.size(); i++) {
                            System.out.printf("|%-4s |%-4s |%-6s |%-7s |%-11s |%-4s |%-16s |%-16s |%-13s |%-13s |%-13s |", listaSalida.get(i).getSalida_id(), listaSalida.get(i).getTren_id(),
                                    listaSalida.get(i).getOrigen_id(), listaSalida.get(i).getDestino_id(), listaSalida.get(i).getFecha(),
                                    (listaSalida.get(i).getHora().length() == 6 ? listaSalida.get(i).getHora() + "0" : listaSalida.get(i).getHora()), listaSalida.get(i).getPrecio_boleto_general(), listaSalida.get(i).getPrecio_boleto_p_clase(),
                                    listaSalida.get(i).getDisponibilidad_general(), listaSalida.get(i).getDisponibilidad_a_primera_c(), listaSalida.get(i).getDisponibilidad_espera());
                            System.out.println("");
                        }
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        break;
                    case 3:
                        String horaFitro = "";
                        do {
                            System.out.println("Porfavor ingrese la hora de la salida HH:MM Formato 24(5 caracteres), por la que desea realizar el filtro");
                            String cadenaD = teclado.nextLine();
                            if (cadenaD.length() != 0 && cadenaD.length() <= 5) {
                                if (as.validarHora(cadenaD)) {
                                    horaFitro = cadenaD;
                                    break;
                                } else {
                                    System.out.println("Porfavor digite solo numeros, no letras");
                                }
                            } else {
                                if (cadenaD.length() == 0) {
                                    System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                } else {
                                    System.out.println("El maximo de caracteres permitos es de 5");
                                }
                            }
                        } while (true);
                        filtro = String.format("SELECT salida_id,tren_id,origen_id,destino_id, (CONVERT(date,fecha_y_hora)) AS Fecha, CONCAT(DATEPART(HOUR,fecha_y_hora),':',DATEPART(MINUTE,fecha_y_hora)) AS Hora ,precio_boleto_general,precio_boleto_p_clase,disponibilidad_general, disponibilidad_a_primera_c,disponibilidad_espera FROM Salidas WHERE YEAR(fecha_y_hora)=%s AND MONTH(fecha_y_hora)=%s AND (%s>=(DAY(fecha_y_hora)-3) AND %s<=(DAY(fecha_y_hora))) AND CONCAT(DATEPART(HOUR,fecha_y_hora),':',DATEPART(MINUTE,fecha_y_hora))>'%s' AND (disponibilidad_general>0 OR disponibilidad_a_primera_c>0 OR disponibilidad_espera>0) AND CONCAT(DATEPART(HOUR,fecha_y_hora),':',DATEPART(MINUTE,fecha_y_hora))='%s' ", actual.getYear(), actual.getMonth(), actual.getDay(), actual.getDay(), hora, horaFitro);
                        listaSalida = salidaPersistencia.consultarTodosFiltro(filtro);
                        System.out.println("");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|                                                      Tabla de Salidas                                                              |");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |Tren |Origen |Destino |Fecha       |Hora    |Precio B.General |Precio BPrimClas |Dispo. General|Dospo. Primera |Dispo. Espera |");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        for (int i = 0; i < listaSalida.size(); i++) {
                            System.out.printf("|%-4s |%-4s |%-6s |%-7s |%-11s |%-4s |%-16s |%-16s |%-13s |%-13s |%-13s |", listaSalida.get(i).getSalida_id(), listaSalida.get(i).getTren_id(),
                                    listaSalida.get(i).getOrigen_id(), listaSalida.get(i).getDestino_id(), listaSalida.get(i).getFecha(),
                                    (listaSalida.get(i).getHora().length() == 6 ? listaSalida.get(i).getHora() + "0" : listaSalida.get(i).getHora()), listaSalida.get(i).getPrecio_boleto_general(), listaSalida.get(i).getPrecio_boleto_p_clase(),
                                    listaSalida.get(i).getDisponibilidad_general(), listaSalida.get(i).getDisponibilidad_a_primera_c(), listaSalida.get(i).getDisponibilidad_espera());
                            System.out.println("");
                        }
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        break;
                    case 4:
                        ArrayList<Lugar> listaLugar = lugarPersistencia.consultarTodos();
                        System.out.println("");
                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("|                            Tabla de Lugares                           |");
                        System.out.println("-------------------------------------------------------------------------");
                        System.out.println("|ID   |Nombre Estacion      |Estado               |Ciudad               |");
                        System.out.println("-------------------------------------------------------------------------");
                        for (int i = 0; i < listaLugar.size(); i++) {
                            System.out.printf("|%-4s |%-20s |%-20s |%-20s |", listaLugar.get(i).getLugar_id(), listaLugar.get(i).getNombre_estacion(), listaLugar.get(i).getEstado(), listaLugar.get(i).getCiudad());
                            System.out.println("");
                        }
                        System.out.println("-------------------------------------------------------------------------");
                        System.out.println("");
                        boolean variableSalidaLugares = true;
                        Integer idOrigen = null;
                        Integer idDestino = null;
                        do {
                            do {
                                try {
                                    System.out.println("Ingrese el id de la salida para el origen");
                                    int n = teclado.nextInt();
                                    if (listaLugar.indexOf(new Lugar(n)) < 0) {
                                        System.out.println("El id ingresado no corresponde con alguno de los lugares en la base de datos ");
                                        System.out.println("Estos son los id de los lugares actuales");
                                        for (int i = 0; i < listaLugar.size(); i++) {
                                            System.out.print(listaLugar.get(i).getLugar_id() + ",");
                                        }
                                    } else {
                                        idOrigen = listaLugar.get(listaLugar.indexOf(new Lugar(n))).getLugar_id();
                                        break;
                                    }
                                } catch (Exception e) {
                                    teclado.nextLine();
                                    System.out.println("Porfavor ingrese digitos");
                                }
                            } while (true);
                            do {
                                try {
                                    System.out.println("Ingrese el id de la salida para el destino");
                                    int n = teclado.nextInt();
                                    if (listaLugar.indexOf(new Lugar(n)) < 0) {
                                        System.out.println("El id ingresado no corresponde con alguno de los lugares en la base de datos ");
                                        System.out.println("Estos son los id de los lugares actuales");
                                        for (int i = 0; i < listaLugar.size(); i++) {
                                            System.out.print(listaLugar.get(i).getLugar_id() + ",");
                                        }
                                    } else {
                                        idDestino = listaLugar.get(listaLugar.indexOf(new Lugar(n))).getLugar_id();
                                        break;
                                    }
                                } catch (Exception e) {
                                    teclado.nextLine();
                                    System.out.println("Porfavor ingrese digitos");
                                }
                            } while (true);
                            if (idOrigen != idDestino) {
                                variableSalidaLugares = false;
                            } else {
                                System.out.println("Para ingresar una salida de un tren, el id de origen y destino no deben de ser el mismo");
                                System.out.println("Verifique su marcacion");
                            }
                        } while (variableSalidaLugares);
                        filtro = String.format("SELECT salida_id,tren_id,origen_id,destino_id, (CONVERT(date,fecha_y_hora)) AS Fecha, CONCAT(DATEPART(HOUR,fecha_y_hora),':',DATEPART(MINUTE,fecha_y_hora)) AS Hora ,precio_boleto_general,precio_boleto_p_clase,disponibilidad_general, disponibilidad_a_primera_c,disponibilidad_espera FROM Salidas WHERE YEAR(fecha_y_hora)=%s AND MONTH(fecha_y_hora)=%s AND (%s>=(DAY(fecha_y_hora)-3) AND %s<=(DAY(fecha_y_hora))) AND CONCAT(DATEPART(HOUR,fecha_y_hora),':',DATEPART(MINUTE,fecha_y_hora))>'%s' AND (disponibilidad_general>0 OR disponibilidad_a_primera_c>0 OR disponibilidad_espera>0) AND origen_id=%s AND destino_id=%s", actual.getYear(), actual.getMonth(), actual.getDay(), actual.getDay(), hora, idOrigen, idDestino);
                        listaSalida = salidaPersistencia.consultarTodosFiltro(filtro);
                        System.out.println("");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|                                                      Tabla de Salidas                                                              |");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |Tren |Origen |Destino |Fecha       |Hora    |Precio B.General |Precio BPrimClas |Dispo. General|Dospo. Primera |Dispo. Espera |");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        for (int i = 0; i < listaSalida.size(); i++) {
                            System.out.printf("|%-4s |%-4s |%-6s |%-7s |%-11s |%-4s |%-16s |%-16s |%-13s |%-13s |%-13s |", listaSalida.get(i).getSalida_id(), listaSalida.get(i).getTren_id(),
                                    listaSalida.get(i).getOrigen_id(), listaSalida.get(i).getDestino_id(), listaSalida.get(i).getFecha(),
                                    (listaSalida.get(i).getHora().length() == 6 ? listaSalida.get(i).getHora() + "0" : listaSalida.get(i).getHora()), listaSalida.get(i).getPrecio_boleto_general(), listaSalida.get(i).getPrecio_boleto_p_clase(),
                                    listaSalida.get(i).getDisponibilidad_general(), listaSalida.get(i).getDisponibilidad_a_primera_c(), listaSalida.get(i).getDisponibilidad_espera());
                            System.out.println("");
                        }
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        break;
                    case 0:
                        break;
                    default:
                        break;
                }
                Integer auxiliarD = null;
                teclado.nextLine();
                do {
                    try {
                        System.out.println("Ingrese el id del salidas para reservar el boleto");
                        int n = teclado.nextInt();
                        auxiliarD = n;
                    } catch (Exception e) {
                        teclado.nextLine();
                        System.out.println("Porfavor solo ingrese digitos");
                    }
                    if (auxiliarD != null) {
                        int indice = listaSalida.indexOf(new Salida(auxiliarD));
                        if (indice >= 0) {
                            teclado.nextLine();
                            Salida s = listaSalida.get(indice);
                            Integer auxilarTipo = null;
                            do {
                                try {
                                    System.out.println("Ingrese el tipo de boleto a reservar 1=Tipo general Precio=" + s.getPrecio_boleto_general());
                                    System.out.println("o 2=Tipo Primera Clase Precio=" + s.getPrecio_boleto_p_clase());
                                    int n = teclado.nextInt();
                                    auxilarTipo = n;
                                } catch (Exception e) {
                                    teclado.nextLine();
                                    System.out.println("Porfavor solo ingrese digitos");
                                }
                                if (auxilarTipo == 1 || auxilarTipo == 2) {
                                    break;
                                } else {
                                    System.out.println("Opcion no valida");
                                }
                            } while (true);
                            Pasajero p = null;
                            Pasajero pg = null;
                            Integer opcion = null;
                            teclado.nextLine();
                            System.out.println("");
                            do {
                                System.out.println("¿Desea ingresar los datos del pasajero o ya tiene su id, ingrese Y pos si ya lo tiene"
                                        + "o ingrese n, por si no para agregar un pasajero?");
                                String cadenaD = teclado.nextLine();
                                if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                    if (cadenaD.equalsIgnoreCase("Y")) {
                                        teclado.nextLine();
                                        do {
                                            try {
                                                System.out.println("Ingrese el id del pasajero para reservarle un boleto");
                                                int n = teclado.nextInt();
                                                auxiliar = n;
                                            } catch (Exception e) {
                                                teclado.nextLine();
                                                System.out.println("Porfavor solo ingrese digitos");
                                            }
                                            if (auxiliar != -1) {
                                                p = pasajeroPersistencia.obtenerPasajero(auxiliar);
                                                if (p != null) {
                                                    break;
                                                } else {
                                                    System.out.println("El pasajero no existe en la base de datos");
                                                }
                                            }
                                        } while (true);
                                        opcion = 1;
                                        break;
                                    } else if (cadenaD.equalsIgnoreCase("N")) {
                                        teclado.nextLine();

                                        String nombrePasajero = "", apellidoPaterno = "", aoellidoMaterno = "", fechaNacimiento = "", telefono = "", direccion = "";
                                        String nombreTren = "", modelo = "", diasOperacion = "";
                                        char genero;
                                        nombrePasajero = as.regreso("Ingrese el nombre del pasajero(15 caracteres)", 15);
                                        apellidoPaterno = as.regreso("Ingrese el apellido paterno del pasajero(15 caracteres)", 15);
                                        aoellidoMaterno = as.regreso("Ingrese el apellido materno del pasajero(15 caracteres)", 15);
                                        do {
                                            System.out.println("");
                                            System.out.println("Ingrese la fecha de nacimiento DD/MM/AAAA (10 caracteres),Si no lo desea ingresar coloque un (*)");
                                            String cadenaF = teclado.nextLine();
                                            if (cadenaF.length() != 0 && cadenaF.length() <= 10) {
                                                if (cadenaF.length() == 1) {
                                                    if (cadenaF.equalsIgnoreCase("*")) {
                                                        break;
                                                    } else {
                                                        System.out.println("Caractere invalido");
                                                    }
                                                } else {
                                                    if (validaFecha(cadenaF)) {
                                                        String fecha = validarDias(cadenaF);
                                                        if (fecha == null) {
                                                            System.out.println("La fecha contiene dias invalidos,verifique el mes o dia");
                                                        } else {
                                                            fechaNacimiento = fecha;
                                                            break;
                                                        }
                                                    } else {
                                                        System.out.println("La fecha no concuerda con el formato");
                                                    }
                                                }

                                            } else {
                                                if (cadenaF.length() == 0) {
                                                    System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                                } else {
                                                    System.out.println("El maximo de caracteres permitos es de 10");
                                                }
                                            }
                                        } while (true);
                                        do {
                                            System.out.println("");
                                            System.out.println("Ingrese el genero del pasajero del pasajero M/H (1 caracteres)");
                                            String cadenaF = teclado.nextLine().toUpperCase();
                                            if (cadenaF.length() != 0 && cadenaF.length() <= 1) {
                                                if (cadenaF.equalsIgnoreCase("M") || cadenaF.equalsIgnoreCase("H")) {
                                                    genero = cadenaF.charAt(0);
                                                    break;
                                                } else {
                                                    System.out.println("No ha ingresado una opcion valida verifique porfavor.");
                                                }

                                            } else {
                                                if (cadenaF.length() == 0) {
                                                    System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                                } else {
                                                    System.out.println("El maximo de caracteres permitos es de 1");
                                                }
                                            }
                                        } while (true);
                                        do {
                                            System.out.println("");
                                            System.out.println("Ingrese el telefono del pasajero(12 caracteres),Si no lo desea ingresar coloque un (*)");
                                            String cadenaF = teclado.nextLine();
                                            if (cadenaF.length() != 0 && cadenaF.length() <= 12) {
                                                if (cadenaF.length() == 1) {
                                                    if (cadenaF.equalsIgnoreCase("*")) {
                                                        break;
                                                    } else {
                                                        System.out.println("No ha ingresado una opcion valida");
                                                    }
                                                } else {
                                                    if (!validaEntero(cadenaF)) {
                                                        System.out.println("Lo ingresado no corresponde a un numero de telefono");
                                                    } else {
                                                        telefono = cadenaF;
                                                        break;
                                                    }
                                                }
                                            } else {
                                                if (cadenaF.length() == 0) {
                                                    System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                                } else {
                                                    System.out.println("El maximo de caracteres permitos es de 12");
                                                }
                                            }
                                        } while (true);
                                        do {
                                            System.out.println("");
                                            System.out.println("Ingrese la direccion del pasajero(50 caracteres),Si no lo desea ingresar coloque un (*)");
                                            String cadenaF = teclado.nextLine();
                                            if (cadenaF.length() != 0 && cadenaF.length() <= 50) {
                                                if (cadenaF.length() == 1) {
                                                    if (cadenaF.equalsIgnoreCase("*")) {
                                                        break;
                                                    } else {
                                                        System.out.println("No ha ingresado una opcion valida");
                                                    }
                                                } else {
                                                    direccion = cadenaF;
                                                    break;
                                                }
                                            } else {
                                                if (cadenaF.length() == 0) {
                                                    System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                                } else {
                                                    System.out.println("El maximo de caracteres permitos es de 50");
                                                }
                                            }
                                        } while (true);
                                        System.out.println("");
                                        System.out.println("-----------------------------------------------------------------");
                                        System.out.println("|                     Confirmacion                              |");
                                        System.out.println("-----------------------------------------------------------------");
                                        System.out.printf("|%s %-53s |", "Nombre; ", nombrePasajero);
                                        System.out.println("");
                                        System.out.printf("|%s %-43s |", "Apellido Paterno; ", apellidoPaterno);
                                        System.out.println("");
                                        System.out.printf("|%s %-45s |", "Apellido Materno", aoellidoMaterno);
                                        System.out.println("");
                                        System.out.printf("|%s %-43s |", "Fecha Nacimiento; ", fechaNacimiento);
                                        System.out.println("");
                                        System.out.printf("|%s %-53s |", "Genero; ", genero);
                                        System.out.println("");
                                        System.out.printf("|%s %-51s |", "Telefono; ", telefono);
                                        System.out.println("");
                                        System.out.printf("|%s %-50s |", "Direccion; ", direccion);
                                        System.out.println("");
                                        System.out.println("-----------------------------------------------------------------");
                                        System.out.println("");
                                        pg = new Pasajero(nombrePasajero, apellidoPaterno, aoellidoMaterno, fechaNacimiento, genero, telefono, direccion);
                                        opcion = 2;
                                        break;
                                    } else {
                                        System.out.println("Ingrese una opcion valida");
                                    }
                                } else {
                                    if (cadenaD.length() == 0) {
                                        System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                    } else {
                                        System.out.println("El maximo de caracteres permitos es de 1");
                                    }
                                }
                            } while (true);
                            teclado.nextLine();
                            if (opcion == 1) {
                                if (auxilarTipo == 1 && s.getDisponibilidad_espera() == 0 && s.getDisponibilidad_general() == 0) {
                                    System.out.println("Ya no es posible reservarles boletos,el tipo de opcion que ha selecionado o la lista de espera esta llena");
                                } else if (auxilarTipo == 1 && s.getDisponibilidad_general() >= 1) {
                                    do {
                                        System.out.println("¿Desea comprar el boleto (y/n)?");
                                        String cadenaD = teclado.nextLine();
                                        if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                            if (cadenaD.equalsIgnoreCase("Y")) {
                                                // comprar;
                                                teclado.nextLine();
                                                BoletoDetalle boletoInsertar = new BoletoDetalle(p.getPasajero_id(), s.getSalida_id(), "General", "Confirmado", fechaActual + " " + hora);
                                                boletoPersistencia.insertarBoleto(boletoInsertar);
                                                int a = boletoPersistencia.obtenerBoletoLast();
                                                System.out.println("----------------------------------------------------------");
                                                System.out.println("|                ¡RESERVA GENERADA!                      |");
                                                System.out.println("----------------------------------------------------------");
                                                System.out.printf("|%15s %s %15s %4s %10s %7s \n", s.getOrigen_id(), "-", s.getDestino_id(), "|", "BOLETO NO.", "|");
                                                System.out.printf("|%19s %18s %7s %10s \n", fechaActual, "|", a, "|");
                                                System.out.printf("|%19s %-18s %13s \n", hora, "HORAS            |     ", "   |");
                                                System.out.printf("|%39s %s %s %s \n", " | ", "TREN NO.    ", s.getTren_id(), " |");
                                                System.out.println("----------------------------------------------------------");
                                                System.out.printf("|%-45s  %s  \n", p.getNombre() + " " + p.getApellido_paterno() + " " + p.getApellido_materno(), "         |");
                                                System.out.printf("|%-45s  %s  \n", s.getFecha() + " " + s.getHora(), "         |");
                                                System.out.println("----------------------------------------------------------");
                                                salidaPersistencia.actualizarDisponibilidaGeneral(s.getSalida_id(), 0);
                                                break;
                                            } else if (cadenaD.equalsIgnoreCase("N")) {
                                                break;
                                            } else {
                                                System.out.println("Ingrese una opcion valida");
                                            }
                                        } else {
                                            if (cadenaD.length() == 0) {
                                                System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                            } else {
                                                System.out.println("El maximo de caracteres permitos es de 1");
                                            }
                                        }
                                    } while (true);
                                } else if (auxilarTipo == 1 && s.getDisponibilidad_general() == 0 && s.getDisponibilidad_espera() >= 1) {
                                    do {
                                        System.out.println("¿Desea comprar el boleto aunque este se vaya a encontrar en lista de espera(y/n)?");
                                        String cadenaD = teclado.nextLine();
                                        if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                            if (cadenaD.equalsIgnoreCase("Y")) {
                                                teclado.nextLine();
                                                BoletoDetalle boletoInsertar = new BoletoDetalle(p.getPasajero_id(), s.getSalida_id(), "General", "Espera", fechaActual + " " + hora);
                                                boletoPersistencia.insertarBoleto(boletoInsertar);
                                                int a = boletoPersistencia.obtenerBoletoLast();
                                                System.out.println("----------------------------------------------------------");
                                                System.out.println("|                ¡RESERVA GENERADA!                      |");
                                                System.out.println("----------------------------------------------------------");
                                                System.out.printf("|%15s %s %15s %4s %10s %7s \n", s.getOrigen_id(), "-", s.getDestino_id(), "|", "BOLETO NO.", "|");
                                                System.out.printf("|%19s %18s %7s %10s \n", fechaActual, "|", a, "|");
                                                System.out.printf("|%19s %-18s %13s \n", hora, "HORAS            |     ", "   |");
                                                System.out.printf("|%39s %s %s %s \n", " | ", "TREN NO.    ", s.getTren_id(), " |");
                                                System.out.println("----------------------------------------------------------");
                                                System.out.printf("|%-45s  %s  \n", p.getNombre() + " " + p.getApellido_paterno() + " " + p.getApellido_materno(), "         |");
                                                System.out.printf("|%-45s  %s  \n", s.getFecha() + " " + s.getHora(), "         |");
                                                System.out.println("----------------------------------------------------------");
                                                salidaPersistencia.actualizarDisponibilidaEspera(s.getSalida_id(), 0);
                                                break;
                                            } else if (cadenaD.equalsIgnoreCase("N")) {
                                                break;
                                            } else {
                                                System.out.println("Ingrese una opcion valida");
                                            }
                                        } else {
                                            if (cadenaD.length() == 0) {
                                                System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                            } else {
                                                System.out.println("El maximo de caracteres permitos es de 1");
                                            }
                                        }
                                    } while (true);
                                }

                                if (auxilarTipo == 2 && s.getDisponibilidad_espera() == 0 && s.getDisponibilidad_a_primera_c() == 0) {
                                    System.out.println("Ya no es posible reservarles boletos,el tipo de opcion que ha selecionado o la lista de espera esta llena");
                                } else if (auxilarTipo == 2 && s.getDisponibilidad_a_primera_c() >= 1) {
                                    do {
                                        System.out.println("¿Desea comprar el boleto (y/n)?");
                                        String cadenaD = teclado.nextLine();
                                        if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                            if (cadenaD.equalsIgnoreCase("Y")) {
                                                teclado.nextLine();
                                                BoletoDetalle boletoInsertar = new BoletoDetalle(p.getPasajero_id(), s.getSalida_id(), "General", "Confirmado", fechaActual + " " + hora);
                                                boletoPersistencia.insertarBoleto(boletoInsertar);
                                                int a = boletoPersistencia.obtenerBoletoLast();
                                                System.out.println("----------------------------------------------------------");
                                                System.out.println("|                ¡RESERVA GENERADA!                      |");
                                                System.out.println("----------------------------------------------------------");
                                                System.out.printf("|%15s %s %15s %4s %10s %7s \n", s.getOrigen_id(), "-", s.getDestino_id(), "|", "BOLETO NO.", "|");
                                                System.out.printf("|%19s %18s %7s %10s \n", fechaActual, "|", a, "|");
                                                System.out.printf("|%19s %-18s %13s \n", hora, "HORAS            |     ", "   |");
                                                System.out.printf("|%39s %s %s %s \n", " | ", "TREN NO.    ", s.getTren_id(), " |");
                                                System.out.println("----------------------------------------------------------");
                                                System.out.printf("|%-45s  %s  \n", p.getNombre() + " " + p.getApellido_paterno() + " " + p.getApellido_materno(), "         |");
                                                System.out.printf("|%-45s  %s  \n", s.getFecha() + " " + s.getHora(), "         |");
                                                System.out.println("----------------------------------------------------------");
                                                salidaPersistencia.actualizarDisponibilidaPrimera(s.getSalida_id(), 0);
                                                break;
                                            } else if (cadenaD.equalsIgnoreCase("N")) {
                                                break;
                                            } else {
                                                System.out.println("Ingrese una opcion valida");
                                            }
                                        } else {
                                            if (cadenaD.length() == 0) {
                                                System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                            } else {
                                                System.out.println("El maximo de caracteres permitos es de 1");
                                            }
                                        }
                                    } while (true);
                                } else if (auxilarTipo == 2 && s.getDisponibilidad_a_primera_c() == 0 && s.getDisponibilidad_espera() >= 1) {
                                    do {
                                        System.out.println("¿Desea comprar el boleto aunque este se vaya a encontrar en lista de espera(y/n)?");
                                        String cadenaD = teclado.nextLine();
                                        if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                            if (cadenaD.equalsIgnoreCase("Y")) {
                                                // comprar;
                                                teclado.nextLine();
                                                BoletoDetalle boletoInsertar = new BoletoDetalle(p.getPasajero_id(), s.getSalida_id(), "General", "Espera", fechaActual + " " + hora);
                                                boletoPersistencia.insertarBoleto(boletoInsertar);
                                                int a = boletoPersistencia.obtenerBoletoLast();
                                                System.out.println("----------------------------------------------------------");
                                                System.out.println("|                ¡RESERVA GENERADA!                      |");
                                                System.out.println("----------------------------------------------------------");
                                                System.out.printf("|%15s %s %15s %4s %10s %7s \n", s.getOrigen_id(), "-", s.getDestino_id(), "|", "BOLETO NO.", "|");
                                                System.out.printf("|%19s %18s %7s %10s \n", fechaActual, "|", a, "|");
                                                System.out.printf("|%19s %-18s %13s \n", hora, "HORAS            |     ", "   |");
                                                System.out.printf("|%39s %s %s %s \n", " | ", "TREN NO.    ", s.getTren_id(), " |");
                                                System.out.println("----------------------------------------------------------");
                                                System.out.printf("|%-45s  %s  \n", p.getNombre() + " " + p.getApellido_paterno() + " " + p.getApellido_materno(), "         |");
                                                System.out.printf("|%-45s  %s  \n", s.getFecha() + " " + s.getHora(), "         |");
                                                System.out.println("----------------------------------------------------------");
                                                salidaPersistencia.actualizarDisponibilidaEspera(s.getSalida_id(), 0);
                                                break;
                                            } else if (cadenaD.equalsIgnoreCase("N")) {
                                                break;
                                            } else {
                                                System.out.println("Ingrese una opcion valida");
                                            }
                                        } else {
                                            if (cadenaD.length() == 0) {
                                                System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                            } else {
                                                System.out.println("El maximo de caracteres permitos es de 1");
                                            }
                                        }
                                    } while (true);
                                }

                            }
                            if (opcion == 2) {
                                if (auxilarTipo == 1 && s.getDisponibilidad_espera() == 0 && s.getDisponibilidad_general() == 0) {
                                    System.out.println("Ya no es posible reservarles boletos,el tipo de opcion que ha selecionado o la lista de espera esta llena");
                                } else if (auxilarTipo == 1 && s.getDisponibilidad_general() >= 1) {
                                    do {
                                        System.out.println("¿Desea comprar el boleto (y/n)?");
                                        String cadenaD = teclado.nextLine();
                                        if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                            if (cadenaD.equalsIgnoreCase("Y")) {
                                                // comprar;
                                                teclado.nextLine();
                                                pasajeroPersistencia.insertarPasajero(pg);
                                                int pasajero_id = pasajeroPersistencia.obtenerPasajeroLastID();
                                                BoletoDetalle boletoInsertar = new BoletoDetalle(pasajero_id, s.getSalida_id(), "General", "Espera", fechaActual + " " + hora);
                                                boletoPersistencia.insertarBoleto(boletoInsertar);
                                                int a = boletoPersistencia.obtenerBoletoLast();
                                                System.out.println("----------------------------------------------------------");
                                                System.out.println("|                ¡RESERVA GENERADA!                      |");
                                                System.out.println("----------------------------------------------------------");
                                                System.out.printf("|%15s %s %15s %4s %10s %7s \n", s.getOrigen_id(), "-", s.getDestino_id(), "|", "BOLETO NO.", "|");
                                                System.out.printf("|%19s %18s %7s %10s \n", fechaActual, "|", a, "|");
                                                System.out.printf("|%19s %-18s %13s \n", hora, "HORAS            |     ", "   |");
                                                System.out.printf("|%39s %s %s %s \n", " | ", "TREN NO.    ", s.getTren_id(), " |");
                                                System.out.println("----------------------------------------------------------");
                                                System.out.printf("|%-45s  %s  \n", pg.getNombre() + " " + pg.getApellido_paterno() + " " + pg.getApellido_materno(), "         |");
                                                System.out.printf("|%-45s  %s  \n", s.getFecha() + " " + s.getHora(), "         |");
                                                System.out.println("----------------------------------------------------------");
                                                salidaPersistencia.actualizarDisponibilidaGeneral(s.getSalida_id(), 0);
                                                break;
                                            } else if (cadenaD.equalsIgnoreCase("N")) {
                                                break;
                                            } else {
                                                System.out.println("Ingrese una opcion valida");
                                            }
                                        } else {
                                            if (cadenaD.length() == 0) {
                                                System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                            } else {
                                                System.out.println("El maximo de caracteres permitos es de 1");
                                            }
                                        }
                                    } while (true);
                                } else if (auxilarTipo == 1 && s.getDisponibilidad_general() == 0 && s.getDisponibilidad_espera() >= 1) {
                                    do {
                                        System.out.println("¿Desea comprar el boleto aunque este se vaya a encontrar en lista de espera(y/n)?");
                                        String cadenaD = teclado.nextLine();
                                        if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                            if (cadenaD.equalsIgnoreCase("Y")) {
                                                // comprar;
                                                teclado.nextLine();
                                                pasajeroPersistencia.insertarPasajero(pg);
                                                int pasajero_id = pasajeroPersistencia.obtenerPasajeroLastID();
                                                BoletoDetalle boletoInsertar = new BoletoDetalle(pasajero_id, s.getSalida_id(), "General", "Espera", fechaActual + " " + hora);
                                                boletoPersistencia.insertarBoleto(boletoInsertar);
                                                int a = boletoPersistencia.obtenerBoletoLast();
                                                System.out.println("----------------------------------------------------------");
                                                System.out.println("|                ¡RESERVA GENERADA!                      |");
                                                System.out.println("----------------------------------------------------------");
                                                System.out.printf("|%15s %s %15s %4s %10s %7s \n", s.getOrigen_id(), "-", s.getDestino_id(), "|", "BOLETO NO.", "|");
                                                System.out.printf("|%19s %18s %7s %10s \n", fechaActual, "|", a, "|");
                                                System.out.printf("|%19s %-18s %13s \n", hora, "HORAS            |     ", "   |");
                                                System.out.printf("|%39s %s %s %s \n", " | ", "TREN NO.    ", s.getTren_id(), " |");
                                                System.out.println("----------------------------------------------------------");
                                                System.out.printf("|%-45s  %s  \n", pg.getNombre() + " " + pg.getApellido_paterno() + " " + pg.getApellido_materno(), "         |");
                                                System.out.printf("|%-45s  %s  \n", s.getFecha() + " " + s.getHora(), "         |");
                                                System.out.println("----------------------------------------------------------");
                                                salidaPersistencia.actualizarDisponibilidaEspera(s.getSalida_id(), 0);
                                                break;
                                            } else if (cadenaD.equalsIgnoreCase("N")) {
                                                break;
                                            } else {
                                                System.out.println("Ingrese una opcion valida");
                                            }
                                        } else {
                                            if (cadenaD.length() == 0) {
                                                System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                            } else {
                                                System.out.println("El maximo de caracteres permitos es de 1");
                                            }
                                        }
                                    } while (true);
                                }

                                if (auxilarTipo == 2 && s.getDisponibilidad_espera() == 0 && s.getDisponibilidad_a_primera_c() == 0) {
                                    System.out.println("Ya no es posible reservarles boletos,el tipo de opcion que ha selecionado o la lista de espera esta llena");
                                } else if (auxilarTipo == 2 && s.getDisponibilidad_a_primera_c() >= 1) {
                                    do {
                                        System.out.println("¿Desea comprar el boleto (y/n)?");
                                        String cadenaD = teclado.nextLine();
                                        if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                            if (cadenaD.equalsIgnoreCase("Y")) {
                                                teclado.nextLine();
                                                pasajeroPersistencia.insertarPasajero(pg);
                                                int pasajero_id = pasajeroPersistencia.obtenerPasajeroLastID();
                                                BoletoDetalle boletoInsertar = new BoletoDetalle(pasajero_id, s.getSalida_id(), "General", "Espera", fechaActual + " " + hora);
                                                boletoPersistencia.insertarBoleto(boletoInsertar);
                                                int a = boletoPersistencia.obtenerBoletoLast();
                                                System.out.println("----------------------------------------------------------");
                                                System.out.println("|                ¡RESERVA GENERADA!                      |");
                                                System.out.println("----------------------------------------------------------");
                                                System.out.printf("|%15s %s %15s %4s %10s %7s \n", s.getOrigen_id(), "-", s.getDestino_id(), "|", "BOLETO NO.", "|");
                                                System.out.printf("|%19s %18s %7s %10s \n", fechaActual, "|", a, "|");
                                                System.out.printf("|%19s %-18s %13s \n", hora, "HORAS            |     ", "   |");
                                                System.out.printf("|%39s %s %s %s \n", " | ", "TREN NO.    ", s.getTren_id(), " |");
                                                System.out.println("----------------------------------------------------------");
                                                System.out.printf("|%-45s  %s  \n", pg.getNombre() + " " + pg.getApellido_paterno() + " " + pg.getApellido_materno(), "         |");
                                                System.out.printf("|%-45s  %s  \n", s.getFecha() + " " + s.getHora(), "         |");
                                                System.out.println("----------------------------------------------------------");
                                                salidaPersistencia.actualizarDisponibilidaPrimera(s.getSalida_id(), 0);
                                                break;
                                            } else if (cadenaD.equalsIgnoreCase("N")) {
                                                break;
                                            } else {
                                                System.out.println("Ingrese una opcion valida");
                                            }
                                        } else {
                                            if (cadenaD.length() == 0) {
                                                System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                            } else {
                                                System.out.println("El maximo de caracteres permitos es de 1");
                                            }
                                        }
                                    } while (true);
                                } else if (auxilarTipo == 2 && s.getDisponibilidad_a_primera_c() == 0 && s.getDisponibilidad_espera() >= 1) {
                                    do {
                                        System.out.println("¿Desea comprar el boleto aunque este se vaya a encontrar en lista de espera(y/n)?");
                                        String cadenaD = teclado.nextLine();
                                        if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                            if (cadenaD.equalsIgnoreCase("Y")) {
                                                // comprar;
                                                teclado.nextLine();
                                                pasajeroPersistencia.insertarPasajero(pg);
                                                int pasajero_id = pasajeroPersistencia.obtenerPasajeroLastID();
                                                BoletoDetalle boletoInsertar = new BoletoDetalle(pasajero_id, s.getSalida_id(), "General", "Espera", fechaActual + " " + hora);
                                                boletoPersistencia.insertarBoleto(boletoInsertar);
                                                int a = boletoPersistencia.obtenerBoletoLast();
                                                System.out.println("----------------------------------------------------------");
                                                System.out.println("|                ¡RESERVA GENERADA!                      |");
                                                System.out.println("----------------------------------------------------------");
                                                System.out.printf("|%15s %s %15s %4s %10s %7s \n", s.getOrigen_id(), "-", s.getDestino_id(), "|", "BOLETO NO.", "|");
                                                System.out.printf("|%19s %18s %7s %10s \n", fechaActual, "|", a, "|");
                                                System.out.printf("|%19s %-18s %13s \n", hora, "HORAS            |     ", "   |");
                                                System.out.printf("|%39s %s %s %s \n", " | ", "TREN NO.    ", s.getTren_id(), " |");
                                                System.out.println("----------------------------------------------------------");
                                                System.out.printf("|%-45s  %s  \n", pg.getNombre() + " " + pg.getApellido_paterno() + " " + pg.getApellido_materno(), "         |");
                                                System.out.printf("|%-45s  %s  \n", s.getFecha() + " " + s.getHora(), "         |");
                                                System.out.println("----------------------------------------------------------");
                                                salidaPersistencia.actualizarDisponibilidaEspera(s.getSalida_id(), 0);
                                                break;
                                            } else if (cadenaD.equalsIgnoreCase("N")) {
                                                break;
                                            } else {
                                                System.out.println("Ingrese una opcion valida");
                                            }
                                        } else {
                                            if (cadenaD.length() == 0) {
                                                System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                            } else {
                                                System.out.println("El maximo de caracteres permitos es de 1");
                                            }
                                        }
                                    } while (true);
                                }

                            }

                            break;
                        } else {
                            System.out.println("Porfavor ingrese una salida de la lista que se presento");
                        }
                    }
                } while (true);
                teclado.nextLine();
                System.out.println("-------------------------------------------------------------------");
                do {
                    System.out.println("¿Desea comprar otro boleto (y/n)?");
                    String cadenaD = teclado.nextLine();
                    if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                        if (cadenaD.equalsIgnoreCase("Y")) {
                            break;
                        } else if (cadenaD.equalsIgnoreCase("N")) {
                            opcion1 = false;
                            break;
                        } else {
                            System.out.println("Ingrese una opcion valida");
                        }
                    } else {
                        if (cadenaD.length() == 0) {
                            System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                        } else {
                            System.out.println("El maximo de caracteres permitos es de 1");
                        }
                    }
                } while (true);

                System.out.println("");
            } else {
                opcion1 = false;
                System.out.println("No hay se pueden reservar boletos, la fecha de reserva de uno es tres dias antes de la fecha de la salida");
            }
        }

    }

    public void opcion2() {
        System.out.println("");
        Scanner teclado = new Scanner(System.in);
        boolean opcion2 = true;
        while (opcion2) {
            System.out.println("----------------------------  -----------------");
            System.out.println("|♥♦♣♠      Cancelar un Boleto           ♥♦♣♠|");
            System.out.println("-----------------------------------------------");
            System.out.println("|     Opciones             |      Tecla        |");
            System.out.println("------------------------------------------------");
            System.out.println("| 1. Por numero de Boleto  |          1        | ");
            System.out.println("| 2. Por numero de Cliente |          2        | ");
            System.out.println("| 4. Por nombre de cliente |          3        | ");
            System.out.println("| 5. Salir                 |          0        | ");
            System.out.println("------------------------------------------------");
            System.out.println("");
            Integer auxiliar = as.opcionesMenuRangos(3);
            switch (auxiliar) {
                case 1:
                    Integer idBoleto = null;
                    do {
                        try {
                            System.out.println("Ingrese el id del boleto a Cancelar");
                            int n = teclado.nextInt();
                            idBoleto = n;
                        } catch (Exception e) {
                            teclado.nextLine();
                            System.out.println("Porfavor ingrese digitos");
                        }
                        if (idBoleto != null) {
                            BoletoDetalle L = boletoPersistencia.obtenerBoletoDetallado(idBoleto);
                            if (L != null) {
                                if (L.getEstatus().equalsIgnoreCase("Cancelado")) {
                                    System.out.println("El boleto ya ha sido cancelado");
                                } else if (L.getEstatus().equalsIgnoreCase("Espera")) {
                                    teclado.nextLine();
                                    System.out.println("-------------------------------------------");
                                    System.out.println("|          Datos del boleto               |");
                                    System.out.println("-------------------------------------------");
                                    System.out.printf("|%s %-27s |", "Id. Boleto; ", L.getBoleto_id());
                                    System.out.println("");
                                    System.out.printf("|%s %-25s |", "Id. Pasajero; ", L.getPasajero_id());
                                    System.out.println("");
                                    System.out.printf("|%s %-22s |", "Nombre Pasajero; ", L.getNombre_pasajero());
                                    System.out.println("");
                                    System.out.printf("|%s %-27s |", "Id. Salida; ", L.getSalida_id());
                                    System.out.println("");
                                    System.out.printf("|%s %-30s |", "Orgien ID", L.getOrigen_id());
                                    System.out.println("");
                                    System.out.printf("|%s %-22s |", "Estacion_Origen; ", L.getOrigen_nombre());
                                    System.out.println("");
                                    System.out.printf("|%s %-29s |", "Destino_ID", L.getDestino_id());
                                    System.out.println("");
                                    System.out.printf("|%s %-21s |", "Estacion_Destino; ", L.getDestino_nombre());
                                    System.out.println("");
                                    System.out.printf("|%s %-31s |", "ID. Tren", L.getTren_id());
                                    System.out.println("");
                                    System.out.printf("|%s %-26s |", "Nombre_Tren; ", L.getTren_nombre());
                                    System.out.println("");
                                    System.out.printf("|%s %-26s |", "Tipo Boleto; ", L.getTipo_boleto());
                                    System.out.println("");
                                    System.out.printf("|%s %-22s |", "Fecha De Compra; ", L.getFecha_boleto());
                                    System.out.println("");
                                    System.out.println("-------------------------------------------");
                                    System.out.println("");
                                    do {
                                        System.out.println("¿Desea cancelar el boleto (y/n)?");
                                        String cadenaD = teclado.nextLine();
                                        if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                            if (cadenaD.equalsIgnoreCase("Y")) {
                                                boletoPersistencia.cancelarBoleto(idBoleto);
                                                salidaPersistencia.actualizarDisponibilidaEspera(L.getSalida_id(), 1);
                                                break;
                                            } else if (cadenaD.equalsIgnoreCase("N")) {
                                                break;
                                            } else {
                                                System.out.println("Ingrese una opcion valida");
                                            }
                                        } else {
                                            if (cadenaD.length() == 0) {
                                                System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                            } else {
                                                System.out.println("El maximo de caracteres permitos es de 1");
                                            }
                                        }
                                    } while (true);
                                    System.out.println("");
                                } else if (L.getEstatus().equalsIgnoreCase("Confirmado")) {
                                    teclado.nextLine();
                                    teclado.nextLine();
                                    System.out.println("-------------------------------------------");
                                    System.out.println("|          Datos del boleto               |");
                                    System.out.println("-------------------------------------------");
                                    System.out.printf("|%s %-27s |", "Id. Boleto; ", L.getBoleto_id());
                                    System.out.println("");
                                    System.out.printf("|%s %-25s |", "Id. Pasajero; ", L.getPasajero_id());
                                    System.out.println("");
                                    System.out.printf("|%s %-22s |", "Nombre Pasajero; ", L.getNombre_pasajero());
                                    System.out.println("");
                                    System.out.printf("|%s %-27s |", "Id. Salida; ", L.getSalida_id());
                                    System.out.println("");
                                    System.out.printf("|%s %-30s |", "Orgien ID", L.getOrigen_id());
                                    System.out.println("");
                                    System.out.printf("|%s %-22s |", "Estacion_Origen; ", L.getOrigen_nombre());
                                    System.out.println("");
                                    System.out.printf("|%s %-29s |", "Destino_ID", L.getDestino_id());
                                    System.out.println("");
                                    System.out.printf("|%s %-21s |", "Estacion_Destino; ", L.getDestino_nombre());
                                    System.out.println("");
                                    System.out.printf("|%s %-31s |", "ID. Tren", L.getTren_id());
                                    System.out.println("");
                                    System.out.printf("|%s %-26s |", "Nombre_Tren; ", L.getTren_nombre());
                                    System.out.println("");
                                    System.out.printf("|%s %-26s |", "Tipo Boleto; ", L.getTipo_boleto());
                                    System.out.println("");
                                    System.out.printf("|%s %-22s |", "Fecha De Compra; ", L.getFecha_boleto());
                                    System.out.println("");
                                    System.out.println("-------------------------------------------");
                                    System.out.println("");
                                    do {
                                        System.out.println("¿Desea cancelar el boleto (y/n)?");
                                        String cadenaD = teclado.nextLine();
                                        if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                            if (cadenaD.equalsIgnoreCase("Y")) {
                                                BoletoDetalle obtener = boletoPersistencia.obtenerBoletoDetallado(idBoleto);
                                                boletoPersistencia.cancelarBoleto(idBoleto);
                                                if (L.getTipo_boleto().equalsIgnoreCase("General")) {
                                                    salidaPersistencia.actualizarDisponibilidaGeneral(L.getSalida_id(), 1);
                                                    ArrayList<BoletoDetalle> listaEspera = boletoPersistencia.consultarTodosEsperaParaPreguntar(L.getSalida_id());
                                                    if (listaEspera.size() > 0) {
                                                        if (listaEspera.get(0).getTipo_boleto().equalsIgnoreCase("General")) {
                                                            System.out.println("");
                                                            System.out.println("-------------------------------------------");
                                                            System.out.println("|          Datos del boleto               |");
                                                            System.out.println("-------------------------------------------");
                                                            System.out.printf("|%s %-27s |", "Id. Boleto; ", listaEspera.get(0).getBoleto_id());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-25s |", "Id. Pasajero; ", listaEspera.get(0).getPasajero_id());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-22s |", "Nombre Pasajero; ", listaEspera.get(0).getNombre_pasajero());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-27s |", "Id. Salida; ", listaEspera.get(0).getSalida_id());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-30s |", "Orgien ID", listaEspera.get(0).getOrigen_id());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-22s |", "Estacion_Origen; ", listaEspera.get(0).getOrigen_nombre());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-29s |", "Destino_ID", listaEspera.get(0).getDestino_id());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-21s |", "Estacion_Destino; ", listaEspera.get(0).getDestino_nombre());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-31s |", "ID. Tren", listaEspera.get(0).getTren_id());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-26s |", "Nombre_Tren; ", listaEspera.get(0).getTren_nombre());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-26s |", "Tipo Boleto; ", listaEspera.get(0).getTipo_boleto());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-22s |", "Fecha De Compra; ", listaEspera.get(0).getFecha_boleto());
                                                            System.out.println("");
                                                            System.out.println("-------------------------------------------");
                                                            System.out.println("");
                                                            do {
                                                                System.out.println("¿Desea comprar o vender el boleto mostrado(y/n)?");
                                                                String cadenah = teclado.nextLine();
                                                                if (cadenah.length() != 0 && cadenah.length() <= 1) {
                                                                    if (cadenaD.equalsIgnoreCase("Y")) {
                                                                        salidaPersistencia.actualizarDisponibilidaGeneral(L.getSalida_id(), 0);
                                                                        boletoPersistencia.restablecerBoleto(listaEspera.get(0).getBoleto_id());
                                                                        salidaPersistencia.actualizarDisponibilidaEspera(listaEspera.get(0).getSalida_id(), 1);
                                                                        break;
                                                                    } else if (cadenah.equalsIgnoreCase("N")) {
                                                                        break;
                                                                    } else {
                                                                        System.out.println("Ingrese una opcion valida");
                                                                    }
                                                                } else {
                                                                    if (cadenah.length() == 0) {
                                                                        System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                                                    } else {
                                                                        System.out.println("El maximo de caracteres permitos es de 1");
                                                                    }
                                                                }
                                                            } while (true);
                                                            System.out.println("");
                                                        }
                                                    }
                                                } else if (L.getTipo_boleto().equalsIgnoreCase("Primera Clase")) {
                                                    salidaPersistencia.actualizarDisponibilidaPrimera(L.getSalida_id(), 1);
                                                    ArrayList<BoletoDetalle> listaEspera = boletoPersistencia.consultarTodosEsperaParaPreguntar(L.getSalida_id());
                                                    if (listaEspera.size() > 0) {
                                                        if (listaEspera.get(0).getTipo_boleto().equalsIgnoreCase("Primera Clase")) {
                                                            System.out.println("");
                                                            System.out.println("-------------------------------------------");
                                                            System.out.println("|          Datos del boleto               |");
                                                            System.out.println("-------------------------------------------");
                                                            System.out.printf("|%s %-27s |", "Id. Boleto; ", listaEspera.get(0).getBoleto_id());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-25s |", "Id. Pasajero; ", listaEspera.get(0).getPasajero_id());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-22s |", "Nombre Pasajero; ", listaEspera.get(0).getNombre_pasajero());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-27s |", "Id. Salida; ", listaEspera.get(0).getSalida_id());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-30s |", "Orgien ID", listaEspera.get(0).getOrigen_id());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-22s |", "Estacion_Origen; ", listaEspera.get(0).getOrigen_nombre());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-29s |", "Destino_ID", listaEspera.get(0).getDestino_id());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-21s |", "Estacion_Destino; ", listaEspera.get(0).getDestino_nombre());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-31s |", "ID. Tren", listaEspera.get(0).getTren_id());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-26s |", "Nombre_Tren; ", listaEspera.get(0).getTren_nombre());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-26s |", "Tipo Boleto; ", listaEspera.get(0).getTipo_boleto());
                                                            System.out.println("");
                                                            System.out.printf("|%s %-22s |", "Fecha De Compra; ", listaEspera.get(0).getFecha_boleto());
                                                            System.out.println("");
                                                            System.out.println("-------------------------------------------");
                                                            System.out.println("");
                                                            do {
                                                                System.out.println("¿Desea comprar o vender el boleto mostrado(y/n)?");
                                                                String cadenah = teclado.nextLine();
                                                                if (cadenah.length() != 0 && cadenah.length() <= 1) {
                                                                    if (cadenaD.equalsIgnoreCase("Y")) {
                                                                        salidaPersistencia.actualizarDisponibilidaPrimera(L.getSalida_id(), 0);
                                                                        boletoPersistencia.restablecerBoleto(listaEspera.get(0).getBoleto_id());
                                                                        salidaPersistencia.actualizarDisponibilidaEspera(listaEspera.get(0).getSalida_id(), 1);
                                                                        break;
                                                                    } else if (cadenah.equalsIgnoreCase("N")) {
                                                                        break;
                                                                    } else {
                                                                        System.out.println("Ingrese una opcion valida");
                                                                    }
                                                                } else {
                                                                    if (cadenah.length() == 0) {
                                                                        System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                                                    } else {
                                                                        System.out.println("El maximo de caracteres permitos es de 1");
                                                                    }
                                                                }
                                                            } while (true);
                                                            System.out.println("");
                                                        }
                                                    }
                                                }

                                                break;
                                            } else if (cadenaD.equalsIgnoreCase("N")) {
                                                break;
                                            } else {
                                                System.out.println("Ingrese una opcion valida");
                                            }
                                        } else {
                                            if (cadenaD.length() == 0) {
                                                System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                            } else {
                                                System.out.println("El maximo de caracteres permitos es de 1");
                                            }
                                        }
                                    } while (true);
                                    System.out.println("");
                                }
                            } else {
                                System.out.println("No existe un boleto con el id ingresado");
                            }
                            break;
                        }
                    } while (true);
                    break;
                case 2:
                    Integer idPasajero = null;
                    do {
                        try {
                            System.out.println("Ingrese el id del pasajero a cancelar un boleto");
                            int n = teclado.nextInt();
                            idPasajero = n;
                        } catch (Exception e) {
                            teclado.nextLine();
                            System.out.println("Porfavor ingrese digitos");
                        }
                        if (idPasajero != null) {
                            String consultaBase = "SELECT b.boleto_id,b.pasajero_id, p.nombre AS Nombre_Pasajero,b.salida_id,o.lugar_id AS Origen_ID,o.nombre_estacion AS Estacion_Origen,d.lugar_id AS Destino_ID,d.nombre_estacion AS Estacion_Destino, t.tren_id, t.nombre AS Nombre_tren, b.tipo_boleto,b.fecha_y_hora AS Fecha_Boleto, b.estatus FROM Boletos AS b INNER JOIN Pasajero AS P ON b.pasajero_id=p.pasajero_id INNER JOIN Salidas as s ON b.salida_id=s.salida_id INNER JOIN Lugar AS o ON s.origen_id=o.lugar_id INNER JOIN Lugar AS d ON d.lugar_id=s.destino_id INNER JOIN Tren AS t ON t.tren_id=s.tren_id WHERE (b.estatus='Espera' OR b.estatus='Confirmado')AND p.pasajero_id=" + idPasajero;
                            ArrayList< BoletoDetalle> listaBoletos = boletoPersistencia.consultarBoletosFiltroEspecial(consultaBase);
                            if (listaBoletos.size() >= 0) {
                                System.out.println("");
                                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("|                                                  Tabla de Boletos Vendidos                                                                          |");
                                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("|ID   |P.ID |Nombre           |S.ID |O.ID |Estacion_Origen |D.ID |Estacion_Destino  |T.ID |Nombre Tren      |Tipo Boleto      |Fecha Boleto           |");
                                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                                for (int i = 0; i < listaBoletos.size(); i++) {
                                    System.out.printf("|%-4s |%-4s |%-16s |%-4s |%-4s |%-16s |%-4s |%-16s |%-4s |%-16s |%-15s  |%-20s |", listaBoletos.get(i).getBoleto_id(), listaBoletos.get(i).getPasajero_id(),
                                            listaBoletos.get(i).getNombre_pasajero(), listaBoletos.get(i).getSalida_id(), listaBoletos.get(i).getOrigen_id(),
                                            listaBoletos.get(i).getOrigen_nombre(), listaBoletos.get(i).getDestino_id(), listaBoletos.get(i).getDestino_nombre(), listaBoletos.get(i).getTren_id(), listaBoletos.get(i).getTren_nombre(),
                                            (listaBoletos.get(i).getTipo_boleto().equalsIgnoreCase("General")
                                            ? listaBoletos.get(i).getTipo_boleto() + "   " : listaBoletos.get(i).getTipo_boleto()), listaBoletos.get(i).getFecha_boleto());
                                    System.out.println("");
                                }
                                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                                System.out.println("");
                                idBoleto = null;
                                do {
                                    try {
                                        System.out.println("Ingrese el id del boleto a Cancelar");
                                        int n = teclado.nextInt();
                                        idBoleto = n;
                                    } catch (Exception e) {
                                        teclado.nextLine();
                                        System.out.println("Porfavor ingrese digitos");
                                    }
                                    if (idBoleto != null) {
                                        if (listaBoletos.indexOf(new BoletoDetalle(idBoleto)) >= 0) {
                                            int indice = listaBoletos.indexOf(new BoletoDetalle(idBoleto));
                                            System.out.println("-------------------------------------------");
                                            System.out.println("|          Datos del boleto               |");
                                            System.out.println("-------------------------------------------");
                                            System.out.printf("|%s %-27s |", "Id. Boleto; ", listaBoletos.get(indice).getBoleto_id());
                                            System.out.println("");
                                            System.out.printf("|%s %-25s |", "Id. Pasajero; ", listaBoletos.get(indice).getPasajero_id());
                                            System.out.println("");
                                            System.out.printf("|%s %-22s |", "Nombre Pasajero; ", listaBoletos.get(indice).getNombre_pasajero());
                                            System.out.println("");
                                            System.out.printf("|%s %-27s |", "Id. Salida; ", listaBoletos.get(indice).getSalida_id());
                                            System.out.println("");
                                            System.out.printf("|%s %-30s |", "Orgien ID", listaBoletos.get(indice).getOrigen_id());
                                            System.out.println("");
                                            System.out.printf("|%s %-22s |", "Estacion_Origen; ", listaBoletos.get(indice).getOrigen_nombre());
                                            System.out.println("");
                                            System.out.printf("|%s %-29s |", "Destino_ID", listaBoletos.get(indice).getDestino_id());
                                            System.out.println("");
                                            System.out.printf("|%s %-21s |", "Estacion_Destino; ", listaBoletos.get(indice).getDestino_nombre());
                                            System.out.println("");
                                            System.out.printf("|%s %-31s |", "ID. Tren", listaBoletos.get(indice).getTren_id());
                                            System.out.println("");
                                            System.out.printf("|%s %-26s |", "Nombre_Tren; ", listaBoletos.get(indice).getTren_nombre());
                                            System.out.println("");
                                            System.out.printf("|%s %-26s |", "Tipo Boleto; ", listaBoletos.get(indice).getTipo_boleto());
                                            System.out.println("");
                                            System.out.printf("|%s %-22s |", "Fecha De Compra; ", listaBoletos.get(indice).getFecha_boleto());
                                            System.out.println("");
                                            System.out.println("-------------------------------------------");
                                            System.out.println("");
                                            teclado.nextLine();
                                            do {
                                                System.out.println("¿Desea cancelar el boleto (y/n)?");
                                                String cadenaD = teclado.nextLine();
                                                if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                                    if (cadenaD.equalsIgnoreCase("Y")) {
                                                        BoletoDetalle L = boletoPersistencia.obtenerBoletoDetallado(idBoleto);
                                                        boletoPersistencia.cancelarBoleto(idBoleto);
                                                        if (L.getTipo_boleto().equalsIgnoreCase("General")) {
                                                            salidaPersistencia.actualizarDisponibilidaGeneral(L.getSalida_id(), 1);
                                                            ArrayList<BoletoDetalle> listaEspera = boletoPersistencia.consultarTodosEsperaParaPreguntar(L.getSalida_id());
                                                            if (listaEspera.size() > 0) {
                                                                if (listaEspera.get(0).getTipo_boleto().equalsIgnoreCase("General")) {
                                                                    System.out.println("");
                                                                    System.out.println("-------------------------------------------");
                                                                    System.out.println("|          Datos del boleto               |");
                                                                    System.out.println("-------------------------------------------");
                                                                    System.out.printf("|%s %-27s |", "Id. Boleto; ", listaEspera.get(0).getBoleto_id());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-25s |", "Id. Pasajero; ", listaEspera.get(0).getPasajero_id());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-22s |", "Nombre Pasajero; ", listaEspera.get(0).getNombre_pasajero());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-27s |", "Id. Salida; ", listaEspera.get(0).getSalida_id());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-30s |", "Orgien ID", listaEspera.get(0).getOrigen_id());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-22s |", "Estacion_Origen; ", listaEspera.get(0).getOrigen_nombre());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-29s |", "Destino_ID", listaEspera.get(0).getDestino_id());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-21s |", "Estacion_Destino; ", listaEspera.get(0).getDestino_nombre());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-31s |", "ID. Tren", listaEspera.get(0).getTren_id());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-26s |", "Nombre_Tren; ", listaEspera.get(0).getTren_nombre());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-26s |", "Tipo Boleto; ", listaEspera.get(0).getTipo_boleto());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-22s |", "Fecha De Compra; ", listaEspera.get(0).getFecha_boleto());
                                                                    System.out.println("");
                                                                    System.out.println("-------------------------------------------");
                                                                    System.out.println("");
                                                                    do {
                                                                        System.out.println("¿Desea comprar o vender el boleto mostrado(y/n)?");
                                                                        String cadenah = teclado.nextLine();
                                                                        if (cadenah.length() != 0 && cadenah.length() <= 1) {
                                                                            if (cadenaD.equalsIgnoreCase("Y")) {
                                                                                salidaPersistencia.actualizarDisponibilidaGeneral(L.getSalida_id(), 0);
                                                                                boletoPersistencia.restablecerBoleto(listaEspera.get(0).getBoleto_id());
                                                                                salidaPersistencia.actualizarDisponibilidaEspera(listaEspera.get(0).getSalida_id(), 1);
                                                                                break;
                                                                            } else if (cadenah.equalsIgnoreCase("N")) {
                                                                                break;
                                                                            } else {
                                                                                System.out.println("Ingrese una opcion valida");
                                                                            }
                                                                        } else {
                                                                            if (cadenah.length() == 0) {
                                                                                System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                                                            } else {
                                                                                System.out.println("El maximo de caracteres permitos es de 1");
                                                                            }
                                                                        }
                                                                    } while (true);
                                                                    System.out.println("");
                                                                }
                                                            }
                                                        } else if (L.getTipo_boleto().equalsIgnoreCase("Primera Clase")) {
                                                            salidaPersistencia.actualizarDisponibilidaPrimera(L.getSalida_id(), 1);
                                                            ArrayList<BoletoDetalle> listaEspera = boletoPersistencia.consultarTodosEsperaParaPreguntar(L.getSalida_id());
                                                            if (listaEspera.size() > 0) {
                                                                if (listaEspera.get(0).getTipo_boleto().equalsIgnoreCase("Primera Clase")) {
                                                                    System.out.println("");
                                                                    System.out.println("-------------------------------------------");
                                                                    System.out.println("|          Datos del boleto               |");
                                                                    System.out.println("-------------------------------------------");
                                                                    System.out.printf("|%s %-27s |", "Id. Boleto; ", listaEspera.get(0).getBoleto_id());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-25s |", "Id. Pasajero; ", listaEspera.get(0).getPasajero_id());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-22s |", "Nombre Pasajero; ", listaEspera.get(0).getNombre_pasajero());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-27s |", "Id. Salida; ", listaEspera.get(0).getSalida_id());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-30s |", "Orgien ID", listaEspera.get(0).getOrigen_id());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-22s |", "Estacion_Origen; ", listaEspera.get(0).getOrigen_nombre());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-29s |", "Destino_ID", listaEspera.get(0).getDestino_id());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-21s |", "Estacion_Destino; ", listaEspera.get(0).getDestino_nombre());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-31s |", "ID. Tren", listaEspera.get(0).getTren_id());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-26s |", "Nombre_Tren; ", listaEspera.get(0).getTren_nombre());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-26s |", "Tipo Boleto; ", listaEspera.get(0).getTipo_boleto());
                                                                    System.out.println("");
                                                                    System.out.printf("|%s %-22s |", "Fecha De Compra; ", listaEspera.get(0).getFecha_boleto());
                                                                    System.out.println("");
                                                                    System.out.println("-------------------------------------------");
                                                                    System.out.println("");
                                                                    do {
                                                                        System.out.println("¿Desea comprar o vender el boleto mostrado(y/n)?");
                                                                        String cadenah = teclado.nextLine();
                                                                        if (cadenah.length() != 0 && cadenah.length() <= 1) {
                                                                            if (cadenaD.equalsIgnoreCase("Y")) {
                                                                                salidaPersistencia.actualizarDisponibilidaPrimera(L.getSalida_id(), 0);
                                                                                boletoPersistencia.restablecerBoleto(listaEspera.get(0).getBoleto_id());
                                                                                salidaPersistencia.actualizarDisponibilidaEspera(listaEspera.get(0).getSalida_id(), 1);
                                                                                break;
                                                                            } else if (cadenah.equalsIgnoreCase("N")) {
                                                                                break;
                                                                            } else {
                                                                                System.out.println("Ingrese una opcion valida");
                                                                            }
                                                                        } else {
                                                                            if (cadenah.length() == 0) {
                                                                                System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                                                            } else {
                                                                                System.out.println("El maximo de caracteres permitos es de 1");
                                                                            }
                                                                        }
                                                                    } while (true);
                                                                    System.out.println("");
                                                                }
                                                            }
                                                        }

                                                        break;
                                                    } else if (cadenaD.equalsIgnoreCase("N")) {
                                                        break;
                                                    } else {
                                                        System.out.println("Ingrese una opcion valida");
                                                    }
                                                } else {
                                                    if (cadenaD.length() == 0) {
                                                        System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                                    } else {
                                                        System.out.println("El maximo de caracteres permitos es de 1");
                                                    }
                                                }
                                            } while (true);
                                            System.out.println("");
                                            break;
                                        } else {
                                            System.out.println("Porfavor eliga un id de los que estan ingresados");
                                        }
                                        break;
                                    }
                                } while (true);
                            } else {
                                System.out.println("El pasajero no ha comprado boletos");
                            }
                            break;
                        }
                    } while (true);
                    break;

                case 3:
                    teclado.nextLine();
                    String nombreBuscar = as.regreso("Ingrese el nombre del pasajero(15 caracteres)", 15);
                    ArrayList<Pasajero> listaActualF = pasajeroPersistencia.consultarTodosFiltro("select * from Pasajero where nombre  like '%" + nombreBuscar + "%'");
                    if (listaActualF.size() > 0) {
                        System.out.println("");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|                                                           Tabla de Pasajeros                                                                 |");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |Nombre          |Apellido Paterno|Apellido Materno|Fecha Nacimi.|Gen. |Telefono     |Direccion                                          |");
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
                        for (int i = 0; i < listaActualF.size(); i++) {
                            System.out.printf("|%-4s |%-15s |%-15s |%-15s |%-12s |%-4s |%-12s |%-50s |", listaActualF.get(i).getPasajero_id(), listaActualF.get(i).getNombre(), listaActualF.get(i).getApellido_paterno(), listaActualF.get(i).getApellido_materno(), listaActualF.get(i).getFecha_nacimiento(), listaActualF.get(i).getGenero(), listaActualF.get(i).getTelefono(), listaActualF.get(i).getDireccion());
                            System.out.println("");
                        }
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
                        idPasajero = null;
                        do {
                            try {
                                System.out.println("Ingrese el id del pasajero a cancelar un boleto");
                                int n = teclado.nextInt();
                                idPasajero = n;
                            } catch (Exception e) {
                                teclado.nextLine();
                                System.out.println("Porfavor ingrese digitos");
                            }
                            if (idPasajero != null) {
                                String consultaBase = "SELECT b.boleto_id,b.pasajero_id, p.nombre AS Nombre_Pasajero,b.salida_id,o.lugar_id AS Origen_ID,o.nombre_estacion AS Estacion_Origen,d.lugar_id AS Destino_ID,d.nombre_estacion AS Estacion_Destino, t.tren_id, t.nombre AS Nombre_tren, b.tipo_boleto,b.fecha_y_hora AS Fecha_Boleto, b.estatus FROM Boletos AS b INNER JOIN Pasajero AS P ON b.pasajero_id=p.pasajero_id INNER JOIN Salidas as s ON b.salida_id=s.salida_id INNER JOIN Lugar AS o ON s.origen_id=o.lugar_id INNER JOIN Lugar AS d ON d.lugar_id=s.destino_id INNER JOIN Tren AS t ON t.tren_id=s.tren_id WHERE (b.estatus='Espera' OR b.estatus='Confirmado')AND p.pasajero_id=" + idPasajero;
                                ArrayList< BoletoDetalle> listaBoletos = boletoPersistencia.consultarBoletosFiltroEspecial(consultaBase);
                                if (listaBoletos.size() >= 1) {
                                    System.out.println("");
                                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                                    System.out.println("|                                                  Tabla de Boletos Vendidos                                                                          |");
                                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                                    System.out.println("|ID   |P.ID |Nombre           |S.ID |O.ID |Estacion_Origen |D.ID |Estacion_Destino  |T.ID |Nombre Tren      |Tipo Boleto      |Fecha Boleto           |");
                                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                                    for (int i = 0; i < listaBoletos.size(); i++) {
                                        System.out.printf("|%-4s |%-4s |%-16s |%-4s |%-4s |%-16s |%-4s |%-16s |%-4s |%-16s |%-15s  |%-20s |", listaBoletos.get(i).getBoleto_id(), listaBoletos.get(i).getPasajero_id(),
                                                listaBoletos.get(i).getNombre_pasajero(), listaBoletos.get(i).getSalida_id(), listaBoletos.get(i).getOrigen_id(),
                                                listaBoletos.get(i).getOrigen_nombre(), listaBoletos.get(i).getDestino_id(), listaBoletos.get(i).getDestino_nombre(), listaBoletos.get(i).getTren_id(), listaBoletos.get(i).getTren_nombre(),
                                                (listaBoletos.get(i).getTipo_boleto().equalsIgnoreCase("General")
                                                ? listaBoletos.get(i).getTipo_boleto() + "   " : listaBoletos.get(i).getTipo_boleto()), listaBoletos.get(i).getFecha_boleto());
                                        System.out.println("");
                                    }
                                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                                    System.out.println("");
                                    System.out.println("");
                                    idBoleto = null;
                                    do {
                                        try {
                                            System.out.println("Ingrese el id del boleto a Cancelar");
                                            int n = teclado.nextInt();
                                            idBoleto = n;
                                        } catch (Exception e) {
                                            teclado.nextLine();
                                            System.out.println("Porfavor ingrese digitos");
                                        }
                                        if (idBoleto != null) {
                                            if (listaBoletos.indexOf(new BoletoDetalle(idBoleto)) >= 0) {
                                                int indice = listaBoletos.indexOf(new BoletoDetalle(idBoleto));
                                                System.out.println("-------------------------------------------");
                                                System.out.println("|          Datos del boleto               |");
                                                System.out.println("-------------------------------------------");
                                                System.out.printf("|%s %-27s |", "Id. Boleto; ", listaBoletos.get(indice).getBoleto_id());
                                                System.out.println("");
                                                System.out.printf("|%s %-25s |", "Id. Pasajero; ", listaBoletos.get(indice).getPasajero_id());
                                                System.out.println("");
                                                System.out.printf("|%s %-22s |", "Nombre Pasajero; ", listaBoletos.get(indice).getNombre_pasajero());
                                                System.out.println("");
                                                System.out.printf("|%s %-27s |", "Id. Salida; ", listaBoletos.get(indice).getSalida_id());
                                                System.out.println("");
                                                System.out.printf("|%s %-30s |", "Orgien ID", listaBoletos.get(indice).getOrigen_id());
                                                System.out.println("");
                                                System.out.printf("|%s %-22s |", "Estacion_Origen; ", listaBoletos.get(indice).getOrigen_nombre());
                                                System.out.println("");
                                                System.out.printf("|%s %-29s |", "Destino_ID", listaBoletos.get(indice).getDestino_id());
                                                System.out.println("");
                                                System.out.printf("|%s %-21s |", "Estacion_Destino; ", listaBoletos.get(indice).getDestino_nombre());
                                                System.out.println("");
                                                System.out.printf("|%s %-31s |", "ID. Tren", listaBoletos.get(indice).getTren_id());
                                                System.out.println("");
                                                System.out.printf("|%s %-26s |", "Nombre_Tren; ", listaBoletos.get(indice).getTren_nombre());
                                                System.out.println("");
                                                System.out.printf("|%s %-26s |", "Tipo Boleto; ", listaBoletos.get(indice).getTipo_boleto());
                                                System.out.println("");
                                                System.out.printf("|%s %-22s |", "Fecha De Compra; ", listaBoletos.get(indice).getFecha_boleto());
                                                System.out.println("");
                                                System.out.println("-------------------------------------------");
                                                System.out.println("");
                                                teclado.nextLine();
                                                do {
                                                    System.out.println("¿Desea cancelar el boleto (y/n)?");
                                                    String cadenaD = teclado.nextLine();
                                                    if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                                        if (cadenaD.equalsIgnoreCase("Y")) {
                                                            BoletoDetalle L = boletoPersistencia.obtenerBoletoDetallado(idBoleto);
                                                            boletoPersistencia.cancelarBoleto(idBoleto);
                                                            if (L.getTipo_boleto().equalsIgnoreCase("General")) {
                                                                salidaPersistencia.actualizarDisponibilidaGeneral(L.getSalida_id(), 1);
                                                                ArrayList<BoletoDetalle> listaEspera = boletoPersistencia.consultarTodosEsperaParaPreguntar(L.getSalida_id());
                                                                if (listaEspera.size() > 0) {
                                                                    if (listaEspera.get(0).getTipo_boleto().equalsIgnoreCase("General")) {
                                                                        System.out.println("");
                                                                        System.out.println("-------------------------------------------");
                                                                        System.out.println("|          Datos del boleto               |");
                                                                        System.out.println("-------------------------------------------");
                                                                        System.out.printf("|%s %-27s |", "Id. Boleto; ", listaEspera.get(0).getBoleto_id());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-25s |", "Id. Pasajero; ", listaEspera.get(0).getPasajero_id());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-22s |", "Nombre Pasajero; ", listaEspera.get(0).getNombre_pasajero());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-27s |", "Id. Salida; ", listaEspera.get(0).getSalida_id());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-30s |", "Orgien ID", listaEspera.get(0).getOrigen_id());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-22s |", "Estacion_Origen; ", listaEspera.get(0).getOrigen_nombre());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-29s |", "Destino_ID", listaEspera.get(0).getDestino_id());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-21s |", "Estacion_Destino; ", listaEspera.get(0).getDestino_nombre());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-31s |", "ID. Tren", listaEspera.get(0).getTren_id());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-26s |", "Nombre_Tren; ", listaEspera.get(0).getTren_nombre());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-26s |", "Tipo Boleto; ", listaEspera.get(0).getTipo_boleto());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-22s |", "Fecha De Compra; ", listaEspera.get(0).getFecha_boleto());
                                                                        System.out.println("");
                                                                        System.out.println("-------------------------------------------");
                                                                        System.out.println("");
                                                                        do {
                                                                            System.out.println("¿Desea comprar o vender el boleto mostrado(y/n)?");
                                                                            String cadenah = teclado.nextLine();
                                                                            if (cadenah.length() != 0 && cadenah.length() <= 1) {
                                                                                if (cadenaD.equalsIgnoreCase("Y")) {
                                                                                    salidaPersistencia.actualizarDisponibilidaGeneral(L.getSalida_id(), 0);
                                                                                    boletoPersistencia.restablecerBoleto(listaEspera.get(0).getBoleto_id());
                                                                                    salidaPersistencia.actualizarDisponibilidaEspera(listaEspera.get(0).getSalida_id(), 1);
                                                                                    break;
                                                                                } else if (cadenah.equalsIgnoreCase("N")) {
                                                                                    break;
                                                                                } else {
                                                                                    System.out.println("Ingrese una opcion valida");
                                                                                }
                                                                            } else {
                                                                                if (cadenah.length() == 0) {
                                                                                    System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                                                                } else {
                                                                                    System.out.println("El maximo de caracteres permitos es de 1");
                                                                                }
                                                                            }
                                                                        } while (true);
                                                                        System.out.println("");
                                                                    }
                                                                }
                                                            } else if (L.getTipo_boleto().equalsIgnoreCase("Primera Clase")) {
                                                                salidaPersistencia.actualizarDisponibilidaPrimera(L.getSalida_id(), 1);
                                                                ArrayList<BoletoDetalle> listaEspera = boletoPersistencia.consultarTodosEsperaParaPreguntar(L.getSalida_id());
                                                                if (listaEspera.size() > 0) {
                                                                    if (listaEspera.get(0).getTipo_boleto().equalsIgnoreCase("Primera Clase")) {
                                                                        System.out.println("");
                                                                        System.out.println("-------------------------------------------");
                                                                        System.out.println("|          Datos del boleto               |");
                                                                        System.out.println("-------------------------------------------");
                                                                        System.out.printf("|%s %-27s |", "Id. Boleto; ", listaEspera.get(0).getBoleto_id());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-25s |", "Id. Pasajero; ", listaEspera.get(0).getPasajero_id());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-22s |", "Nombre Pasajero; ", listaEspera.get(0).getNombre_pasajero());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-27s |", "Id. Salida; ", listaEspera.get(0).getSalida_id());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-30s |", "Orgien ID", listaEspera.get(0).getOrigen_id());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-22s |", "Estacion_Origen; ", listaEspera.get(0).getOrigen_nombre());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-29s |", "Destino_ID", listaEspera.get(0).getDestino_id());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-21s |", "Estacion_Destino; ", listaEspera.get(0).getDestino_nombre());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-31s |", "ID. Tren", listaEspera.get(0).getTren_id());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-26s |", "Nombre_Tren; ", listaEspera.get(0).getTren_nombre());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-26s |", "Tipo Boleto; ", listaEspera.get(0).getTipo_boleto());
                                                                        System.out.println("");
                                                                        System.out.printf("|%s %-22s |", "Fecha De Compra; ", listaEspera.get(0).getFecha_boleto());
                                                                        System.out.println("");
                                                                        System.out.println("-------------------------------------------");
                                                                        System.out.println("");
                                                                        do {
                                                                            System.out.println("¿Desea comprar o vender el boleto mostrado(y/n)?");
                                                                            String cadenah = teclado.nextLine();
                                                                            if (cadenah.length() != 0 && cadenah.length() <= 1) {
                                                                                if (cadenaD.equalsIgnoreCase("Y")) {
                                                                                    salidaPersistencia.actualizarDisponibilidaPrimera(L.getSalida_id(), 0);
                                                                                    boletoPersistencia.restablecerBoleto(listaEspera.get(0).getBoleto_id());
                                                                                    salidaPersistencia.actualizarDisponibilidaEspera(listaEspera.get(0).getSalida_id(), 1);
                                                                                    break;
                                                                                } else if (cadenah.equalsIgnoreCase("N")) {
                                                                                    break;
                                                                                } else {
                                                                                    System.out.println("Ingrese una opcion valida");
                                                                                }
                                                                            } else {
                                                                                if (cadenah.length() == 0) {
                                                                                    System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                                                                } else {
                                                                                    System.out.println("El maximo de caracteres permitos es de 1");
                                                                                }
                                                                            }
                                                                        } while (true);
                                                                        System.out.println("");
                                                                    }
                                                                }
                                                            }
                                                            break;
                                                        } else if (cadenaD.equalsIgnoreCase("N")) {
                                                            break;
                                                        } else {
                                                            System.out.println("Ingrese una opcion valida");
                                                        }
                                                    } else {
                                                        if (cadenaD.length() == 0) {
                                                            System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                                        } else {
                                                            System.out.println("El maximo de caracteres permitos es de 1");
                                                        }
                                                    }
                                                } while (true);
                                                System.out.println("");
                                                break;
                                            } else {
                                                System.out.println("Porfavor eliga un id de los que estan ingresados");
                                            }
                                            break;
                                        }
                                    } while (true);
                                } else {
                                    System.out.println("El pasajero aun no ha comprado boletos");
                                }
                                break;
                            }
                        } while (true);
//
                    } else {
                        System.out.println("No existen pasajeros que coincidan con lo ingresado");
                    }
                    break;

                case 0:
                    opcion2 = false;
                    break;
                default:
                    break;
            }
            System.out.println(
                    "");
            do {
                System.out.println("¿Desea cancelar otro boleto (y/n)?");
                String cadenaD = teclado.nextLine();
                if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                    if (cadenaD.equalsIgnoreCase("Y")) {
                        break;
                    } else if (cadenaD.equalsIgnoreCase("N")) {
                        opcion2 = false;
                        break;
                    } else {
                        System.out.println("Ingrese una opcion valida");
                    }
                } else {
                    if (cadenaD.length() == 0) {
                        System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                    } else {
                        System.out.println("El maximo de caracteres permitos es de 1");
                    }
                }
            } while (true);
            System.out.println(
                    "");

        }
        teclado.nextLine();

        System.out.println(
                "");
    }

    public void opcion3() {
        System.out.println("");
        Scanner teclado = new Scanner(System.in);
        boolean opcion3 = true;
        ArrayList<BoletoDetalle> listaActual = boletoPersistencia.consultarTodosCancelados();
        if (listaActual != null) {
            while (opcion3) {
                System.out.println("----------------------------------------------");
                System.out.println("|♥♦♣♠     Menu Boleto Cancelados      ♥♦♣♠|");
                System.out.println("----------------------------------------------");
                System.out.println("|     Opciones           |      Tecla        |");
                System.out.println("----------------------------------------------");
                System.out.println("| 1. Todos               |          1        | ");
                System.out.println("| 2. Por Tren            |          2        | ");
                System.out.println("| 3. Por Salida          |          3        | ");
                System.out.println("| 4. Por Cliente         |          4        | ");
                System.out.println("| 5. Salir               |          0        | ");
                System.out.println("----------------------------------------------");
                System.out.println("");
                Integer auxiliar = as.opcionesMenuRangos(4);
                switch (auxiliar) {
                    case 1:
                        System.out.println("");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|                                                  Tabla de Boletos Vendidos                                                                          |");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |P.ID |Nombre           |S.ID |O.ID |Estacion_Origen |D.ID |Estacion_Destino  |T.ID |Nombre Tren      |Tipo Boleto      |Fecha Boleto           |");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                        for (int i = 0; i < listaActual.size(); i++) {
                            System.out.printf("|%-4s |%-4s |%-16s |%-4s |%-4s |%-16s |%-4s |%-16s |%-4s |%-16s |%-15s  |%-20s |", listaActual.get(i).getBoleto_id(), listaActual.get(i).getPasajero_id(),
                                    listaActual.get(i).getNombre_pasajero(), listaActual.get(i).getSalida_id(), listaActual.get(i).getOrigen_id(),
                                    listaActual.get(i).getOrigen_nombre(), listaActual.get(i).getDestino_id(), listaActual.get(i).getDestino_nombre(), listaActual.get(i).getTren_id(), listaActual.get(i).getTren_nombre(),
                                    (listaActual.get(i).getTipo_boleto().equalsIgnoreCase("General")
                                    ? listaActual.get(i).getTipo_boleto() + "   " : listaActual.get(i).getTipo_boleto()), listaActual.get(i).getFecha_boleto());
                            System.out.println("");
                        }
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                        System.out.println("");
                        break;
                    case 2:
                        ArrayList<Tren> listaTren = trenPersistencia.consultarTodos();
                        System.out.println("");
                        System.out.println("--------------------------------------------------------------------------------------------------");
                        System.out.println("|                                     Tabla de Trenes                                            |");
                        System.out.println("--------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |Nombre               |Modelo     |Año  |Num. Pasajeros  |Asientos P.C.   |Dias Operación  |");
                        System.out.println("--------------------------------------------------------------------------------------------------");
                        for (int i = 0; i < listaTren.size(); i++) {
                            System.out.printf("|%-4s |%-20s |%-10s |%-4s |%-15s |%-15s |%-15s |", listaTren.get(i).getTren_id(), listaTren.get(i).getNomdre(), listaTren.get(i).getModelo(), listaTren.get(i).getAño(), listaTren.get(i).getNumero_pasajeros(), listaTren.get(i).getAsientos_primera(), listaTren.get(i).getDias_operacion());
                            System.out.println("");
                        }
                        System.out.println("--------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        Integer idTren = null;
                        do {
                            try {
                                System.out.println("Ingrese el id del tren");
                                int n = teclado.nextInt();
                                if (listaTren.indexOf(new Tren(n)) < 0) {
                                    System.out.println("El id ingresado no corresponde con alguno de los trenes en la base de datos ");
                                    System.out.println("Estos son los id de los trenes actuales");
                                    for (int i = 0; i < listaTren.size(); i++) {
                                        System.out.print(listaTren.get(i).getTren_id() + ",");
                                    }
                                } else {
                                    idTren = listaTren.get(listaTren.indexOf(new Tren(n))).getTren_id();
                                    break;
                                }
                            } catch (Exception e) {
                                teclado.nextLine();
                                System.out.println("Porfavor ingrese digitos");
                            }
                        } while (true);
                        teclado.nextLine();
                        ArrayList<BoletoDetalle> listaActualF = boletoPersistencia.consultarTodosFiltroCancelados("t.tren_id=" + idTren);
                        if (listaActualF != null) {
                            System.out.println("");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|                                                  Tabla de Boletos Vendidos                                                                          |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|ID   |P.ID |Nombre           |S.ID |O.ID |Estacion_Origen |D.ID |Estacion_Destino  |T.ID |Nombre Tren      |Tipo Boleto      |Fecha Boleto           |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                            for (int i = 0; i < listaActualF.size(); i++) {
                                System.out.printf("|%-4s |%-4s |%-16s |%-4s |%-4s |%-16s |%-4s |%-16s |%-4s |%-16s |%-15s  |%-20s |", listaActualF.get(i).getBoleto_id(), listaActualF.get(i).getPasajero_id(),
                                        listaActualF.get(i).getNombre_pasajero(), listaActualF.get(i).getSalida_id(), listaActualF.get(i).getOrigen_id(),
                                        listaActualF.get(i).getOrigen_nombre(), listaActualF.get(i).getDestino_id(), listaActualF.get(i).getDestino_nombre(), listaActualF.get(i).getTren_id(), listaActualF.get(i).getTren_nombre(),
                                        (listaActualF.get(i).getTipo_boleto().equalsIgnoreCase("General")
                                        ? listaActualF.get(i).getTipo_boleto() + "   " : listaActualF.get(i).getTipo_boleto()), listaActualF.get(i).getFecha_boleto());
                                System.out.println("");
                            }
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                            System.out.println("");
                        } else {
                            System.out.println("No existen boletos vendidos de ese tren");
                        }
                        break;
                    case 3:
                        ArrayList<Salida> listaActualA = salidaPersistencia.consultarTodos();
                        System.out.println("");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|                                                      Tabla de Salidas                                                              |");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |Tren |Origen |Destino |Fecha       |Hora    |Precio B.General |Precio BPrimClas |Dispo. General|Dospo. Primera |Dispo. Espera |");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        for (int i = 0; i < listaActualA.size(); i++) {
                            System.out.printf("|%-4s |%-4s |%-6s |%-7s |%-11s |%-4s |%-16s |%-16s |%-13s |%-13s |%-13s |", listaActualA.get(i).getSalida_id(), listaActualA.get(i).getTren_id(),
                                    listaActualA.get(i).getOrigen_id(), listaActualA.get(i).getDestino_id(), listaActualA.get(i).getFecha(),
                                    (listaActualA.get(i).getHora().length() == 6 ? listaActualA.get(i).getHora() + "0" : listaActualA.get(i).getHora()), listaActualA.get(i).getPrecio_boleto_general(), listaActualA.get(i).getPrecio_boleto_p_clase(),
                                    listaActualA.get(i).getDisponibilidad_general(), listaActualA.get(i).getDisponibilidad_a_primera_c(), listaActualA.get(i).getDisponibilidad_espera());
                            System.out.println("");
                        }
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        Integer idSalida = null;
                        do {
                            try {
                                System.out.println("Ingrese el id de la salida");
                                int n = teclado.nextInt();
                                if (listaActualA.indexOf(new Salida(n)) < 0) {
                                    System.out.println("El id ingresado no corresponde con alguna de las salidas en la base de datos ");
                                    System.out.println("Estos son los id de las salidass actuales");
                                    for (int i = 0; i < listaActualA.size(); i++) {
                                        System.out.print(listaActualA.get(i).getSalida_id() + ",");
                                    }
                                } else {
                                    idSalida = listaActualA.get(listaActualA.indexOf(new Salida(n))).getSalida_id();
                                    break;
                                }
                            } catch (Exception e) {
                                teclado.nextLine();
                                System.out.println("Porfavor ingrese digitos");
                            }
                        } while (true);
                        teclado.nextLine();
                        listaActualF = boletoPersistencia.consultarTodosFiltroCancelados("s.salida_id=" + idSalida);
                        if (listaActualF != null) {
                            System.out.println("");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|                                                  Tabla de Boletos Vendidos                                                                          |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|ID   |P.ID |Nombre           |S.ID |O.ID |Estacion_Origen |D.ID |Estacion_Destino  |T.ID |Nombre Tren      |Tipo Boleto      |Fecha Boleto           |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                            for (int i = 0; i < listaActualF.size(); i++) {
                                System.out.printf("|%-4s |%-4s |%-16s |%-4s |%-4s |%-16s |%-4s |%-16s |%-4s |%-16s |%-15s  |%-20s |", listaActualF.get(i).getBoleto_id(), listaActualF.get(i).getPasajero_id(),
                                        listaActualF.get(i).getNombre_pasajero(), listaActualF.get(i).getSalida_id(), listaActualF.get(i).getOrigen_id(),
                                        listaActualF.get(i).getOrigen_nombre(), listaActualF.get(i).getDestino_id(), listaActualF.get(i).getDestino_nombre(), listaActualF.get(i).getTren_id(), listaActualF.get(i).getTren_nombre(),
                                        (listaActualF.get(i).getTipo_boleto().equalsIgnoreCase("General")
                                        ? listaActualF.get(i).getTipo_boleto() + "   " : listaActualF.get(i).getTipo_boleto()), listaActualF.get(i).getFecha_boleto());
                                System.out.println("");
                            }
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                            System.out.println("");
                        } else {
                            System.out.println("No existen boletos vendidos de ese salida");
                        }
                        break;
                    case 4:
                        ArrayList<Pasajero> listaActualP = pasajeroPersistencia.consultarTodos();
                        System.out.println("");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|                                                           Tabla de Pasajeros                                                                 |");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |Nombre          |Apellido Paterno|Apellido Materno|Fecha Nacimi.|Gen. |Telefono     |Direccion                                          |");
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
                        for (int i = 0; i < listaActualP.size(); i++) {
                            System.out.printf("|%-4s |%-15s |%-15s |%-15s |%-12s |%-4s |%-12s |%-50s |", listaActualP.get(i).getPasajero_id(), listaActualP.get(i).getNombre(), listaActualP.get(i).getApellido_paterno(), listaActualP.get(i).getApellido_materno(), listaActualP.get(i).getFecha_nacimiento(), listaActualP.get(i).getGenero(), listaActualP.get(i).getTelefono(), listaActualP.get(i).getDireccion());
                            System.out.println("");
                        }
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        Integer idPasajero = null;
                        do {
                            try {
                                System.out.println("Ingrese el id del pasajero");
                                int n = teclado.nextInt();
                                if (listaActualP.indexOf(new Pasajero(n)) < 0) {
                                    System.out.println("El id ingresado no corresponde con alguno de los clientes en la base de datos ");
                                    System.out.println("Estos son los id de los clientes actuales");
                                    for (int i = 0; i < listaActualP.size(); i++) {
                                        System.out.print(listaActualP.get(i).getPasajero_id() + ",");
                                    }
                                } else {
                                    idPasajero = listaActualP.get(listaActualP.indexOf(new Pasajero(n))).getPasajero_id();
                                    break;
                                }
                            } catch (Exception e) {
                                teclado.nextLine();
                                System.out.println("Porfavor ingrese digitos");
                            }
                        } while (true);
                        teclado.nextLine();
                        listaActualF = boletoPersistencia.consultarTodosFiltroCancelados("p.pasajero_id=" + idPasajero);
                        if (listaActualF != null) {
                            System.out.println("");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|                                                  Tabla de Boletos Vendidos                                                                          |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|ID   |P.ID |Nombre           |S.ID |O.ID |Estacion_Origen |D.ID |Estacion_Destino  |T.ID |Nombre Tren      |Tipo Boleto      |Fecha Boleto           |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                            for (int i = 0; i < listaActualF.size(); i++) {
                                System.out.printf("|%-4s |%-4s |%-16s |%-4s |%-4s |%-16s |%-4s |%-16s |%-4s |%-16s |%-15s  |%-20s |", listaActualF.get(i).getBoleto_id(), listaActualF.get(i).getPasajero_id(),
                                        listaActualF.get(i).getNombre_pasajero(), listaActualF.get(i).getSalida_id(), listaActualF.get(i).getOrigen_id(),
                                        listaActualF.get(i).getOrigen_nombre(), listaActualF.get(i).getDestino_id(), listaActualF.get(i).getDestino_nombre(), listaActualF.get(i).getTren_id(), listaActualF.get(i).getTren_nombre(),
                                        (listaActualF.get(i).getTipo_boleto().equalsIgnoreCase("General")
                                        ? listaActualF.get(i).getTipo_boleto() + "   " : listaActualF.get(i).getTipo_boleto()), listaActualF.get(i).getFecha_boleto());
                                System.out.println("");
                            }
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                            System.out.println("");
                        } else {
                            System.out.println("No existen boletos vendidos de ese salida");
                        }
                        break;
                    case 0:
                        opcion3 = false;
                        break;
                    default:
                        break;
                }
            }
        } else {
            System.out.println("No existen boletos para mostrar");
        }
        teclado.nextLine();
        System.out.println("");
    }

    public void opcion4() {
        System.out.println("");
        Scanner teclado = new Scanner(System.in);
        boolean opcion4 = true;
        ArrayList<BoletoDetalle> listaActual = boletoPersistencia.consultarTodosEspera();
        if (listaActual != null) {
            while (opcion4) {
                System.out.println("---------------------------------------------");
                System.out.println("|♥♦♣♠     Menu Boleto Espera          ♥♦♣♠|");
                System.out.println("---------------------------------------------");
                System.out.println("|     Opciones           |      Tecla        |");
                System.out.println("----------------------------------------------");
                System.out.println("| 1. Todos               |          1        | ");
                System.out.println("| 2. Por Tren            |          2        | ");
                System.out.println("| 3. Por Salida          |          3        | ");
                System.out.println("| 4. Por Cliente         |          4        | ");
                System.out.println("| 5. Salir               |          0        | ");
                System.out.println("----------------------------------------------");
                System.out.println("");
                Integer auxiliar = as.opcionesMenuRangos(4);
                switch (auxiliar) {
                    case 1:
                        System.out.println("");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|                                                  Tabla de Boletos Vendidos                                                                          |");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |P.ID |Nombre           |S.ID |O.ID |Estacion_Origen |D.ID |Estacion_Destino  |T.ID |Nombre Tren      |Tipo Boleto      |Fecha Boleto           |");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                        for (int i = 0; i < listaActual.size(); i++) {
                            System.out.printf("|%-4s |%-4s |%-16s |%-4s |%-4s |%-16s |%-4s |%-16s |%-4s |%-16s |%-15s  |%-20s |", listaActual.get(i).getBoleto_id(), listaActual.get(i).getPasajero_id(),
                                    listaActual.get(i).getNombre_pasajero(), listaActual.get(i).getSalida_id(), listaActual.get(i).getOrigen_id(),
                                    listaActual.get(i).getOrigen_nombre(), listaActual.get(i).getDestino_id(), listaActual.get(i).getDestino_nombre(), listaActual.get(i).getTren_id(), listaActual.get(i).getTren_nombre(),
                                    (listaActual.get(i).getTipo_boleto().equalsIgnoreCase("General")
                                    ? listaActual.get(i).getTipo_boleto() + "   " : listaActual.get(i).getTipo_boleto()), listaActual.get(i).getFecha_boleto());
                            System.out.println("");
                        }
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                        System.out.println("");
                        break;
                    case 2:
                        ArrayList<Tren> listaTren = trenPersistencia.consultarTodos();
                        System.out.println("");
                        System.out.println("--------------------------------------------------------------------------------------------------");
                        System.out.println("|                                     Tabla de Trenes                                            |");
                        System.out.println("--------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |Nombre               |Modelo     |Año  |Num. Pasajeros  |Asientos P.C.   |Dias Operación  |");
                        System.out.println("--------------------------------------------------------------------------------------------------");
                        for (int i = 0; i < listaTren.size(); i++) {
                            System.out.printf("|%-4s |%-20s |%-10s |%-4s |%-15s |%-15s |%-15s |", listaTren.get(i).getTren_id(), listaTren.get(i).getNomdre(), listaTren.get(i).getModelo(), listaTren.get(i).getAño(), listaTren.get(i).getNumero_pasajeros(), listaTren.get(i).getAsientos_primera(), listaTren.get(i).getDias_operacion());
                            System.out.println("");
                        }
                        System.out.println("--------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        Integer idTren = null;
                        do {
                            try {
                                System.out.println("Ingrese el id del tren");
                                int n = teclado.nextInt();
                                if (listaTren.indexOf(new Tren(n)) < 0) {
                                    System.out.println("El id ingresado no corresponde con alguno de los trenes en la base de datos ");
                                    System.out.println("Estos son los id de los trenes actuales");
                                    for (int i = 0; i < listaTren.size(); i++) {
                                        System.out.print(listaTren.get(i).getTren_id() + ",");
                                    }
                                } else {
                                    idTren = listaTren.get(listaTren.indexOf(new Tren(n))).getTren_id();
                                    break;
                                }
                            } catch (Exception e) {
                                teclado.nextLine();
                                System.out.println("Porfavor ingrese digitos");
                            }
                        } while (true);
                        teclado.nextLine();
                        ArrayList<BoletoDetalle> listaActualF = boletoPersistencia.consultarTodosFiltroEspera("t.tren_id=" + idTren);
                        if (listaActualF != null) {
                            System.out.println("");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|                                                  Tabla de Boletos Vendidos                                                                          |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|ID   |P.ID |Nombre           |S.ID |O.ID |Estacion_Origen |D.ID |Estacion_Destino  |T.ID |Nombre Tren      |Tipo Boleto      |Fecha Boleto           |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                            for (int i = 0; i < listaActualF.size(); i++) {
                                System.out.printf("|%-4s |%-4s |%-16s |%-4s |%-4s |%-16s |%-4s |%-16s |%-4s |%-16s |%-15s  |%-20s |", listaActualF.get(i).getBoleto_id(), listaActualF.get(i).getPasajero_id(),
                                        listaActualF.get(i).getNombre_pasajero(), listaActualF.get(i).getSalida_id(), listaActualF.get(i).getOrigen_id(),
                                        listaActualF.get(i).getOrigen_nombre(), listaActualF.get(i).getDestino_id(), listaActualF.get(i).getDestino_nombre(), listaActualF.get(i).getTren_id(), listaActualF.get(i).getTren_nombre(),
                                        (listaActualF.get(i).getTipo_boleto().equalsIgnoreCase("General")
                                        ? listaActualF.get(i).getTipo_boleto() + "   " : listaActualF.get(i).getTipo_boleto()), listaActualF.get(i).getFecha_boleto());
                                System.out.println("");
                            }
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                            System.out.println("");
                        } else {
                            System.out.println("No existen boletos vendidos de ese tren");
                        }
                        break;
                    case 3:
                        ArrayList<Salida> listaActualA = salidaPersistencia.consultarTodos();
                        System.out.println("");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|                                                      Tabla de Salidas                                                              |");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |Tren |Origen |Destino |Fecha       |Hora    |Precio B.General |Precio BPrimClas |Dispo. General|Dospo. Primera |Dispo. Espera |");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        for (int i = 0; i < listaActualA.size(); i++) {
                            System.out.printf("|%-4s |%-4s |%-6s |%-7s |%-11s |%-4s |%-16s |%-16s |%-13s |%-13s |%-13s |", listaActualA.get(i).getSalida_id(), listaActualA.get(i).getTren_id(),
                                    listaActualA.get(i).getOrigen_id(), listaActualA.get(i).getDestino_id(), listaActualA.get(i).getFecha(),
                                    (listaActualA.get(i).getHora().length() == 6 ? listaActualA.get(i).getHora() + "0" : listaActualA.get(i).getHora()), listaActualA.get(i).getPrecio_boleto_general(), listaActualA.get(i).getPrecio_boleto_p_clase(),
                                    listaActualA.get(i).getDisponibilidad_general(), listaActualA.get(i).getDisponibilidad_a_primera_c(), listaActualA.get(i).getDisponibilidad_espera());
                            System.out.println("");
                        }
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        Integer idSalida = null;
                        do {
                            try {
                                System.out.println("Ingrese el id de la salida");
                                int n = teclado.nextInt();
                                if (listaActualA.indexOf(new Salida(n)) < 0) {
                                    System.out.println("El id ingresado no corresponde con alguna de las salidas en la base de datos ");
                                    System.out.println("Estos son los id de las salidass actuales");
                                    for (int i = 0; i < listaActualA.size(); i++) {
                                        System.out.print(listaActualA.get(i).getSalida_id() + ",");
                                    }
                                } else {
                                    idSalida = listaActualA.get(listaActualA.indexOf(new Salida(n))).getSalida_id();
                                    break;
                                }
                            } catch (Exception e) {
                                teclado.nextLine();
                                System.out.println("Porfavor ingrese digitos");
                            }
                        } while (true);
                        teclado.nextLine();
                        listaActualF = boletoPersistencia.consultarTodosFiltroEspera("s.salida_id=" + idSalida);
                        if (listaActualF != null) {
                            System.out.println("");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|                                                  Tabla de Boletos Vendidos                                                                          |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|ID   |P.ID |Nombre           |S.ID |O.ID |Estacion_Origen |D.ID |Estacion_Destino  |T.ID |Nombre Tren      |Tipo Boleto      |Fecha Boleto           |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                            for (int i = 0; i < listaActualF.size(); i++) {
                                System.out.printf("|%-4s |%-4s |%-16s |%-4s |%-4s |%-16s |%-4s |%-16s |%-4s |%-16s |%-15s  |%-20s |", listaActualF.get(i).getBoleto_id(), listaActualF.get(i).getPasajero_id(),
                                        listaActualF.get(i).getNombre_pasajero(), listaActualF.get(i).getSalida_id(), listaActualF.get(i).getOrigen_id(),
                                        listaActualF.get(i).getOrigen_nombre(), listaActualF.get(i).getDestino_id(), listaActualF.get(i).getDestino_nombre(), listaActualF.get(i).getTren_id(), listaActualF.get(i).getTren_nombre(),
                                        (listaActualF.get(i).getTipo_boleto().equalsIgnoreCase("General")
                                        ? listaActualF.get(i).getTipo_boleto() + "   " : listaActualF.get(i).getTipo_boleto()), listaActualF.get(i).getFecha_boleto());
                                System.out.println("");
                            }
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                            System.out.println("");
                        } else {
                            System.out.println("No existen boletos vendidos de ese salida");
                        }
                        break;
                    case 4:
                        ArrayList<Pasajero> listaActualP = pasajeroPersistencia.consultarTodos();
                        System.out.println("");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|                                                           Tabla de Pasajeros                                                                 |");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |Nombre          |Apellido Paterno|Apellido Materno|Fecha Nacimi.|Gen. |Telefono     |Direccion                                          |");
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
                        for (int i = 0; i < listaActualP.size(); i++) {
                            System.out.printf("|%-4s |%-15s |%-15s |%-15s |%-12s |%-4s |%-12s |%-50s |", listaActualP.get(i).getPasajero_id(), listaActualP.get(i).getNombre(), listaActualP.get(i).getApellido_paterno(), listaActualP.get(i).getApellido_materno(), listaActualP.get(i).getFecha_nacimiento(), listaActualP.get(i).getGenero(), listaActualP.get(i).getTelefono(), listaActualP.get(i).getDireccion());
                            System.out.println("");
                        }
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        Integer idPasajero = null;
                        do {
                            try {
                                System.out.println("Ingrese el id del pasajero");
                                int n = teclado.nextInt();
                                if (listaActualP.indexOf(new Pasajero(n)) < 0) {
                                    System.out.println("El id ingresado no corresponde con alguno de los clientes en la base de datos ");
                                    System.out.println("Estos son los id de los clientes actuales");
                                    for (int i = 0; i < listaActualP.size(); i++) {
                                        System.out.print(listaActualP.get(i).getPasajero_id() + ",");
                                    }
                                } else {
                                    idPasajero = listaActualP.get(listaActualP.indexOf(new Pasajero(n))).getPasajero_id();
                                    break;
                                }
                            } catch (Exception e) {
                                teclado.nextLine();
                                System.out.println("Porfavor ingrese digitos");
                            }
                        } while (true);
                        teclado.nextLine();
                        listaActualF = boletoPersistencia.consultarTodosFiltroEspera("p.pasajero_id=" + idPasajero);
                        if (listaActualF != null) {
                            System.out.println("");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|                                                  Tabla de Boletos Vendidos                                                                          |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|ID   |P.ID |Nombre           |S.ID |O.ID |Estacion_Origen |D.ID |Estacion_Destino  |T.ID |Nombre Tren      |Tipo Boleto      |Fecha Boleto           |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                            for (int i = 0; i < listaActualF.size(); i++) {
                                System.out.printf("|%-4s |%-4s |%-16s |%-4s |%-4s |%-16s |%-4s |%-16s |%-4s |%-16s |%-15s  |%-20s |", listaActualF.get(i).getBoleto_id(), listaActualF.get(i).getPasajero_id(),
                                        listaActualF.get(i).getNombre_pasajero(), listaActualF.get(i).getSalida_id(), listaActualF.get(i).getOrigen_id(),
                                        listaActualF.get(i).getOrigen_nombre(), listaActualF.get(i).getDestino_id(), listaActualF.get(i).getDestino_nombre(), listaActualF.get(i).getTren_id(), listaActualF.get(i).getTren_nombre(),
                                        (listaActualF.get(i).getTipo_boleto().equalsIgnoreCase("General")
                                        ? listaActualF.get(i).getTipo_boleto() + "   " : listaActualF.get(i).getTipo_boleto()), listaActualF.get(i).getFecha_boleto());
                                System.out.println("");
                            }
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                            System.out.println("");
                        } else {
                            System.out.println("No existen boletos vendidos de ese salida");
                        }
                        break;
                    case 0:
                        opcion4 = false;
                        break;
                    default:
                        break;
                }
            }
        } else {
            System.out.println("No existen boletos para mostrar");
        }
        teclado.nextLine();
        System.out.println("");
    }

    public void opcion5() {
        System.out.println("");
        Scanner teclado = new Scanner(System.in);
        boolean opcion5 = true;
        ArrayList<BoletoDetalle> listaActual = boletoPersistencia.consultarTodosVendidos();
        if (listaActual != null) {
            while (opcion5) {
                System.out.println("---------------------------------------------");
                System.out.println("|♥♦♣♠     Menu Boleto Vendidos        ♥♦♣♠|");
                System.out.println("---------------------------------------------");
                System.out.println("|     Opciones           |      Tecla        |");
                System.out.println("----------------------------------------------");
                System.out.println("| 1. Todos               |          1        | ");
                System.out.println("| 2. Por Tren            |          2        | ");
                System.out.println("| 3. Por Salida          |          3        | ");
                System.out.println("| 4. Por Cliente         |          4        | ");
                System.out.println("| 5. Salir               |          0        | ");
                System.out.println("----------------------------------------------");
                System.out.println("");
                Integer auxiliar = as.opcionesMenuRangos(4);
                switch (auxiliar) {
                    case 1:
                        System.out.println("");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|                                                  Tabla de Boletos Vendidos                                                                          |");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |P.ID |Nombre           |S.ID |O.ID |Estacion_Origen |D.ID |Estacion_Destino  |T.ID |Nombre Tren      |Tipo Boleto      |Fecha Boleto           |");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                        for (int i = 0; i < listaActual.size(); i++) {
                            System.out.printf("|%-4s |%-4s |%-16s |%-4s |%-4s |%-16s |%-4s |%-16s |%-4s |%-16s |%-15s  |%-20s |", listaActual.get(i).getBoleto_id(), listaActual.get(i).getPasajero_id(),
                                    listaActual.get(i).getNombre_pasajero(), listaActual.get(i).getSalida_id(), listaActual.get(i).getOrigen_id(),
                                    listaActual.get(i).getOrigen_nombre(), listaActual.get(i).getDestino_id(), listaActual.get(i).getDestino_nombre(), listaActual.get(i).getTren_id(), listaActual.get(i).getTren_nombre(),
                                    (listaActual.get(i).getTipo_boleto().equalsIgnoreCase("General")
                                    ? listaActual.get(i).getTipo_boleto() + "   " : listaActual.get(i).getTipo_boleto()), listaActual.get(i).getFecha_boleto());
                            System.out.println("");
                        }
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                        System.out.println("");
                        break;
                    case 2:
                        ArrayList<Tren> listaTren = trenPersistencia.consultarTodos();
                        System.out.println("");
                        System.out.println("--------------------------------------------------------------------------------------------------");
                        System.out.println("|                                     Tabla de Trenes                                            |");
                        System.out.println("--------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |Nombre               |Modelo     |Año  |Num. Pasajeros  |Asientos P.C.   |Dias Operación  |");
                        System.out.println("--------------------------------------------------------------------------------------------------");
                        for (int i = 0; i < listaTren.size(); i++) {
                            System.out.printf("|%-4s |%-20s |%-10s |%-4s |%-15s |%-15s |%-15s |", listaTren.get(i).getTren_id(), listaTren.get(i).getNomdre(), listaTren.get(i).getModelo(), listaTren.get(i).getAño(), listaTren.get(i).getNumero_pasajeros(), listaTren.get(i).getAsientos_primera(), listaTren.get(i).getDias_operacion());
                            System.out.println("");
                        }
                        System.out.println("--------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        Integer idTren = null;
                        do {
                            try {
                                System.out.println("Ingrese el id del tren");
                                int n = teclado.nextInt();
                                if (listaTren.indexOf(new Tren(n)) < 0) {
                                    System.out.println("El id ingresado no corresponde con alguno de los trenes en la base de datos ");
                                    System.out.println("Estos son los id de los trenes actuales");
                                    for (int i = 0; i < listaTren.size(); i++) {
                                        System.out.print(listaTren.get(i).getTren_id() + ",");
                                    }
                                } else {
                                    idTren = listaTren.get(listaTren.indexOf(new Tren(n))).getTren_id();
                                    break;
                                }
                            } catch (Exception e) {
                                teclado.nextLine();
                                System.out.println("Porfavor ingrese digitos");
                            }
                        } while (true);
                        teclado.nextLine();
                        ArrayList<BoletoDetalle> listaActualF = boletoPersistencia.consultarTodosFiltroVendidos("t.tren_id=" + idTren);
                        if (listaActualF != null) {
                            System.out.println("");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|                                                  Tabla de Boletos Vendidos                                                                          |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|ID   |P.ID |Nombre           |S.ID |O.ID |Estacion_Origen |D.ID |Estacion_Destino  |T.ID |Nombre Tren      |Tipo Boleto      |Fecha Boleto           |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                            for (int i = 0; i < listaActualF.size(); i++) {
                                System.out.printf("|%-4s |%-4s |%-16s |%-4s |%-4s |%-16s |%-4s |%-16s |%-4s |%-16s |%-15s  |%-20s |", listaActualF.get(i).getBoleto_id(), listaActualF.get(i).getPasajero_id(),
                                        listaActualF.get(i).getNombre_pasajero(), listaActualF.get(i).getSalida_id(), listaActualF.get(i).getOrigen_id(),
                                        listaActualF.get(i).getOrigen_nombre(), listaActualF.get(i).getDestino_id(), listaActualF.get(i).getDestino_nombre(), listaActualF.get(i).getTren_id(), listaActualF.get(i).getTren_nombre(),
                                        (listaActualF.get(i).getTipo_boleto().equalsIgnoreCase("General")
                                        ? listaActualF.get(i).getTipo_boleto() + "   " : listaActualF.get(i).getTipo_boleto()), listaActualF.get(i).getFecha_boleto());
                                System.out.println("");
                            }
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                            System.out.println("");
                        } else {
                            System.out.println("No existen boletos vendidos de ese tren");
                        }
                        break;
                    case 3:
                        ArrayList<Salida> listaActualA = salidaPersistencia.consultarTodos();
                        System.out.println("");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|                                                      Tabla de Salidas                                                              |");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |Tren |Origen |Destino |Fecha       |Hora    |Precio B.General |Precio BPrimClas |Dispo. General|Dospo. Primera |Dispo. Espera |");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        for (int i = 0; i < listaActualA.size(); i++) {
                            System.out.printf("|%-4s |%-4s |%-6s |%-7s |%-11s |%-4s |%-16s |%-16s |%-13s |%-13s |%-13s |", listaActualA.get(i).getSalida_id(), listaActualA.get(i).getTren_id(),
                                    listaActualA.get(i).getOrigen_id(), listaActualA.get(i).getDestino_id(), listaActualA.get(i).getFecha(),
                                    (listaActualA.get(i).getHora().length() == 6 ? listaActualA.get(i).getHora() + "0" : listaActualA.get(i).getHora()), listaActualA.get(i).getPrecio_boleto_general(), listaActualA.get(i).getPrecio_boleto_p_clase(),
                                    listaActualA.get(i).getDisponibilidad_general(), listaActualA.get(i).getDisponibilidad_a_primera_c(), listaActualA.get(i).getDisponibilidad_espera());
                            System.out.println("");
                        }
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        Integer idSalida = null;
                        do {
                            try {
                                System.out.println("Ingrese el id de la salida");
                                int n = teclado.nextInt();
                                if (listaActualA.indexOf(new Salida(n)) < 0) {
                                    System.out.println("El id ingresado no corresponde con alguna de las salidas en la base de datos ");
                                    System.out.println("Estos son los id de las salidass actuales");
                                    for (int i = 0; i < listaActualA.size(); i++) {
                                        System.out.print(listaActualA.get(i).getSalida_id() + ",");
                                    }
                                } else {
                                    idSalida = listaActualA.get(listaActualA.indexOf(new Salida(n))).getSalida_id();
                                    break;
                                }
                            } catch (Exception e) {
                                teclado.nextLine();
                                System.out.println("Porfavor ingrese digitos");
                            }
                        } while (true);
                        teclado.nextLine();
                        listaActualF = boletoPersistencia.consultarTodosFiltroVendidos("s.salida_id=" + idSalida);
                        if (listaActualF != null) {
                            System.out.println("");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|                                                  Tabla de Boletos Vendidos                                                                          |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|ID   |P.ID |Nombre           |S.ID |O.ID |Estacion_Origen |D.ID |Estacion_Destino  |T.ID |Nombre Tren      |Tipo Boleto      |Fecha Boleto           |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                            for (int i = 0; i < listaActualF.size(); i++) {
                                System.out.printf("|%-4s |%-4s |%-16s |%-4s |%-4s |%-16s |%-4s |%-16s |%-4s |%-16s |%-15s  |%-20s |", listaActualF.get(i).getBoleto_id(), listaActualF.get(i).getPasajero_id(),
                                        listaActualF.get(i).getNombre_pasajero(), listaActualF.get(i).getSalida_id(), listaActualF.get(i).getOrigen_id(),
                                        listaActualF.get(i).getOrigen_nombre(), listaActualF.get(i).getDestino_id(), listaActualF.get(i).getDestino_nombre(), listaActualF.get(i).getTren_id(), listaActualF.get(i).getTren_nombre(),
                                        (listaActualF.get(i).getTipo_boleto().equalsIgnoreCase("General")
                                        ? listaActualF.get(i).getTipo_boleto() + "   " : listaActualF.get(i).getTipo_boleto()), listaActualF.get(i).getFecha_boleto());
                                System.out.println("");
                            }
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                            System.out.println("");
                        } else {
                            System.out.println("No existen boletos vendidos de ese salida");
                        }
                        break;
                    case 4:
                        ArrayList<Pasajero> listaActualP = pasajeroPersistencia.consultarTodos();
                        System.out.println("");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|                                                           Tabla de Pasajeros                                                                 |");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |Nombre          |Apellido Paterno|Apellido Materno|Fecha Nacimi.|Gen. |Telefono     |Direccion                                          |");
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
                        for (int i = 0; i < listaActualP.size(); i++) {
                            System.out.printf("|%-4s |%-15s |%-15s |%-15s |%-12s |%-4s |%-12s |%-50s |", listaActualP.get(i).getPasajero_id(), listaActualP.get(i).getNombre(), listaActualP.get(i).getApellido_paterno(), listaActualP.get(i).getApellido_materno(), listaActualP.get(i).getFecha_nacimiento(), listaActualP.get(i).getGenero(), listaActualP.get(i).getTelefono(), listaActualP.get(i).getDireccion());
                            System.out.println("");
                        }
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        Integer idPasajero = null;
                        do {
                            try {
                                System.out.println("Ingrese el id del pasajero");
                                int n = teclado.nextInt();
                                if (listaActualP.indexOf(new Pasajero(n)) < 0) {
                                    System.out.println("El id ingresado no corresponde con alguno de los clientes en la base de datos ");
                                    System.out.println("Estos son los id de los clientes actuales");
                                    for (int i = 0; i < listaActualP.size(); i++) {
                                        System.out.print(listaActualP.get(i).getPasajero_id() + ",");
                                    }
                                } else {
                                    idPasajero = listaActualP.get(listaActualP.indexOf(new Pasajero(n))).getPasajero_id();
                                    break;
                                }
                            } catch (Exception e) {
                                teclado.nextLine();
                                System.out.println("Porfavor ingrese digitos");
                            }
                        } while (true);
                        teclado.nextLine();
                        listaActualF = boletoPersistencia.consultarTodosFiltroVendidos("p.pasajero_id=" + idPasajero);
                        if (listaActualF != null) {
                            System.out.println("");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|                                                  Tabla de Boletos Vendidos                                                                          |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|ID   |P.ID |Nombre           |S.ID |O.ID |Estacion_Origen |D.ID |Estacion_Destino  |T.ID |Nombre Tren      |Tipo Boleto      |Fecha Boleto           |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                            for (int i = 0; i < listaActualF.size(); i++) {
                                System.out.printf("|%-4s |%-4s |%-16s |%-4s |%-4s |%-16s |%-4s |%-16s |%-4s |%-16s |%-15s  |%-20s |", listaActualF.get(i).getBoleto_id(), listaActualF.get(i).getPasajero_id(),
                                        listaActualF.get(i).getNombre_pasajero(), listaActualF.get(i).getSalida_id(), listaActualF.get(i).getOrigen_id(),
                                        listaActualF.get(i).getOrigen_nombre(), listaActualF.get(i).getDestino_id(), listaActualF.get(i).getDestino_nombre(), listaActualF.get(i).getTren_id(), listaActualF.get(i).getTren_nombre(),
                                        (listaActualF.get(i).getTipo_boleto().equalsIgnoreCase("General")
                                        ? listaActualF.get(i).getTipo_boleto() + "   " : listaActualF.get(i).getTipo_boleto()), listaActualF.get(i).getFecha_boleto());
                                System.out.println("");
                            }
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------.");
                            System.out.println("");
                        } else {
                            System.out.println("No existen boletos vendidos de ese salida");
                        }
                        break;
                    case 0:
                        opcion5 = false;
                        break;
                    default:
                        break;
                }
            }
        } else {
            System.out.println("No existen boletos para mostrar");
        }
        teclado.nextLine();
        System.out.println("");
    }

}
