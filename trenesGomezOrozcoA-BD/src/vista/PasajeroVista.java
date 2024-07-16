package vista;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import objetosNegocio.Pasajero;
import persistencia.PasajeroPersistencia;

public class PasajeroVista {

    private String[] arregloDias = {"LU", "MA", "MI", "JU", "VI", "SA", "DO"};
    private boolean pasajero = true;
    private PasajeroPersistencia pasajeroPersistencia;
    private Auxiliares a = new Auxiliares();

    public PasajeroVista() {
        pasajeroPersistencia = new PasajeroPersistencia();
    }

    public void menuPasajero() {
        System.out.println("");
        Scanner teclado = new Scanner(System.in);
        while (pasajero) {

            System.out.println("\033[0;34m--------------------------------------------------");
            System.out.println("\033[0;34m|♥♦♣♠              Menu Pasajero          ♥♦♣♠|");
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
                    pasajero = false;
                    break;
                default:
                    break;
            }
        }
        teclado.nextLine();
        System.out.println("");
    }

    /**
     * Inserta un componente
     */
    public void opcion1() {
        Scanner teclado = new Scanner(System.in);
//        teclado.nextLine();
        boolean opcion1 = true;
        System.out.println("");
        while (opcion1) {
            String nombrePasajero = "", apellidoPaterno = "", aoellidoMaterno = "", fechaNacimiento = "", telefono = "", direccion = "";
            char genero;

            nombrePasajero = a.regreso("Ingrese el nombre del pasajero(15 caracteres)", 15);

            apellidoPaterno = a.regreso("Ingrese el apellido paterno del pasajero(15 caracteres)", 15);

            aoellidoMaterno = a.regreso("Ingrese el apellido materno del pasajero(15 caracteres)", 15);
            do {
                System.out.println("");
                System.out.println("Ingrese la fecha de nacimiento DD/MM/AAAA (10 caracteres),Si no lo desea ingresar coloque un (*)");
                String cadenaD = teclado.nextLine();
                if (cadenaD.length() != 0 && cadenaD.length() <= 10) {
                    if (cadenaD.length() == 1) {
                        if (cadenaD.equalsIgnoreCase("*")) {
                            break;
                        } else {
                            System.out.println("Caractere invalido");
                        }
                    } else {
                        if (a.validaFecha(cadenaD)) {
                            String fecha = a.validarDias(cadenaD);
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
                    if (cadenaD.length() == 0) {
                        System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                    } else {
                        System.out.println("El maximo de caracteres permitos es de 10");
                    }
                }
            } while (true);
            do {
                System.out.println("");
                System.out.println("Ingrese el genero del pasajero del pasajero M/H (1 caracteres)");
                String cadenaD = teclado.nextLine().toUpperCase();
                if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                    if (cadenaD.equalsIgnoreCase("M") || cadenaD.equalsIgnoreCase("H")) {
                        genero = cadenaD.charAt(0);
                        break;
                    } else {
                        System.out.println("No ha ingresado una opcion valida verifique porfavor.");
                    }

                } else {
                    if (cadenaD.length() == 0) {
                        System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                    } else {
                        System.out.println("El maximo de caracteres permitos es de 1");
                    }
                }
            } while (true);
            do {
                System.out.println("");
                System.out.println("Ingrese el telefono del pasajero(12 caracteres),Si no lo desea ingresar coloque un (*)");
                String cadenaD = teclado.nextLine();
                if (cadenaD.length() != 0 && cadenaD.length() <= 12) {
                    if (cadenaD.length() == 1) {
                        if (cadenaD.equalsIgnoreCase("*")) {
                            break;
                        } else {
                            System.out.println("No ha ingresado una opcion valida");
                        }
                    } else {
                        if (!a.validaEntero(cadenaD)) {
                            System.out.println("Lo ingresado no corresponde a un numero de telefono");
                        } else {
                            telefono = cadenaD;
                            break;
                        }
                    }
                } else {
                    if (cadenaD.length() == 0) {
                        System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                    } else {
                        System.out.println("El maximo de caracteres permitos es de 12");
                    }
                }
            } while (true);
            do {
                System.out.println("");
                System.out.println("Ingrese la direccion del pasajero(50 caracteres),Si no lo desea ingresar coloque un (*)");
                String cadenaD = teclado.nextLine();
                if (cadenaD.length() != 0 && cadenaD.length() <= 50) {
                    if (cadenaD.length() == 1) {
                        if (cadenaD.equalsIgnoreCase("*")) {
                            break;
                        } else {
                            System.out.println("No ha ingresado una opcion valida");
                        }
                    } else {
                        direccion = cadenaD;
                        break;
                    }
                } else {
                    if (cadenaD.length() == 0) {
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
            do {
                System.out.println("¿Desea guardar el pasajero (y/n)?");
                String cadenaD = teclado.nextLine();
                if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                    if (cadenaD.equalsIgnoreCase("Y")) {
                        Pasajero p = new Pasajero(nombrePasajero, apellidoPaterno, aoellidoMaterno, fechaNacimiento, genero, telefono, direccion);
                        pasajeroPersistencia.insertarPasajero(p);
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
            System.out.println("--------------------------------------------------");
            do {
                System.out.println("¿Desea guardar otra pasajero(y/n)?");
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
        System.out.println("");
    }

    public void opcion2() {
        boolean opcion2 = true;
        Scanner teclado = new Scanner(System.in);
        while (opcion2) {
            System.out.println("");
            ArrayList<Pasajero> listaActual = pasajeroPersistencia.consultarTodos();
            if (listaActual.size() > 0) {
                System.out.println("");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("|                                                           Tabla de Pasajeros                                                                 |");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("|ID   |Nombre          |Apellido Paterno|Apellido Materno|Fecha Nacimi.|Gen. |Telefono     |Direccion                                          |");
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
                for (int i = 0; i < listaActual.size(); i++) {
                    System.out.printf("|%-4s |%-15s |%-15s |%-15s |%-12s |%-4s |%-12s |%-50s |", listaActual.get(i).getPasajero_id(), listaActual.get(i).getNombre(), listaActual.get(i).getApellido_paterno(), listaActual.get(i).getApellido_materno(), listaActual.get(i).getFecha_nacimiento(), listaActual.get(i).getGenero(), listaActual.get(i).getTelefono(), listaActual.get(i).getDireccion());
                    System.out.println("");
                }
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("");
                Integer auxiliar = -1;
                do {
                    try {
                        System.out.println("Ingrese el id del pasajero a actualizar");
                        int n = teclado.nextInt();
                        auxiliar = n;
                    } catch (Exception e) {
                        teclado.nextLine();
                        System.out.println("Porfavor solo ingrese digitos");
                    }
                    if (auxiliar != -1) {
                        if (listaActual.indexOf(new Pasajero(auxiliar)) < 0) {
                            System.out.println("El id ingresado no corresponde a un pasajero de los actuales.");
                            break;
                        } else {
                            teclado.nextLine();
                            //Pedir y Actualizar
                            Pasajero p = pasajeroPersistencia.obtenerPasajero(auxiliar);
                            char genero = p.getGenero();
                            String nombrePasajero = "",
                                    apellidoPaterno = "",
                                    aoellidoMaterno = "",
                                    fechaNacimiento = p.getFecha_nacimiento(),
                                    telefono = p.getTelefono(),
                                    direccion = p.getDireccion();

                            nombrePasajero = a.regresoActualizar("Ingrese el nombre del pasajero(15 caracteres), si no lo desea actualizar ingrese (*)", 15, p.getNombre());
                            apellidoPaterno = a.regresoActualizar("Ingrese el apellido paterno del pasajero(15 caracteres), si no lo desea actualizar ingrese (*)", 15, p.getApellido_paterno());
                            aoellidoMaterno = a.regresoActualizar("Ingrese el apellido materno del pasajero(15 caracteres), si no lo desea actualizar ingrese (*)", 15, p.getApellido_materno());
                            do {
                                System.out.println("");
                                System.out.println("Ingrese la fecha de nacimiento DD/MM/AAAA (10 caracteres),, si no lo desea actualizar ingrese (*)");
                                String cadenaD = teclado.nextLine();
                                if (cadenaD.length() != 0 && cadenaD.length() <= 10) {
                                    if (cadenaD.length() == 1) {
                                        if (cadenaD.equalsIgnoreCase("*")) {
                                            break;
                                        } else {
                                            System.out.println("Caractere invalido");
                                        }
                                    } else {
                                        if (a.validaFecha(cadenaD)) {
                                            String fecha = a.validarDias(cadenaD);
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
                                    if (cadenaD.length() == 0) {
                                        System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                    } else {
                                        System.out.println("El maximo de caracteres permitos es de 10");
                                    }
                                }
                            } while (true);
                            do {
                                System.out.println("");
                                System.out.println("Ingrese el genero del pasajero del pasajero M/H (1 caracteres), si no lo desea actualizar ingrese (*)");
                                String cadenaD = teclado.nextLine().toUpperCase();
                                if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                    if (cadenaD.equalsIgnoreCase("*")) {
                                        break;
                                    } else {
                                        if (cadenaD.equalsIgnoreCase("M") || cadenaD.equalsIgnoreCase("H")) {
                                            genero = cadenaD.charAt(0);
                                            break;
                                        } else {
                                            System.out.println("No ha ingresado una opcion valida verifique porfavor.");
                                        }
                                    }
                                } else {
                                    if (cadenaD.length() == 0) {
                                        System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                    } else {
                                        System.out.println("El maximo de caracteres permitos es de 1");
                                    }
                                }
                            } while (true);
                            do {
                                System.out.println("");
                                System.out.println("Ingrese el telefono del pasajero(12 caracteres), si no lo desea actualizar ingrese (*)");
                                String cadenaD = teclado.nextLine();
                                if (cadenaD.length() != 0 && cadenaD.length() <= 12) {
                                    if (cadenaD.length() == 1) {
                                        if (cadenaD.equalsIgnoreCase("*")) {
                                            break;
                                        } else {
                                            System.out.println("No ha ingresado una opcion valida");
                                        }
                                    } else {
                                        if (!a.validaEntero(cadenaD)) {
                                            System.out.println("Lo ingresado no corresponde a un numero de telefono");
                                        } else {
                                            telefono = cadenaD;
                                            break;
                                        }
                                    }
                                } else {
                                    if (cadenaD.length() == 0) {
                                        System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                                    } else {
                                        System.out.println("El maximo de caracteres permitos es de 12");
                                    }
                                }
                            } while (true);
                            do {
                                System.out.println("");
                                System.out.println("Ingrese la direccion del pasajero(50 caracteres), si no lo desea actualizar ingrese (*)");
                                String cadenaD = teclado.nextLine();
                                if (cadenaD.length() != 0 && cadenaD.length() <= 50) {
                                    if (cadenaD.length() == 1) {
                                        if (cadenaD.equalsIgnoreCase("*")) {
                                            break;
                                        } else {
                                            System.out.println("No ha ingresado una opcion valida");
                                        }
                                    } else {
                                        direccion = cadenaD;
                                        break;
                                    }
                                } else {
                                    if (cadenaD.length() == 0) {
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
                            System.out.printf("|%s %-53s |", "Nombre (" + p.getNombre() + ");", nombrePasajero);
                            System.out.println("");
                            System.out.printf("|%s %-43s |", "Apellido Paterno (" + p.getApellido_paterno() + ");", apellidoPaterno);
                            System.out.println("");
                            System.out.printf("|%s %-45s |", "Apellido Materno (" + p.getApellido_materno() + ");", aoellidoMaterno);
                            System.out.println("");
                            System.out.printf("|%s %-43s |", "Fecha Nacimiento (" + p.getFecha_nacimiento() + ");", fechaNacimiento);
                            System.out.println("");
                            System.out.printf("|%s %-53s |", "Genero (" + p.getGenero() + ");", genero);
                            System.out.println("");
                            System.out.printf("|%s %-51s |", "Telefono (" + p.getTelefono() + ");", telefono);
                            System.out.println("");
                            System.out.printf("|%s %-50s |", "Direccion (" + p.getDireccion() + ");", direccion);
                            System.out.println("");
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("");
                            teclado.nextLine();
                            do {
                                System.out.println("¿Desea actualizar a el pasajero (y/n)?");
                                String cadenaD = teclado.nextLine();
                                if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                    if (cadenaD.equalsIgnoreCase("Y")) {
                                        p.setNombre(nombrePasajero);
                                        p.setApellido_paterno(apellidoPaterno);
                                        p.setApellido_materno(aoellidoMaterno);
                                        p.setFecha_nacimiento(fechaNacimiento);
                                        p.setGenero(genero);
                                        p.setTelefono(telefono);
                                        p.setDireccion(direccion);
                                        pasajeroPersistencia.actualizarPasajero(p);
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
                System.out.println("--------------------------------------------------");
                do {
                    System.out.println("¿Desea actualizar otro pasajero (y/n)?");
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
                System.out.println("No existen pasajeros para actualizar");
            }

        }
        System.out.println("");
    }

    public void opcion3() {
        boolean opcion3 = true;
        Scanner teclado = new Scanner(System.in);
        while (opcion3) {
            ArrayList<Pasajero> listaActual = pasajeroPersistencia.consultarTodos();
            if (listaActual != null) {
                System.out.println("");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("|                                                           Tabla de Pasajeros                                                                 |");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("|ID   |Nombre          |Apellido Paterno|Apellido Materno|Fecha Nacimi.|Gen. |Telefono     |Direccion                                          |");
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
                for (int i = 0; i < listaActual.size(); i++) {
                    System.out.printf("|%-4s |%-15s |%-15s |%-15s |%-12s |%-4s |%-12s |%-50s |", listaActual.get(i).getPasajero_id(), listaActual.get(i).getNombre(), listaActual.get(i).getApellido_paterno(), listaActual.get(i).getApellido_materno(), listaActual.get(i).getFecha_nacimiento(), listaActual.get(i).getGenero(), listaActual.get(i).getTelefono(), listaActual.get(i).getDireccion());
                    System.out.println("");
                }
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("");
                Integer auxiliar = -1;
                do {
                    try {
                        System.out.println("Ingrese el id del pasajero a eliminar");
                        int n = teclado.nextInt();
                        auxiliar = n;
                    } catch (Exception e) {
                        teclado.nextLine();
                        System.out.println("Porfavor solo ingrese digitos");
                    }
                    if (auxiliar != -1) {
                        if (listaActual.indexOf(new Pasajero(auxiliar)) < 0) {
                            System.out.println("El id ingresado no corresponde a un pasajero de los actuales.");
                            break;
                        } else {
                            Pasajero p = pasajeroPersistencia.obtenerPasajero(auxiliar);
                            System.out.println("");
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("|                     Confirmacion                              |");
                            System.out.println("-----------------------------------------------------------------");
                            System.out.printf("|%s %-53s |", "Nombre; ", p.getNombre());
                            System.out.println("");
                            System.out.printf("|%s %-43s |", "Apellido Paterno; ", p.getApellido_paterno());
                            System.out.println("");
                            System.out.printf("|%s %-45s |", "Apellido Materno", p.getApellido_materno());
                            System.out.println("");
                            System.out.printf("|%s %-43s |", "Fecha Nacimiento; ", p.getFecha_nacimiento());
                            System.out.println("");
                            System.out.printf("|%s %-53s |", "Genero; ", p.getGenero());
                            System.out.println("");
                            System.out.printf("|%s %-51s |", "Telefono; ", p.getTelefono());
                            System.out.println("");
                            System.out.printf("|%s %-50s |", "Direccion; ", p.getDireccion());
                            System.out.println("");
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("");
                            //Ademas restar del principal este id sacar del array
                            teclado.nextLine();
                            do {
                                System.out.println("¿Desea eliminar el pasajero (y/n)?");
                                String cadenaD = teclado.nextLine();
                                if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                    if (cadenaD.equalsIgnoreCase("Y")) {
                                        pasajeroPersistencia.eliminarPasajero(auxiliar);
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
                System.out.println("--------------------------------------------------");
                do {
                    System.out.println("¿Desea eliminar otro pasajero (y/n)?");
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
                System.out.println("No existen pasajeros para eliminar");
            }
        }

        System.out.println("");
    }

    public void opcion4() {
        boolean opcion4 = true;
        Scanner teclado = new Scanner(System.in);
        System.out.println("");
        ArrayList<Pasajero> listaActual = pasajeroPersistencia.consultarTodos();
        if (listaActual.size() > 0) {
            while (opcion4) {
                System.out.println("--------------------------------------------------");
                System.out.println("|♥♦♣♠           Menu Consultas           ♥♦♣♠|");
                System.out.println("--------------------------------------------------");
                System.out.println("|          Opciones          |       Tecla       |");
                System.out.println("--------------------------------------------------");
                System.out.println("| 1. Todas                   |          1        | ");
                System.out.println("| 2. Nombre                  |          2        | ");
                System.out.println("| 3. Salir                   |          0        | ");
                System.out.println("--------------------------------------------------");
                System.out.println("");
                Integer auxiliar = a.opcionesMenuRangos(2);
                switch (auxiliar) {
                    case 1:
                        System.out.println("");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|                                                           Tabla de Pasajeros                                                                 |");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |Nombre          |Apellido Paterno|Apellido Materno|Fecha Nacimi.|Gen. |Telefono     |Direccion                                          |");
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
                        for (int i = 0; i < listaActual.size(); i++) {
                            System.out.printf("|%-4s |%-15s |%-15s |%-15s |%-12s |%-4s |%-12s |%-50s |", listaActual.get(i).getPasajero_id(), listaActual.get(i).getNombre(), listaActual.get(i).getApellido_paterno(), listaActual.get(i).getApellido_materno(), listaActual.get(i).getFecha_nacimiento(), listaActual.get(i).getGenero(), listaActual.get(i).getTelefono(), listaActual.get(i).getDireccion());
                            System.out.println("");
                        }
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        break;
                    case 2:
                        String nombreBuscar = a.regreso("Ingrese el nombre del pasajero(15 caracteres)", 15);
                        ArrayList<Pasajero> listaActualF = pasajeroPersistencia.consultarTodosFiltro("select * from Pasajero where nombre  like '%" + nombreBuscar + "%'");
                        if (listaActualF != null) {
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
                        } else {
                            System.out.println("No existen pasajeros que coincidan con lo ingresado");
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
            System.out.println("No existen pasajeros para consultar");
        }
        System.out.println("");
    }

    public static boolean validaFecha(String s) {
        CharSequence cadena = s.trim();
        String reCadena = "^((0?[1-9])|([1-2]\\d)|([3][0-1]))\\/((0?[1-9])|(1[0-2]))\\/\\d{4}$";
        Pattern pattern = Pattern.compile(reCadena);
        Matcher matcher = pattern.matcher(cadena);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static String validarDias(String fecha) {
        int a = 0;
        int auxiliar = 1;
        String diaS = "";
        String mesS = "";
        String anhoS = "";
        int dia = 0;
        int mes = 0;
        int anho = 0;
        char array_caracteres[] = fecha.toCharArray();

        while (a < fecha.length()) {
            if (auxiliar == 1) {
                if (Character.isDigit(array_caracteres[a])) {
                    diaS += array_caracteres[a];
                } else {
                    a++;
                    auxiliar = 2;
                }
            }
            if (auxiliar == 2) {
                if (Character.isDigit(array_caracteres[a])) {
                    mesS += array_caracteres[a];
                } else {
                    a++;
                    auxiliar = 3;
                }
            }
            if (auxiliar == 3) {
                if (Character.isDigit(array_caracteres[a])) {
                    anhoS += array_caracteres[a];
                }
            }
            a++;
        }
        dia = Integer.valueOf(diaS);
        mes = Integer.valueOf(mesS);
        anho = Integer.valueOf(anhoS);
        if (anho % 4 == 0) {
            if (mes < 0 || mes > 12) {
                return null;
            } else {
                switch (mes) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        if (dia < 0 || dia > 31) {
                            return null;
                        }
                        break;
                    case 2:
                        if (dia < 0 || dia > 29) {
                            return null;
                        }
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        if (dia < 0 || dia > 30) {
                            return null;
                        }
                        break;
                    default:
                        break;
                }
            }
        } else {
            if (mes < 0 || mes > 12) {
                return null;
            } else {
                switch (mes) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        if (dia < 0 || dia > 31) {
                            return null;
                        }
                        break;
                    case 2:
                        if (dia < 0 || dia > 28) {
                            return null;
                        }
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        if (dia < 0 || dia > 30) {
                            return null;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return anhoS + "-" + mesS + "-" + diaS;
    }

    public static boolean validaEntero(String s) {
        CharSequence cadena = s.trim();
        String reCadena = "^\\d+$";
        Pattern pattern = Pattern.compile(reCadena);
        Matcher matcher = pattern.matcher(cadena);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static void conexionIAE(String sentencia) {
    }
}
