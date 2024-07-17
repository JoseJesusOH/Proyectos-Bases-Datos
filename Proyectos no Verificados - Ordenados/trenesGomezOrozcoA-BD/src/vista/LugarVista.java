package vista;

import java.util.ArrayList;
import java.util.Scanner;
import objetosNegocio.Lugar;
import persistencia.LugarPersistencia;

public class LugarVista {

    private LugarPersistencia persistenciaLugar;
    private Auxiliares a = new Auxiliares();
    private static boolean lugar = true;

    public LugarVista() {
        persistenciaLugar = new LugarPersistencia();
    }

    public void menuLugar() {
        System.out.println("");
        Scanner teclado = new Scanner(System.in);
        while (lugar) {
            System.out.println("\033[0;34m--------------------------------------------------");
            System.out.println("\033[0;34m|♥♦♣♠              Menu Lugar             ♥♦♣♠|");
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
                    lugar = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("");
    }

    public void opcion1() {
        Scanner teclado = new Scanner(System.in);
//        teclado.nextLine();
        boolean opcion1 = true;
        System.out.println("");
        while (opcion1) {
            String nombreEstacion = "", estado = "", ciudad = "";

            nombreEstacion = a.regreso("Ingrese el nombre de la estacion(20 caracteres)", 20);
            ciudad = a.regreso("Ingrese la ciudad(20 caracteres)", 20);
            estado = a.regreso("Ingrese el estado(20 caracteres)", 20);

            System.out.println("--------------------------------------------------");
            System.out.println("|                Confirmacion                    |");
            System.out.println("--------------------------------------------------");
            System.out.printf("|%s %-23s |", "Nombre de la estacion; ", nombreEstacion);
            System.out.println("");
            System.out.printf("|%s %-38s |", "Ciudad; ", ciudad);
            System.out.println("");
            System.out.printf("|%s %-38s |", "Estado; ", estado);
            System.out.println("");
            System.out.println("--------------------------------------------------");
            System.out.println("");
            do {
                System.out.println("¿Desea guardar la estacion (y/n)?");
                String cadenaD = teclado.nextLine();
                if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                    if (cadenaD.equalsIgnoreCase("Y")) {
                        Lugar l = new Lugar(nombreEstacion, estado, ciudad);
                        persistenciaLugar.insertarLugar(l);
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
                System.out.println("¿Desea guardar otra estacion (y/n)?");
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
        Scanner teclado = new Scanner(System.in);

        boolean opcion2 = true;
        while (opcion2) {
            ArrayList<Lugar> listaActuales = persistenciaLugar.consultarTodos();
            if (listaActuales.size() > 0) {
                System.out.println("");
                System.out.println("------------------------------------------------------------------------");
                System.out.println("|                            Tabla de Lugares                           |");
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("|ID   |Nombre Estacion      |Estado               |Ciudad               |");
                System.out.println("-------------------------------------------------------------------------");
                for (int i = 0; i < listaActuales.size(); i++) {
                    System.out.printf("|%-4s |%-20s |%-20s |%-20s |", listaActuales.get(i).getLugar_id(), listaActuales.get(i).getNombre_estacion(), listaActuales.get(i).getEstado(), listaActuales.get(i).getCiudad());
                    System.out.println("");
                }
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("");
                Integer auxiliar = -1;
                boolean salir = true;
                do {
                    try {
                        System.out.println("Ingrese el id del lugar");
                        int n = teclado.nextInt();
                        auxiliar = n;
                    } catch (Exception e) {
                        teclado.nextLine();
                        System.out.println("Porfavor solo ingrese digitos");
                    }
                    teclado.nextLine();
                    if (auxiliar != -1) {
                        int indice = listaActuales.indexOf(new Lugar(auxiliar));
                        if (indice < 0) {
                            System.out.println("El id ingresado no corresponde a un lugar de los actuales.");
                            break;
                        } else {
                            Lugar l = persistenciaLugar.obtenerLugar(auxiliar);
                            String nombreEstacion = "",
                                    estado = "", ciudad = "";
                            nombreEstacion = a.regresoActualizar("Ingrese el nombre de la estacion(20 caracteres), si no lo desa actualizar introduzca(*)", 20, l.getNombre_estacion());

                            estado = a.regresoActualizar("Ingrese el estado(20 caracteres), si no lo desa actualizar introduzca(*)", 20, l.getEstado());

                            ciudad = a.regresoActualizar("Ingrese la ciudad(20 caracteres), si no lo desa actualizar introduzca(*)", 20, l.getCiudad());

                            teclado.nextLine();
                            System.out.println("--------------------------------------------------");
                            System.out.println("|                Confirmacion                    |");
                            System.out.println("--------------------------------------------------");
                            System.out.printf("|%s %-23s |", "Nombre de la estacion (" + l.getNombre_estacion() + ");", nombreEstacion);
                            System.out.println("");
                            System.out.printf("|%s %-38s |", "Ciudad; (" + l.getCiudad() + ");", ciudad);
                            System.out.println("");
                            System.out.printf("|%s %-38s |", "Estado; (" + l.getEstado() + ");", estado);
                            System.out.println("");
                            System.out.println("--------------------------------------------------");
                            System.out.println("");
                            do {
                                System.out.println("¿Desea actualizar el lugar (y/n)?");
                                String cadenaD = teclado.nextLine();
                                if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                    if (cadenaD.equalsIgnoreCase("Y")) {
                                        l.setNombre_estacion(nombreEstacion);
                                        l.setCiudad(ciudad);
                                        l.setEstado(estado);
                                        persistenciaLugar.actualizarLugar(l);
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
                        break;
                    }
                } while (true);
                teclado.nextLine();
                System.out.println("--------------------------------------------------");
                do {
                    System.out.println("¿Desea actualizar otro lugar (y/n)?");
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
                System.out.println("No existen lugares para actualizar");
            }
        }

        System.out.println("");
    }

    public void opcion3() {
        boolean opcion3 = true;
        Scanner teclado = new Scanner(System.in);

        while (opcion3) {
            ArrayList<Lugar> listaActuales = persistenciaLugar.consultarTodos();
            if (listaActuales.size() > 0) {
                System.out.println("");
                System.out.println("------------------------------------------------------------------------");
                System.out.println("|                            Tabla de Lugares                           |");
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("|ID   |Nombre Estacion      |Estado               |Ciudad               |");
                System.out.println("-------------------------------------------------------------------------");
                for (int i = 0; i < listaActuales.size(); i++) {
                    System.out.printf("|%-4s |%-20s |%-20s |%-20s |", listaActuales.get(i).getLugar_id(), listaActuales.get(i).getNombre_estacion(), listaActuales.get(i).getEstado(), listaActuales.get(i).getCiudad());
                    System.out.println("");
                }
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("");
                do {
                    try {
                        System.out.println("Ingrese el id del lugar");
                        int n = teclado.nextInt();
                        if (listaActuales.indexOf(new Lugar(n)) < 0) {
                            System.out.println("El id ingresado no corresponde a un lugar de los actuales.");
                            break;
                        } else {
                            teclado.nextLine();
                            Lugar e = persistenciaLugar.obtenerLugar(n);
                            System.out.println("");
                            System.out.println("--------------------------------------------------");
                            System.out.println("|                Confirmacion                    |");
                            System.out.println("--------------------------------------------------");
                            System.out.printf("|%s %-23s |", "Nombre de la estacion; ", e.getNombre_estacion());
                            System.out.println("");
                            System.out.printf("|%s %-38s |", "Ciudad; ", e.getCiudad());
                            System.out.println("");
                            System.out.printf("|%s %-38s |", "Estado; ", e.getEstado());
                            System.out.println("");
                            System.out.println("--------------------------------------------------");
                            System.out.println("");
                            do {
                                System.out.println("¿Desea eliminar el lugar (y/n)?");
                                String cadenaD = teclado.nextLine();
                                if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                    if (cadenaD.equalsIgnoreCase("Y")) {
                                        persistenciaLugar.eliminarLugar(n);
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
                    } catch (Exception e) {
                        teclado.nextLine();
                        System.out.println("Porfavor solo ingrese digitos");
                    }
                } while (true);
                teclado.nextLine();
                System.out.println("");
                do {
                    System.out.println("¿Desea eliminar otro lugar (y/n)?");
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
                System.out.println("No existen lugares para eliminar");
            }
        }
        System.out.println("");

    }

    public void opcion4() {
        boolean opcion4 = true;
        Scanner teclado = new Scanner(System.in);
        ArrayList<Lugar> listaActuales = persistenciaLugar.consultarTodos();
        if (listaActuales.size() > 0) {
            System.out.println("");
            while (opcion4) {
                System.out.println("--------------------------------------------------");
                System.out.println("|♥♦♣♠           Menu Consultas           ♥♦♣♠|");
                System.out.println("--------------------------------------------------");
                System.out.println("|          Opciones          |       Tecla       |");
                System.out.println("--------------------------------------------------");
                System.out.println("| 1. Todas                   |          1        | ");
                System.out.println("| 2. Estado                  |          2        | ");
                System.out.println("| 3. Ciudad                  |          3        | ");
                System.out.println("| 4. Salir                   |          0        | ");
                System.out.println("--------------------------------------------------");
                System.out.println("");
                Integer auxiliar = a.opcionesMenuRangos(3);
                switch (auxiliar) {
                    case 1:
                        System.out.println("");
                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("|                            Tabla de Lugares                           |");
                        System.out.println("-------------------------------------------------------------------------");
                        System.out.println("|ID   |Nombre Estacion      |Estado               |Ciudad               |");
                        System.out.println("-------------------------------------------------------------------------");
                        for (int i = 0; i < listaActuales.size(); i++) {
                            System.out.printf("|%-4s |%-20s |%-20s |%-20s |", listaActuales.get(i).getLugar_id(), listaActuales.get(i).getNombre_estacion(), listaActuales.get(i).getEstado(), listaActuales.get(i).getCiudad());
                            System.out.println("");
                        }
                        System.out.println("-------------------------------------------------------------------------");
                        System.out.println("");
                        break;
                    case 2:
                        String estado =a.regreso("Ingrese el estado(20 caracteres)", 20);
                        String filtro = "select * from Lugar where estado like '%" + estado + "%'";
                        ArrayList<Lugar> listaActualesF = persistenciaLugar.consultarTodosFiltro(filtro);
                        if (listaActuales.size() > 0) {
                            System.out.println("");
                            System.out.println("------------------------------------------------------------------------");
                            System.out.println("|                            Tabla de Lugares                           |");
                            System.out.println("-------------------------------------------------------------------------");
                            System.out.println("|ID   |Nombre Estacion      |Estado               |Ciudad               |");
                            System.out.println("-------------------------------------------------------------------------");
                            for (int i = 0; i < listaActualesF.size(); i++) {
                                System.out.printf("|%-4s |%-20s |%-20s |%-20s |", listaActualesF.get(i).getLugar_id(), listaActualesF.get(i).getNombre_estacion(), listaActualesF.get(i).getEstado(), listaActualesF.get(i).getCiudad());
                                System.out.println("");
                            }
                            System.out.println("-------------------------------------------------------------------------");
                            System.out.println("");
                        } else {
                            System.out.println("No existen ugares que tengas coincidencias con lo ingresado.");
                        }
                        break;
                    case 3:
                        String ciudad = a.regreso("Ingrese la ciudad(20 caracteres)", 20);
                      
                        String filtroD = "select * from Lugar where ciudad like '%" + ciudad + "%'";
                        ArrayList<Lugar> listaActualesD = persistenciaLugar.consultarTodosFiltro(filtroD);
                        if (listaActualesD.size() > 0) {
                            System.out.println("");
                            System.out.println("------------------------------------------------------------------------");
                            System.out.println("|                            Tabla de Lugares                           |");
                            System.out.println("-------------------------------------------------------------------------");
                            System.out.println("|ID   |Nombre Estacion      |Estado               |Ciudad               |");
                            System.out.println("-------------------------------------------------------------------------");
                            for (int i = 0; i < listaActualesD.size(); i++) {
                                System.out.printf("|%-4s |%-20s |%-20s |%-20s |", listaActualesD.get(i).getLugar_id(), listaActualesD.get(i).getNombre_estacion(), listaActualesD.get(i).getEstado(), listaActualesD.get(i).getCiudad());
                                System.out.println("");
                            }
                            System.out.println("-------------------------------------------------------------------------");
                            System.out.println("");
                        } else {
                            System.out.println("No existen ugares que tengas coincidencias con lo ingresado.");
                        }
                        break;
                    case 0:
                        opcion4 = false;
                        break;
                    default:
                        break;
                }
            }
            teclado.nextLine();
        } else {
            System.out.println("No existen lugares para mostrar");
        }

        System.out.println("");
    }
}
