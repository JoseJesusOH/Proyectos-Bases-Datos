package vista;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import objetosNegocio.Lugar;
import objetosNegocio.Salida;
import objetosNegocio.Tren;
import objetosServicio.Fecha;
import persistencia.LugarPersistencia;
import persistencia.SalidaPersistencia;
import persistencia.TrenPersistencia;

public class SalidaVista {

    private  int[] meses = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private  String[] arregloDias = {"LU", "MA", "MI", "JU", "VI", "SA", "DO"};
    private boolean salida = true;
    private SalidaPersistencia salidaPersistencia;
    private TrenPersistencia trenPersistencia;
    private LugarPersistencia lugarPersistencia;
   private Auxiliares a=new Auxiliares();
    public SalidaVista() {
        salidaPersistencia = new SalidaPersistencia();
        trenPersistencia = new TrenPersistencia();
        lugarPersistencia = new LugarPersistencia();
    }

    public void menuSalida() {
        System.out.println("");
        Scanner teclado = new Scanner(System.in);
        while (salida) {
            System.out.println("\033[0;34m--------------------------------------------------");
            System.out.println("\033[0;34m|♥♦♣♠              Menu Salidas           ♥♦♣♠|");
            System.out.println("\033[0;34m--------------------------------------------------");
            System.out.println("\033[0;34m|          Opciones          |       Tecla       |");
            System.out.println("\033[0;34m--------------------------------------------------");
            System.out.println("\033[0;34m| 1. Insertar                |          1        | ");
            System.out.println("\033[0;34m| 2. Actualizar              |          2        | ");
            System.out.println("\033[0;34m| 3. Eliminar                |          3        | ");
            System.out.println("\033[0;34m| 4. Consultar               |          4        | ");
            System.out.println("\033[0;34m| 5. Salir                   |          0        | ");
            System.out.println("\033[0;34m--------------------------------------------------");
            System.out.println("");
            Integer auxiliar = a.opcionesMenuRangos(4);
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
                case 0:
                    salida = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("");
    }

    /**
     * Inserta un componente
     */
    public void opcion1() {
        Scanner teclado = new Scanner(System.in);
        boolean opcion1 = true;
        System.out.println("");
        ArrayList<Tren> listaTren = trenPersistencia.consultarTodos();
        ArrayList<Lugar> listaLugar = lugarPersistencia.consultarTodos();
        if (listaLugar.size() >= 2 && listaTren.size() >= 1) {
            while (opcion1) {
                Integer idTren = null, idOrigen = null, idDestino = null, disponPri = 5, dispoPCla = 5, dispoEspe = 2;
                String fecha = "", hora = "", precioBG = "", precioBPC = "";
                Integer auxiliar = null;
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
                do {
                    System.out.println("");
                    System.out.println("Ingrese la fecha de la salida DD/MM/AAAA (10 caracteres)");
                    String cadenaD = teclado.nextLine();
                    if (cadenaD.length() != 0 && cadenaD.length() <= 10) {
                        if (a.validaFecha(cadenaD)) {
                            String fechad = a.validarDias(cadenaD);
                            if (fechad == null) {
                                System.out.println("La fecha contiene dias invalidos,verifique el mes o dia");
                            } else {
                                Fecha s = new Fecha(cadenaD);
                                LocalDate localDate = LocalDate.of(s.getYear(), s.getMonth(), s.getDay());
                                DayOfWeek dayOfWeek = DayOfWeek.from(localDate);
                                int val = dayOfWeek.getValue();
                                String ayudaDia = arregloDias[val - 1];
                                int auxiliarIndice = listaTren.indexOf(new Tren(idTren));
                                String diasOperacion = listaTren.get(auxiliarIndice).getDias_operacion();
                                if (diasOperacion.contains(ayudaDia)) {
                                    fecha = fechad;
                                    break;
                                } else {
                                    System.out.println("El tren no trabaja esos dias, vuelva a ingresar la fecha.");
                                }
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
                teclado.nextLine();
                do {
                    System.out.println("Porfavor ingrese la hora de la salida HH:MM Formato 24(5 caracteres)");
                    String cadenaD = teclado.nextLine();
                    if (cadenaD.length() != 0 && cadenaD.length() <= 5) {
                        if (a.validarHora(cadenaD)) {
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
                do {
                    System.out.println("Porfavor ingrese el precio del boleto general (10 caracteres)");
                    String cadenaD = teclado.nextLine();
                    if (cadenaD.length() != 0 && cadenaD.length() <= 10) {
                        if (a.validarDobles(cadenaD)) {
                            precioBG = cadenaD;
                            break;
                        } else {
                            System.out.println("Porfavor digite solo numeros, no letras");
                        }
                    } else {
                        if (cadenaD.length() == 0) {
                            System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                        } else {
                            System.out.println("El maximo de caracteres permitos es de 10");
                        }
                    }
                } while (true);
                do {
                    System.out.println("Prfavor ingrese el precio del boleto primera clase (10 caracteres)");
                    String cadenaD = teclado.nextLine();
                    if (cadenaD.length() != 0 && cadenaD.length() <= 10) {
                        if (a.validarDobles(cadenaD)) {
                            precioBPC = cadenaD;
                            break;
                        } else {
                            System.out.println("Porfavor digite solo numeros, no letras");
                        }
                    } else {
                        if (cadenaD.length() == 0) {
                            System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                        } else {
                            System.out.println("El maximo de caracteres permitos es de 10");
                        }
                    }
                } while (true);
                System.out.println("");
                System.out.println("");
                System.out.println("--------------------------------------------------");
                System.out.println("|                Confirmacion                    |");
                System.out.println("--------------------------------------------------");
                System.out.printf("|%s %-37s |", "Tren id; ", idTren);
                System.out.println("");
                System.out.printf("|%s %-35s |", "Origen id; ", idOrigen);
                System.out.println("");
                System.out.printf("|%s %-34s |", "Destino id; ", idDestino);
                System.out.println("");
                System.out.printf("|%s %-38s |", "Fecha ; ", fecha);
                System.out.println("");
                System.out.printf("|%s %-40s |", "Hora; ", hora);
                System.out.println("");
                System.out.printf("|%s %-24s |", "Precio boleto General ", precioBG);
                System.out.println("");
                System.out.printf("|%s %-18s |", "Precio boleto Primera Clase ", precioBPC);
                System.out.println("");
                System.out.printf("|%s %-23s |", "Disponibilidad General ", 5);
                System.out.println("");
                System.out.printf("|%s %-23s |", "Disponibilidad Primera ", 5);
                System.out.println("");
                System.out.printf("|%s %-24s |", "Disponibilidad Espera ", 2);
                System.out.println("");
                System.out.println("--------------------------------------------------");
                System.out.println("");
                do {
                    System.out.println("¿Desea guardar la salida(y/n)?");
                    String cadenaD = teclado.nextLine();
                    if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                        if (cadenaD.equalsIgnoreCase("Y")) {
                            Salida sf = new Salida(idTren, idOrigen, idDestino, fecha, hora, precioBG, precioBPC);
                            salidaPersistencia.insertarSalida(sf);
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
                do {
                    System.out.println("¿Desea guardar otra salida (y/n)?");
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

            }
        } else if (listaLugar.isEmpty() && listaTren.isEmpty()) {
            System.out.println("No es posible programar salidas se necesitas mas de un lugar de origen registrado y un tren registrado");
        } else {
            //Checar cuanto hay de cada uno y mencionar el porque 
        }
        System.out.println("");
    }

    public void opcion2() {
        boolean opcion2 = true;
        Scanner teclado = new Scanner(System.in);
        while (opcion2) {
            ArrayList<Salida> listaActual = salidaPersistencia.consultarTodos();
            if (listaActual != null) {
                System.out.println("");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("|                                                      Tabla de Salidas                                                              |");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("|ID   |Tren |Origen |Destino |Fecha       |Hora    |Precio B.General |Precio BPrimClas |Dispo. General|Dospo. Primera |Dispo. Espera |");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                for (int i = 0; i < listaActual.size(); i++) {
                    System.out.printf("|%-4s |%-4s |%-6s |%-7s |%-11s |%-4s |%-16s |%-16s |%-13s |%-13s |%-13s |", listaActual.get(i).getSalida_id(), listaActual.get(i).getTren_id(),
                            listaActual.get(i).getOrigen_id(), listaActual.get(i).getDestino_id(), listaActual.get(i).getFecha(),
                            (listaActual.get(i).getHora().length() == 6 ? listaActual.get(i).getHora() + "0" : listaActual.get(i).getHora()), listaActual.get(i).getPrecio_boleto_general(), listaActual.get(i).getPrecio_boleto_p_clase(),
                            listaActual.get(i).getDisponibilidad_general(), listaActual.get(i).getDisponibilidad_a_primera_c(), listaActual.get(i).getDisponibilidad_espera());
                    System.out.println("");
                }
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("");
                Salida s;
                Integer auxiliar = -1;
                do {
                    try {
                        System.out.println("Ingrese el id del salida a actualizar");
                        int n = teclado.nextInt();
                        auxiliar = n;
                    } catch (Exception e) {
                        teclado.nextLine();
                        System.out.println("Porfavor solo ingrese digitos");
                    }
                    teclado.nextLine();
                    if (auxiliar != -1) {
                        if (listaActual.indexOf(new Salida(auxiliar)) < 0) {
                            System.out.println("El id ingresado no corresponde a una de las salidas actuales.");
                            break;
                        } else {
                            teclado.nextLine();
                            s = salidaPersistencia.obtenerSalida(auxiliar);
                            short disponPri = s.getDisponibilidad_general();
                            short dispoPCla = s.getDisponibilidad_a_primera_c();
                            short dispoEspe = s.getDisponibilidad_espera();
                            Integer salida_id = s.getSalida_id();
                            Integer idOrigen = s.getOrigen_id(),
                                    idTren = s.getTren_id(),
                                    idDestino = s.getDestino_id();
                            String fecha = s.getFecha(),
                                    hora = s.getHora(), precioBG = s.getPrecio_boleto_general(),
                                    precioBPC = s.getPrecio_boleto_p_clase();
                            Integer auxiliarF = null;
                            ArrayList<Tren> listaTren = trenPersistencia.consultarTodos();
                            ArrayList<Lugar> listaLugar = lugarPersistencia.consultarTodos();
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
                            do {
                                try {
                                    System.out.println("Ingrese el id del tren,si no lo desea actualizar ingrese -2");
                                    int n = teclado.nextInt();
                                    if (n == -2) {
                                        break;
                                    } else {
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
                                    }
                                } catch (Exception e) {
                                    teclado.nextLine();
                                    System.out.println("Porfavor ingrese digitos");
                                }
                            } while (true);
                            teclado.nextLine();
                            do {
                                System.out.println("");
                                System.out.println("Ingrese la fecha de la salida DD/MM/AAAA (10 caracteres),Si no lo desea ingresar coloque un (*)");
                                String cadenaD = teclado.nextLine();
                                if (cadenaD.length() != 0 && cadenaD.length() <= 10) {
                                    if (cadenaD.length() == 1) {
                                        if (cadenaD.equalsIgnoreCase("*")) {
                                            //Verificar tambien que la fecha coincida con los dias que el tren trabaja
                                            break;
                                        } else {
                                            System.out.println("Caractere invalido");
                                        }
                                    } else {
                                        if (a.validaFecha(cadenaD)) {
                                            String fechad = a.validarDias(cadenaD);
                                            if (fechad == null) {
                                                System.out.println("La fecha contiene dias invalidos,verifique el mes o dia");
                                            } else {
                                                Fecha se = new Fecha(cadenaD);
                                                LocalDate localDate = LocalDate.of(se.getYear(), se.getMonth(), se.getDay());
                                                DayOfWeek dayOfWeek = DayOfWeek.from(localDate);
                                                int val = dayOfWeek.getValue();
                                                String ayudaDia = arregloDias[val - 1];
                                                int auxiliarIndice = listaTren.indexOf(new Tren(idTren));
                                                String diasOperacion = listaTren.get(auxiliarIndice).getDias_operacion();
                                                if (diasOperacion.contains(ayudaDia)) {
                                                    fecha = fechad;
                                                    break;
                                                } else {
                                                    System.out.println("El tren no trabaja esos dias, vuelva a ingresar la fecha.");
                                                }
                                            }
                                        } else {
                                            System.out.println("La fecha no concuerda con el formato");
                                        }
                                    }
                                } else {
                                    if (cadenaD.length() == 0) {
                                        System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                    } else {
                                        System.out.println("El maximo de caracteres permitos es de 10");
                                    }
                                }
                            } while (true);
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
                            do {
                                do {
                                    try {
                                        System.out.println("Ingrese el id de la salida para el origen,si no lo desea actualizar ingrese -2");
                                        int n = teclado.nextInt();
                                        if (n == -2) {
                                            break;
                                        } else {
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
                                        }
                                    } catch (Exception e) {
                                        teclado.nextLine();
                                        System.out.println("Porfavor ingrese digitos");
                                    }
                                } while (true);
                                do {
                                    try {
                                        System.out.println("Ingrese el id de la salida para el destino, si no lo desea actualizar ingrese -2.");
                                        int n = teclado.nextInt();
                                        if (n == -2) {
                                            break;
                                        } else {
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
                            teclado.nextLine();
                            do {
                                System.out.println("Porfavor ingrese la hora de la salida HH:MM Formato 24(5 caracteres), si no la desea actualizar ingrese *");
                                String cadenaD = teclado.nextLine();
                                if (cadenaD.length() != 0 && cadenaD.length() <= 5) {
                                    if (cadenaD.length() == 1) {
                                        if (cadenaD.equalsIgnoreCase("*")) {
                                            break;
                                        } else {
                                            System.out.println("Caractere invalido");
                                        }
                                    } else {
                                        if (a.validarHora(cadenaD)) {
                                            hora = cadenaD;
                                            break;
                                        } else {
                                            System.out.println("Porfavor digite solo numeros, no letras");
                                        }
                                    }
                                } else {
                                    if (cadenaD.length() == 0) {
                                        System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                    } else {
                                        System.out.println("El maximo de caracteres permitos es de 5");
                                    }
                                }
                            } while (true);
                            do {
                                System.out.println("Prfavor ingrese el precio del boleto general (10 caracteres), si no lo desea actualizar ingrese * ");
                                String cadenaD = teclado.nextLine();
                                if (cadenaD.length() != 0 && cadenaD.length() <= 10) {
                                    if (cadenaD.length() == 1) {
                                        if (cadenaD.equalsIgnoreCase("*")) {
                                            break;
                                        } else {
                                            System.out.println("Caractere invalido");
                                        }
                                    } else {
                                        if (a.validarDobles(cadenaD)) {
                                            precioBG = cadenaD;
                                            break;
                                        } else {
                                            System.out.println("Porfavor digite solo numeros, no letras");
                                        }
                                    }
                                } else {
                                    if (cadenaD.length() == 0) {
                                        System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                    } else {
                                        System.out.println("El maximo de caracteres permitos es de 10");
                                    }
                                }
                            } while (true);
                            do {
                                System.out.println("Prfavor ingrese el precio del boleto primera clase (10 caracteres), si no lo desea actualizar ingrese *");
                                String cadenaD = teclado.nextLine();
                                if (cadenaD.length() != 0 && cadenaD.length() <= 10) {
                                    if (cadenaD.length() == 1) {
                                        if (cadenaD.equalsIgnoreCase("*")) {
                                            break;
                                        } else {
                                            System.out.println("Caractere invalido");
                                        }
                                    } else {
                                        if (a.validarDobles(cadenaD)) {
                                            precioBPC = cadenaD;
                                            break;
                                        } else {
                                            System.out.println("Porfavor digite solo numeros, no letras");
                                        }
                                    }
                                } else {
                                    if (cadenaD.length() == 0) {
                                        System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                    } else {
                                        System.out.println("El maximo de caracteres permitos es de 10");
                                    }
                                }
                            } while (true);
                            teclado.nextLine();
                            System.out.println("");
                            while (true) {
                                try {
                                    System.out.println("Ingrese la disponibilidad para primera clase de la salida, 5 o mas. Si no lo desea actualizar ingrese -2");
                                    short n = teclado.nextShort();
                                    if (n == -2) {
                                        break;
                                    } else {
                                        if (n < 0) {
                                            System.out.println("La disponibilidad debe de ser positivo");
                                        } else if (n == 0) {
                                            System.out.println("La disponibilidad debe de ser mayor a cero");
                                        } else if (n > 0) {
                                            if (n < 5) {
                                                System.out.println("La disponibilida debe de ser mayor a 5");
                                            } else {
                                                dispoPCla = n;
                                                break;
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    teclado.nextLine();
                                    System.out.println("Porfavor ingrese digitos");
                                }

                            }
                             System.out.println("");
                            while (true) {
                                try {
                                    System.out.println("Ingrese la disponibilidad para boletos generales de la salida, 5 o mas. Si no lo desea actualizar ingrese -2");
                                    short n = teclado.nextShort();
                                    if (n == -2) {
                                        break;
                                    } else {
                                        if (n < 0) {
                                            System.out.println("La disponibilidad debe de ser positivo");
                                        } else if (n == 0) {
                                            System.out.println("La disponibilidad debe de ser mayor a cero");
                                        } else if (n > 0) {
                                            if (n < 5) {
                                                System.out.println("La disponibilida debe de ser mayor a 5");
                                            } else {
                                                disponPri = n;
                                                break;
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    teclado.nextLine();
                                    System.out.println("Porfavor ingrese digitos");
                                }

                            }
                             System.out.println("");
                            while (true) {
                                try {
                                    System.out.println("Ingrese la disponibilidad para boletos en espera de la salida, 5 o mas. Si no lo desea actualizar ingrese -2");
                                    short n = teclado.nextShort();
                                    if (n == -2) {
                                        break;
                                    } else {
                                        if (n < 0) {
                                            System.out.println("La disponibilidad debe de ser positivo");
                                        } else if (n == 0) {
                                            System.out.println("La disponibilidad debe de ser mayor a cero");
                                        } else if (n > 0) {
                                            if (n < 5) {
                                                System.out.println("La disponibilida debe de ser mayor a 6");
                                            } else {
                                                dispoEspe = n;
                                                break;
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    teclado.nextLine();
                                    System.out.println("Porfavor ingrese digitos");
                                }

                            }
                            teclado.nextLine();
                            System.out.println("--------------------------------------------------");
                            System.out.println("|                Confirmacion                    |");
                            System.out.println("--------------------------------------------------");
                            System.out.printf("|%s %-37s |", "Tren id (" + s.getTren_id() + ");", idTren);
                            System.out.println("");
                            System.out.printf("|%s %-35s |", "Origen id (" + s.getOrigen_id() + ");", idOrigen);
                            System.out.println("");
                            System.out.printf("|%s %-34s |", "Destino id (" + s.getDestino_id() + ");", idDestino);
                            System.out.println("");
                            System.out.printf("|%s %-38s |", "Fecha (" + s.getFecha() + ");", fecha);
                            System.out.println("");
                            System.out.printf("|%s %-40s |", "Hora (" + s.getHora() + ");", hora);
                            System.out.println("");
                            System.out.printf("|%s %-24s |", "Precio boleto General (" + s.getPrecio_boleto_general() + ");", precioBG);
                            System.out.println("");
                            System.out.printf("|%s %-18s |", "Precio boleto Primera Clase (" + s.getPrecio_boleto_p_clase() + ");", precioBPC);
                            System.out.println("");
                            System.out.printf("|%s %-23s |", "Disponibilidad General (" + s.getDisponibilidad_general() + ");", disponPri);
                            System.out.println("");
                            System.out.printf("|%s %-23s |", "Disponibilidad Primera (" + s.getDisponibilidad_a_primera_c() + ");", dispoPCla);
                            System.out.println("");
                            System.out.printf("|%s %-24s |", "Disponibilidad Espera (" + s.getDisponibilidad_espera() + ");", dispoEspe);
                            System.out.println("");
                            System.out.println("--------------------------------------------------");
                            System.out.println("");
                            do {
                                System.out.println("¿Desea actualizar la salida(y/n)?");
                                String cadenaD = teclado.nextLine();
                                if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                    if (cadenaD.equalsIgnoreCase("Y")) {
                                        s.setSalida_id(salida_id);
                                        s.setTren_id(idTren);
                                        s.setOrigen_id(idOrigen);
                                        s.setDestino_id(idDestino);
                                        s.setFecha(fecha);
                                        s.setHora(hora);
                                        s.setPrecio_boleto_general(precioBG);
                                        s.setPrecio_boleto_p_clase(precioBPC);
                                        s.setDisponibilidad_general(disponPri);
                                        s.setDisponibilidad_a_primera_c(dispoPCla);
                                        s.setDisponibilidad_espera(dispoEspe);
                                        salidaPersistencia.actualizarSalida(s);
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
                        }
                    }
                } while (true);
                teclado.nextLine();
                System.out.println("--------------------------------------------------");
                do {
                    System.out.println("¿Desea actualizar otro salida(y/n)?");
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
            } else {
                opcion2 = false;
                System.out.println("No existen salidas para actualizar");
            }
        }
        System.out.println("");
    }

    public void opcion3() {
        boolean opcion3 = true;
        Scanner teclado = new Scanner(System.in);
        while (opcion3) {
            ArrayList<Salida> listaActual = salidaPersistencia.consultarTodos();
            if (listaActual.size() > 0) {
                System.out.println("");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("|                                                      Tabla de Salidas                                                              |");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("|ID   |Tren |Origen |Destino |Fecha       |Hora    |Precio B.General |Precio BPrimClas |Dispo. General|Dospo. Primera |Dispo. Espera |");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                for (int i = 0; i < listaActual.size(); i++) {
                    System.out.printf("|%-4s |%-4s |%-6s |%-7s |%-11s |%-4s |%-16s |%-16s |%-13s |%-13s |%-13s |", listaActual.get(i).getSalida_id(), listaActual.get(i).getTren_id(),
                            listaActual.get(i).getOrigen_id(), listaActual.get(i).getDestino_id(), listaActual.get(i).getFecha(),
                            (listaActual.get(i).getHora().length() == 6 ? listaActual.get(i).getHora() + "0" : listaActual.get(i).getHora()), listaActual.get(i).getPrecio_boleto_general(), listaActual.get(i).getPrecio_boleto_p_clase(),
                            listaActual.get(i).getDisponibilidad_general(), listaActual.get(i).getDisponibilidad_a_primera_c(), listaActual.get(i).getDisponibilidad_espera());
                    System.out.println("");
                }
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("");
                Integer auxiliar = -1;
                teclado.nextLine();
                do {
                    try {
                        System.out.println("Ingrese el id del la salida a eliminar");
                        int n = teclado.nextInt();
                        auxiliar = n;
                    } catch (Exception e) {
                        teclado.nextLine();
                        System.out.println("Porfavor solo ingrese digitos");
                    }
                    if (auxiliar != -1) {
                        if (listaActual.indexOf(new Salida(auxiliar)) < 0) {
                            System.out.println("El id ingresado no corresponde a una de las salidas actuales.");
                            break;
                        } else {
                            Salida sa = salidaPersistencia.obtenerSalida(auxiliar);
                            System.out.println("");
                            System.out.println("--------------------------------------------------");
                            System.out.println("|                Confirmacion                    |");
                            System.out.println("--------------------------------------------------");
                            System.out.printf("|%s %-37s |", "Tren id; ", sa.getTren_id());
                            System.out.println("");
                            System.out.printf("|%s %-35s |", "Origen id; ", sa.getOrigen_id());
                            System.out.println("");
                            System.out.printf("|%s %-34s |", "Destino id; ", sa.getDestino_id());
                            System.out.println("");
                            System.out.printf("|%s %-38s |", "Fecha ; ", sa.getFecha());
                            System.out.println("");
                            System.out.printf("|%s %-40s |", "Hora; ", sa.getHora());
                            System.out.println("");
                            System.out.printf("|%s %-24s |", "Precio boleto General ", sa.getPrecio_boleto_general());
                            System.out.println("");
                            System.out.printf("|%s %-18s |", "Precio boleto Primera Clase ", sa.getPrecio_boleto_p_clase());
                            System.out.println("");
                            System.out.printf("|%s %-23s |", "Disponibilidad General ", sa.getDisponibilidad_general());
                            System.out.println("");
                            System.out.printf("|%s %-23s |", "Disponibilidad Primera ", sa.getDisponibilidad_a_primera_c());
                            System.out.println("");
                            System.out.printf("|%s %-24s |", "Disponibilidad Espera ", sa.getDisponibilidad_espera());
                            System.out.println("");
                            System.out.println("--------------------------------------------------");
                            System.out.println("");
                            teclado.nextLine();
                            do {
                                System.out.println("¿Desea eliminar el tren (y/n)?");
                                String cadenaD = teclado.nextLine();
                                if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                    if (cadenaD.equalsIgnoreCase("Y")) {
                                        salidaPersistencia.eliminarSalida(auxiliar);
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

                            break;
                        }
                    }
                } while (true);
                teclado.nextLine();
                System.out.println("");
                do {
                    System.out.println("¿Desea eliminar otra salida (y/n)?");
                    String cadenaD = teclado.nextLine();
                    if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                        if (cadenaD.equalsIgnoreCase("Y")) {
                            break;
                        } else if (cadenaD.equalsIgnoreCase("N")) {
                            opcion3 = false;
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
            } else {
                opcion3 = false;
                System.out.println("No existen salidas para eliminar");
            }
        }
        System.out.println("");
    }

    public void opcion4() {
        boolean opcion4 = true;
        Scanner teclado = new Scanner(System.in);
        System.out.println("");
        ArrayList<Salida> listaActual = salidaPersistencia.consultarTodos();
        if (listaActual.size() > 0) {
            while (opcion4) {
                System.out.println("--------------------------------------------------");
                System.out.println("|♥♦♣♠           Menu Consultas           ♥♦♣♠|");
                System.out.println("--------------------------------------------------");
                System.out.println("|          Opciones          |       Tecla       |");
                System.out.println("--------------------------------------------------");
                System.out.println("| 1. Todas                   |          1        | ");
                System.out.println("| 2. Precio Boleto General   |          2        | ");
                System.out.println("| 3. Precio Boleto PrimeraC  |          3        | ");
                System.out.println("| 4. Salir                   |          0        | ");
                System.out.println("--------------------------------------------------");
                System.out.println("");
                Integer auxiliar = a.opcionesMenuRangos(3);
                switch (auxiliar) {
                    case 1:
                        System.out.println("");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|                                                      Tabla de Salidas                                                              |");
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |Tren |Origen |Destino |Fecha       |Hora    |Precio B.General |Precio BPrimClas |Dispo. General|Dospo. Primera |Dispo. Espera |");
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        for (int i = 0; i < listaActual.size(); i++) {
                            System.out.printf("|%-4s |%-4s |%-6s |%-7s |%-11s |%-4s |%-16s |%-16s |%-13s |%-13s |%-13s |", listaActual.get(i).getSalida_id(), listaActual.get(i).getTren_id(),
                                    listaActual.get(i).getOrigen_id(), listaActual.get(i).getDestino_id(), listaActual.get(i).getFecha(),
                                    (listaActual.get(i).getHora().length() == 6 ? listaActual.get(i).getHora() + "0" : listaActual.get(i).getHora()), listaActual.get(i).getPrecio_boleto_general(), listaActual.get(i).getPrecio_boleto_p_clase(),
                                    listaActual.get(i).getDisponibilidad_general(), listaActual.get(i).getDisponibilidad_a_primera_c(), listaActual.get(i).getDisponibilidad_espera());
                            System.out.println("");
                        }
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        break;
                    case 2:
                        String general = "";
                        do {
                            System.out.println("Prfavor ingrese el precio del boleto general para filtrar las salidas.");
                            String cadenaD = teclado.nextLine();
                            if (cadenaD.length() != 0 && cadenaD.length() <= 10) {
                                if (a.validarDobles(cadenaD)) {
                                    general = cadenaD;
                                    break;
                                } else {
                                    System.out.println("Porfavor digite solo numeros, no letras");
                                }
                            } else {
                                if (cadenaD.length() == 0) {
                                    System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                } else {
                                    System.out.println("El maximo de caracteres permitos es de 10");
                                }
                            }
                        } while (true);
                        String consulta = "SELECT salida_id,tren_id,origen_id,destino_id,(CONVERT(date,fecha_y_hora)) AS Fecha,CONCAT(CONCAT(DATEPART(HOUR,fecha_y_hora),' ',':'),' ',DATEPART(MINUTE,fecha_y_hora)) AS Hora,precio_boleto_general,precio_boleto_p_clase,disponibilidad_general,disponibilidad_a_primera_c,disponibilidad_espera FROM Salidas where precio_boleto_general=" + general;
                        ArrayList<Salida> listaActualA = salidaPersistencia.consultarTodosFiltro(consulta);
                        if (listaActualA != null) {
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
                        } else {
                            System.out.println("No existen salidas con lo anteriormente ingresado");
                        }
                        break;
                    case 3:
                        String primera = "";
                        do {
                            System.out.println("Prfavor ingrese el precio del boleto primera clase para filtrar las salidas.");
                            String cadenaD = teclado.nextLine();
                            if (cadenaD.length() != 0 && cadenaD.length() <= 10) {
                                if (a.validarDobles(cadenaD)) {
                                    primera = cadenaD;
                                    break;
                                } else {
                                    System.out.println("Porfavor digite solo numeros, no letras");
                                }
                            } else {
                                if (cadenaD.length() == 0) {
                                    System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                } else {
                                    System.out.println("El maximo de caracteres permitos es de 10");
                                }
                            }
                        } while (true);
                        String consultaD = "SELECT salida_id,tren_id,origen_id,destino_id,(CONVERT(date,fecha_y_hora)) AS Fecha,CONCAT(CONCAT(DATEPART(HOUR,fecha_y_hora),' ',':'),' ',DATEPART(MINUTE,fecha_y_hora)) AS Hora,precio_boleto_general,precio_boleto_p_clase,disponibilidad_general,disponibilidad_a_primera_c,disponibilidad_espera FROM Salidas where precio_boleto_p_clase=" + primera;
                        ArrayList<Salida> listaActualD = salidaPersistencia.consultarTodosFiltro(consultaD);
                        if (listaActualD != null) {
                            System.out.println("");
                            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|                                                      Tabla de Salidas                                                              |");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("|ID   |Tren |Origen |Destino |Fecha       |Hora    |Precio B.General |Precio BPrimClas |Dispo. General|Dospo. Primera |Dispo. Espera |");
                            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                            for (int i = 0; i < listaActualD.size(); i++) {
                                System.out.printf("|%-4s |%-4s |%-6s |%-7s |%-11s |%-4s |%-16s |%-16s |%-13s |%-13s |%-13s |", listaActualD.get(i).getSalida_id(), listaActualD.get(i).getTren_id(),
                                        listaActualD.get(i).getOrigen_id(), listaActualD.get(i).getDestino_id(), listaActualD.get(i).getFecha(),
                                        (listaActualD.get(i).getHora().length() == 6 ? listaActualD.get(i).getHora() + "0" : listaActualD.get(i).getHora()), listaActualD.get(i).getPrecio_boleto_general(), listaActualD.get(i).getPrecio_boleto_p_clase(),
                                        listaActualD.get(i).getDisponibilidad_general(), listaActualD.get(i).getDisponibilidad_a_primera_c(), listaActualD.get(i).getDisponibilidad_espera());
                                System.out.println("");
                            }
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("");
                        } else {
                            System.out.println("No existen salidas con lo anteriormente ingresado");
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
            System.out.println("No existen salidas para mostrar");
        }
        System.out.println("");
    }

  
}
