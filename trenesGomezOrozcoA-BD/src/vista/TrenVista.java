package vista;

import java.util.ArrayList;
import java.util.Scanner;
import objetosNegocio.Tren;
import persistencia.TrenPersistencia;

public class TrenVista {

    private String[] arregloDias = {"LU", "MA", "MI", "JU", "VI", "SA", "DO"};
    private boolean tren = true;
    private TrenPersistencia persistenciaTren;
    private Auxiliares a = new Auxiliares();

    public TrenVista() {
        persistenciaTren = new TrenPersistencia();
    }

    public void menuTren() {
        System.out.println("");
        Scanner teclado = new Scanner(System.in);
        while (tren) {
            System.out.println("\033[0;34m--------------------------------------------------");
            System.out.println("\033[0;34m|♥♦♣♠              Menu Tren              ♥♦♣♠|");
            System.out.println("\033[0;34m--------------------------------------------------\033[0m");
            System.out.println("\033[0;34m|\033[0m          Opciones          \033[0;34m|\033[0m       Tecla       \033[0;34m|");
            System.out.println("\033[0;34m--------------------------------------------------");
            System.out.println("\033[0;34m|\033[0m 1. Insertar                \033[0;34m|\033[0m          1        \033[0;34m| ");
            System.out.println("\033[0;34m|\033[0m 2. Actualizar              \033[0;34m|\033[0m          2        \033[0;34m| ");
            System.out.println("\033[0;34m|\033[0m 3. Eliminar                \033[0;34m|\033[0m          3        \033[0;34m| ");
            System.out.println("\033[0;34m|\033[0m 4. Consultar               \033[0;34m|\033[0m          4        \033[0;34m| ");
            System.out.println("\033[0;34m|\033[0m 5. Salir                   \033[0;34m|\033[0m          0        \033[0;34m| ");
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
                    tren = false;
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
            boolean salidaDigitos = true;
            String nombreTren = "", modelo = "", diasOperacion = "";
            nombreTren = a.regreso("Ingrese el nombre del tren(15 caracteres)", 15);
            modelo = a.regreso("Ingrese el modelo del tren(10 caracteres)", 10);
            Short auxiliarAnho = null;
            System.out.println("");
            do {
                try {
                    System.out.println("Ingrese el año del tren (AAAA)");
                    short n = teclado.nextShort();
                    if (n < 0) {
                        System.out.println("El año debe de ser positivo");
                    } else if (n == 0) {
                        System.out.println("El año debe de ser mayor a cero");
                    } else if (n > 0) {
                        String s = String.valueOf(n);
                        if (s.length() < 4) {
                            System.out.println("EL año debe de contener cuatro digitos");
                        } else if (s.length() > 4) {
                            System.out.println("EL año debe de contener cuatro digitos");
                        } else {
                            auxiliarAnho = n;
                            break;
                        }
                    }
                } catch (Exception e) {
                    teclado.nextLine();
                    System.out.println("Porfavor ingrese digitos");
                }

            } while (true);

            System.out.println("");
            Short capacidad = null;
            while (true) {
                try {
                    System.out.println("Ingrese la capacidad del tren(10 o mas)");
                    short n = teclado.nextShort();
                    if (n < 0) {
                        System.out.println("La capacidad debe de ser positivo");
                    } else if (n == 0) {
                        System.out.println("La capacidad debe de ser mayor a cero");
                    } else if (n > 0) {
                        if (n < 10) {
                            System.out.println("La capacidad del tren debe de ser mayor a diez");
                        } else {
                            capacidad = n;
                            break;
                        }
                    }
                } catch (Exception e) {
                    teclado.nextLine();
                    System.out.println("Porfavor ingrese digitos");
                }

            }
            System.out.println("");
            Short asientosPrimeraClase = null;
            while (true) {
                try {
                    System.out.println("Ingrese los asientos de primera clase del tren (5 o mas)");
                    short n = teclado.nextShort();
                    if (n < 0) {
                        System.out.println("La cantidad debe de ser positiva");
                    } else if (n == 0) {
                        System.out.println("La cantidad debe de ser mayor a cero");
                    } else if (n > 0) {
                        if (n < 5) {
                            System.out.println("La cantidad debe de ser mayor a cinco");
                        } else if (n >= 5) {
                            if (n * 2 > capacidad) {
                                System.out.println("El doble de la cantidad ingresa no debe de superar la capacidad total del tren");
                            } else if (n * 2 <= capacidad) {
                                asientosPrimeraClase = n;
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
            System.out.println("Dias de oepracion; Lunes=1 Martes=2 Miercoles=3");
            System.out.println("Jueves=4 Viernes=5 Sabado=6 Domingo=7");
            System.out.println("");
            int apoyoNumerico = -1;
            int n = -1;
            do {
                try {
                    System.out.println("Ingrese los dias que trabajara el tren");
                    n = teclado.nextInt();
                    apoyoNumerico = 0;
                } catch (Exception e) {
                    teclado.nextLine();
                    System.out.println("Porfavor ingrese digitos");
                }
                if (apoyoNumerico == 0) {
                    if (n < 0) {
                        System.out.println("Por favor solo numeros positivos");
                    } else if (n == 0) {
                        System.out.println("El 0 no es valido");
                    } else if (n > 0) {
                        String ayuda = String.valueOf(n);
                        if (ayuda.contains("8") || ayuda.contains("9") || ayuda.contains("0")) {
                            String cero = ayuda.replaceAll("0", "");
                            String ocho = cero.replaceAll("8", "");
                            String nueve = ocho.replaceAll("9", "");
                            ayuda = nueve;
                        }
                        if (ayuda.equalsIgnoreCase("")) {
                            System.out.println("Porfavor ingrese opciones validas");
                        } else {
                            Integer actualIngreso = Integer.valueOf(ayuda);
                            String auxiliarString = String.valueOf(actualIngreso);
                            char auxiliarChar[] = auxiliarString.toCharArray();
                            int auxiliarInt[] = new int[auxiliarChar.length];
                            for (int i = 0; i < auxiliarInt.length; i++) {
                                auxiliarInt[i] = Character.getNumericValue(auxiliarChar[i]);
                            }
                            int auxiliarOrden[] = auxiliarInt;
                            int i, j, aux;
                            for (i = 0; i < auxiliarOrden.length - 1; i++) {
                                for (j = 0; j < auxiliarOrden.length - i - 1; j++) {
                                    if (auxiliarOrden[j + 1] < auxiliarOrden[j]) {
                                        aux = auxiliarOrden[j + 1];
                                        auxiliarOrden[j + 1] = auxiliarOrden[j];
                                        auxiliarOrden[j] = aux;
                                    }
                                }
                            }
                            for (int f = 0; f < auxiliarOrden.length; f++) {
                                for (int m = 0; m < auxiliarOrden.length - 1; m++) {
                                    if (f != m) {
                                        if (auxiliarOrden[f] == auxiliarOrden[m]) {
                                            auxiliarOrden[f] = -1;
                                        }
                                    }
                                }
                            }
                            for (int k = 0; k < auxiliarOrden.length; k++) {
                                int l = auxiliarOrden[k];
                                if (l != -1) {
                                    diasOperacion += arregloDias[l - 1];
                                }
                            }
                            break;
                        }
                    }
                }
            } while (true);
            teclado.nextLine();
            System.out.println("");
            if (salidaDigitos) {
                System.out.println("--------------------------------------------------");
                System.out.println("|                Confirmacion                    |");
                System.out.println("--------------------------------------------------");
                System.out.printf("|%s %-29s |", "Nombre del tren; ", nombreTren);
                System.out.println("");
                System.out.printf("|%s %-38s |", "Modelo; ", modelo);
                System.out.println("");
                System.out.printf("|%s %-41s |", "Año; ", auxiliarAnho);
                System.out.println("");
                System.out.printf("|%s %-35s |", "Capacidad; ", capacidad);
                System.out.println("");
                System.out.printf("|%s %-28s |", "Asientos Primera; ", asientosPrimeraClase);
                System.out.println("");
                System.out.printf("|%s %-30s |", "Dias operacion; ", diasOperacion);
                System.out.println("");
                System.out.println("--------------------------------------------------");
                System.out.println("");
                do {
                    System.out.println("¿Desea guardar el tren(y/n)?");
                    String cadenaD = teclado.nextLine();
                    if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                        if (cadenaD.equalsIgnoreCase("Y")) {
                            Tren t = new Tren(nombreTren, modelo, auxiliarAnho, capacidad, asientosPrimeraClase, diasOperacion);
                            persistenciaTren.insertarTren(t);
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
            System.out.println("");
            do {
                System.out.println("¿Desea guardar otra tren (y/n)?");
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
        teclado.nextLine();
        System.out.println("");
    }

    public void opcion2() {
        boolean opcion2 = true;
        Scanner teclado = new Scanner(System.in);
        while (opcion2) {
            ArrayList<Tren> listaActual = persistenciaTren.consultarTodos();
            if (listaActual.size() > 0) {
                System.out.println("");
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println("|                                     Tabla de Trenes                                            |");
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println("|ID   |Nombre               |Modelo     |Año  |Num. Pasajeros  |Asientos P.C.   |Dias Operación  |");
                System.out.println("--------------------------------------------------------------------------------------------------");
                for (int i = 0; i < listaActual.size(); i++) {
                    System.out.printf("|%-4s |%-20s |%-10s |%-4s |%-15s |%-15s |%-15s |", listaActual.get(i).getTren_id(), listaActual.get(i).getNomdre(), listaActual.get(i).getModelo(), listaActual.get(i).getAño(), listaActual.get(i).getNumero_pasajeros(), listaActual.get(i).getAsientos_primera(), listaActual.get(i).getDias_operacion());
                    System.out.println("");
                }
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println("");
                Integer auxiliar = -1;
                do {
                    try {
                        System.out.println("Ingrese el id del tren");
                        int n = teclado.nextInt();
                        auxiliar = n;
                    } catch (Exception e) {
                        teclado.nextLine();
                        System.out.println("Porfavor solo ingrese digitos");
                    }
                    if (auxiliar != -1) {
                        if (listaActual.indexOf(new Tren(auxiliar)) < 0) {
                            System.out.println("El id ingresado no corresponde a un tren de los actuales.");
                            break;
                        } else {
                            teclado.nextLine();
                            Tren t = persistenciaTren.obtenerTren(auxiliar);
                            String nombreTren = "",
                                    modelo = "",
                                    diasOperacion = t.getDias_operacion();
                            nombreTren = a.regresoActualizar("Ingrese el nombre del tren(15 caracteres), si no desea actualizar ingrese (*)", 15, t.getNomdre());
                            modelo = a.regresoActualizar("Ingrese el modelo del tren(10 caracteres), si no desea actualizar ingrese (*)", 10, t.getModelo());
                            Short auxiliarAnho = t.getAño();
                            System.out.println("");
                            do {
                                try {
                                    System.out.println("Ingrese el año del tren (AAAA), si no desea actualizar ingrese (-2)");
                                    short n = teclado.nextShort();
                                    if (n == -2) {
                                        break;
                                    } else {
                                        if (n < 0) {
                                            System.out.println("El año debe de ser positivo");
                                        } else if (n == 0) {
                                            System.out.println("El año debe de ser mayor a cero");
                                        } else if (n > 0) {
                                            String s = String.valueOf(n);
                                            if (s.length() < 4) {
                                                System.out.println("EL año debe de contener cuatro digitos");
                                            } else if (s.length() > 4) {
                                                System.out.println("EL año debe de contener cuatro digitos");
                                            } else {
                                                auxiliarAnho = n;
                                                break;
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    teclado.nextLine();
                                    System.out.println("Porfavor ingrese digitos");
                                }

                            } while (true);

                            System.out.println("");
                            Short capacidad = t.getNumero_pasajeros();
                            while (true) {
                                try {
                                    System.out.println("Ingrese la capacidad del tren(10 o mas),si no desea actualizar ingrese (-2)");
                                    short n = teclado.nextShort();
                                    if (n == -2) {
                                        break;
                                    } else {
                                        if (n < 0) {
                                            System.out.println("La capacidad debe de ser positivo");
                                        } else if (n == 0) {
                                            System.out.println("La capacidad debe de ser mayor a cero");
                                        } else if (n > 0) {
                                            if (n < 10) {
                                                System.out.println("La capacidad del tren debe de ser mayor a diez");
                                            } else {
                                                capacidad = n;
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
                            Short asientosPrimeraClase = t.getAsientos_primera();
                            while (true) {
                                try {
                                    System.out.println("Ingrese los asientos de primera clase del tren (5 o mas),si no desea actualizar ingrese (-2)");
                                    short n = teclado.nextShort();
                                    if (n == -2) {
                                        break;
                                    } else {
                                        if (n < 0) {
                                            System.out.println("La cantidad debe de ser positiva");
                                        } else if (n == 0) {
                                            System.out.println("La cantidad debe de ser mayor a cero");
                                        } else if (n > 0) {
                                            if (n < 5) {
                                                System.out.println("La cantidad debe de ser mayor a cinco");
                                            } else if (n >= 5) {
                                                if (n * 2 > capacidad) {
                                                    System.out.println("El doble de la cantidad ingresa no debe de superar la capacidad total del tren");
                                                } else if (n * 2 <= capacidad) {
                                                    asientosPrimeraClase = n;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    teclado.nextLine();
                                    System.out.println("Porfavor ingrese digitos");
                                }

                            }
                            System.out.println("");
                            System.out.println("Dias de oepracion; Lunes=1 Martes=2 Miercoles=3");
                            System.out.println("Jueves=4 Viernes=5 Sabado=6 Domingo=7");
                            System.out.println("");
                            int apoyoNumerico = -1;
                            int n = -1;
                            do {
                                try {
                                    System.out.println("Ingrese los dias que trabajara el tren,si no desea actualizar ingrese (-2)");
                                    n = teclado.nextInt();
                                    apoyoNumerico = 0;
                                } catch (Exception e) {
                                    teclado.nextLine();
                                    System.out.println("Porfavor ingrese digitos");
                                }

                                if (apoyoNumerico == 0) {
                                    if (n == -2) {
                                        break;
                                    } else {
                                        if (n < 0) {
                                            System.out.println("Por favor solo numeros positivos");
                                        } else if (n == 0) {
                                            System.out.println("El 0 no es valido");
                                        } else if (n > 0) {
                                            String ayuda = String.valueOf(n);
                                            if (ayuda.contains("8") || ayuda.contains("9") || ayuda.contains("0")) {
                                                String cero = ayuda.replaceAll("0", "");
                                                String ocho = cero.replaceAll("8", "");
                                                String nueve = ocho.replaceAll("9", "");
                                                ayuda = nueve;
                                            }
                                            if (ayuda.equalsIgnoreCase("")) {
                                                System.out.println("Porfavor ingrese opciones validas");
                                            } else {
                                                Integer actualIngreso = Integer.valueOf(ayuda);
                                                String auxiliarString = String.valueOf(actualIngreso);
                                                char auxiliarChar[] = auxiliarString.toCharArray();
                                                int auxiliarInt[] = new int[auxiliarChar.length];
                                                for (int i = 0; i < auxiliarInt.length; i++) {
                                                    auxiliarInt[i] = Character.getNumericValue(auxiliarChar[i]);
                                                }
                                                int auxiliarOrden[] = auxiliarInt;
                                                int i, j, aux;
                                                for (i = 0; i < auxiliarOrden.length - 1; i++) {
                                                    for (j = 0; j < auxiliarOrden.length - i - 1; j++) {
                                                        if (auxiliarOrden[j + 1] < auxiliarOrden[j]) {
                                                            aux = auxiliarOrden[j + 1];
                                                            auxiliarOrden[j + 1] = auxiliarOrden[j];
                                                            auxiliarOrden[j] = aux;
                                                        }
                                                    }
                                                }
                                                for (int f = 0; f < auxiliarOrden.length; f++) {
                                                    for (int m = 0; m < auxiliarOrden.length - 1; m++) {
                                                        if (f != m) {
                                                            if (auxiliarOrden[f] == auxiliarOrden[m]) {
                                                                auxiliarOrden[f] = -1;
                                                            }
                                                        }
                                                    }
                                                }
                                                diasOperacion = "";
                                                for (int k = 0; k < auxiliarOrden.length; k++) {
                                                    int l = auxiliarOrden[k];
                                                    if (l != -1) {
                                                        diasOperacion += arregloDias[l - 1];
                                                    }
                                                }
                                                break;
                                            }
                                        }
                                    }
                                }
                            } while (true);
                            teclado.nextLine();
                            System.out.println("");
                            System.out.println("--------------------------------------------------");
                            System.out.println("|                Confirmacion                    |");
                            System.out.println("--------------------------------------------------");
                            System.out.printf("|%s %-29s |", "Nombre del tren (" + t.getNomdre() + ");", nombreTren);
                            System.out.println("");
                            System.out.printf("|%s %-38s |", "Modelo (" + t.getModelo() + ");", modelo);
                            System.out.println("");
                            System.out.printf("|%s %-41s |", "Año (" + t.getAño() + ");", auxiliarAnho);
                            System.out.println("");
                            System.out.printf("|%s %-35s |", "Capacidad (" + t.getNumero_pasajeros() + ");", capacidad);
                            System.out.println("");
                            System.out.printf("|%s %-28s |", "Asientos Primera (" + t.getAsientos_primera() + ");", asientosPrimeraClase);
                            System.out.println("");
                            System.out.printf("|%s %-30s |", "Dias operacion (" + t.getDias_operacion() + ");", diasOperacion);
                            System.out.println("");
                            System.out.println("--------------------------------------------------");
                            System.out.println("");
                            teclado.nextLine();
                            do {
                                System.out.println("¿Desea actualizar el tren el tren(y/n)?");
                                String cadenaD = teclado.nextLine();
                                if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                    if (cadenaD.equalsIgnoreCase("Y")) {
                                        t.setNomdre(nombreTren);
                                        t.setModelo(modelo);
                                        t.setAño(auxiliarAnho);
                                        t.setNumero_pasajeros(capacidad);
                                        t.setAsientos_primera(asientosPrimeraClase);
                                        t.setDias_operacion(diasOperacion);
                                        persistenciaTren.actualizarTren(t);
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
                    System.out.println("¿Desea actualizar otro tren (y/n)?");
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
                System.out.println("No existen trenes para actualizar");
            }
        }
        System.out.println("");
    }

    public void opcion3() {
        boolean opcion3 = true;
        Scanner teclado = new Scanner(System.in);
        while (opcion3) {
            ArrayList<Tren> listaActual = persistenciaTren.consultarTodos();
            if (listaActual.size() > 0) {
                System.out.println("");
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println("|                                     Tabla de Trenes                                            |");
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println("|ID   |Nombre               |Modelo     |Año  |Num. Pasajeros  |Asientos P.C.   |Dias Operación  |");
                System.out.println("--------------------------------------------------------------------------------------------------");
                for (int i = 0; i < listaActual.size(); i++) {
                    System.out.printf("|%-4s |%-20s |%-10s |%-4s |%-15s |%-15s |%-15s |", listaActual.get(i).getTren_id(), listaActual.get(i).getNomdre(), listaActual.get(i).getModelo(), listaActual.get(i).getAño(), listaActual.get(i).getNumero_pasajeros(), listaActual.get(i).getAsientos_primera(), listaActual.get(i).getDias_operacion());
                    System.out.println("");
                }
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.println("");
                Integer auxiliar = -1;
                do {
                    try {
                        System.out.println("Ingrese el id del tren");
                        int n = teclado.nextInt();
                        auxiliar = n;
                    } catch (Exception e) {
                        System.out.println("Porfavor solo ingrese digitos");
                    }
                    if (auxiliar != -1) {
                        if (listaActual.indexOf(new Tren(auxiliar)) < 0) {
                            System.out.println("El id ingresado no corresponde a un lugar de los actuales.");
                            break;
                        } else {
                            Tren t = persistenciaTren.obtenerTren(auxiliar);
                            System.out.println("");
                            System.out.println("--------------------------------------------------");
                            System.out.println("|                Confirmacion                    |");
                            System.out.println("--------------------------------------------------");
                            System.out.printf("|%s %-29s |", "Nombre del tren; ", t.getNomdre());
                            System.out.println("");
                            System.out.printf("|%s %-38s |", "Modelo; ", t.getModelo());
                            System.out.println("");
                            System.out.printf("|%s %-41s |", "Año; ", t.getAño());
                            System.out.println("");
                            System.out.printf("|%s %-35s |", "Capacidad; ", t.getNumero_pasajeros());
                            System.out.println("");
                            System.out.printf("|%s %-28s |", "Asientos Primera; ", t.getAsientos_primera());
                            System.out.println("");
                            System.out.printf("|%s %-30s |", "Dias operacion; ", t.getDias_operacion());
                            System.out.println("");
                            System.out.println("--------------------------------------------------");
                            System.out.println("");
                            teclado.nextLine();
                            do {
                                System.out.println("¿Desea eliminar el tren (y/n)?");
                                String cadenaD = teclado.nextLine();
                                if (cadenaD.length() != 0 && cadenaD.length() <= 1) {
                                    if (cadenaD.equalsIgnoreCase("Y")) {
                                        persistenciaTren.eliminarTren(auxiliar);
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
                    System.out.println("¿Desea eliminar otro tren (y/n)?");
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
                System.out.println("No existen trenes para eliminar");
            }

        }

        System.out.println("");
    }

    public void opcion4() {
        boolean opcion4 = true;
        Scanner teclado = new Scanner(System.in);
        System.out.println("");
        ArrayList<Tren> listaActual = persistenciaTren.consultarTodos();
        if (listaActual.size() > 0) {
            while (opcion4) {
                System.out.println("--------------------------------------------------");
                System.out.println("|♥♦♣♠           Menu Consultas           ♥♦♣♠|");
                System.out.println("--------------------------------------------------");
                System.out.println("|          Opciones          |       Tecla       |");
                System.out.println("--------------------------------------------------");
                System.out.println("| 1. Todas                   |          1        | ");
                System.out.println("| 2. Dias Operacion          |          2        | ");
                System.out.println("| 3. Salir                   |          0        | ");
                System.out.println("--------------------------------------------------");
                System.out.println("");
                Integer auxiliar = a.opcionesMenuRangos(2);
                switch (auxiliar) {
                    case 1:
                        System.out.println("");
                        System.out.println("--------------------------------------------------------------------------------------------------");
                        System.out.println("|                                     Tabla de Trenes                                            |");
                        System.out.println("--------------------------------------------------------------------------------------------------");
                        System.out.println("|ID   |Nombre               |Modelo     |Año  |Num. Pasajeros  |Asientos P.C.   |Dias Operación  |");
                        System.out.println("--------------------------------------------------------------------------------------------------");
                        for (int i = 0; i < listaActual.size(); i++) {
                            System.out.printf("|%-4s |%-20s |%-10s |%-4s |%-15s |%-15s |%-15s |", listaActual.get(i).getTren_id(), listaActual.get(i).getNomdre(), listaActual.get(i).getModelo(), listaActual.get(i).getAño(), listaActual.get(i).getNumero_pasajeros(), listaActual.get(i).getAsientos_primera(), listaActual.get(i).getDias_operacion());
                            System.out.println("");
                        }
                        System.out.println("--------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        break;
                    case 2:
                        String diasOperacion = "";
                        System.out.println("");
                        System.out.println("Dias de oepracion de los trenes; Lunes=1 Martes=2 Miercoles=3");
                        System.out.println("Jueves=4 Viernes=5 Sabado=6 Domingo=7");
                        System.out.println("");
                        int apoyoNumerico = -1;
                        int n = -1;
                        do {
                            try {
                                System.out.println("Ingrese los dias operaciones, para filtrar el tren por lo seleccionado");
                                n = teclado.nextInt();
                                apoyoNumerico = 0;
                            } catch (Exception e) {
                                teclado.nextLine();
                                System.out.println("Porfavor ingrese digitos");
                            }
                            if (apoyoNumerico == 0) {
                                if (n < 0) {
                                    System.out.println("Por favor solo numeros positivos");
                                } else if (n == 0) {
                                    System.out.println("El 0 no es valido");
                                } else if (n > 0) {
                                    String ayuda = String.valueOf(n);
                                    if (ayuda.contains("8") || ayuda.contains("9") || ayuda.contains("0")) {
                                        String cero = ayuda.replaceAll("0", "");
                                        String ocho = cero.replaceAll("8", "");
                                        String nueve = ocho.replaceAll("9", "");
                                        ayuda = nueve;
                                    }
                                    Integer actualIngreso = Integer.valueOf(ayuda);
                                    String auxiliarString = String.valueOf(actualIngreso);
                                    char auxiliarChar[] = auxiliarString.toCharArray();
                                    int auxiliarInt[] = new int[auxiliarChar.length];
                                    for (int i = 0; i < auxiliarInt.length; i++) {
                                        auxiliarInt[i] = Character.getNumericValue(auxiliarChar[i]);
                                    }
                                    int auxiliarOrden[] = auxiliarInt;
                                    int i, j, aux;
                                    for (i = 0; i < auxiliarOrden.length - 1; i++) {
                                        for (j = 0; j < auxiliarOrden.length - i - 1; j++) {
                                            if (auxiliarOrden[j + 1] < auxiliarOrden[j]) {
                                                aux = auxiliarOrden[j + 1];
                                                auxiliarOrden[j + 1] = auxiliarOrden[j];
                                                auxiliarOrden[j] = aux;
                                            }
                                        }
                                    }
                                    for (int f = 0; f < auxiliarOrden.length; f++) {
                                        for (int m = 0; m < auxiliarOrden.length - 1; m++) {
                                            if (f != m) {
                                                if (auxiliarOrden[f] == auxiliarOrden[m]) {
                                                    auxiliarOrden[f] = -1;
                                                }
                                            }
                                        }
                                    }
                                    for (int k = 0; k < auxiliarOrden.length; k++) {
                                        int l = auxiliarOrden[k];
                                        if (l != -1) {
                                            diasOperacion += arregloDias[l - 1];
                                        }
                                    }
                                    break;
                                }
                            }
                        } while (true);
                        ArrayList<Tren> listaActualF = persistenciaTren.consultarTodosFiltro("select * from Tren where dias_operacion  like '%" + diasOperacion + "%'");
                        if (listaActualF != null) {
                            System.out.println("");
                            System.out.println("--------------------------------------------------------------------------------------------------");
                            System.out.println("|                                     Tabla de Trenes                                            |");
                            System.out.println("--------------------------------------------------------------------------------------------------");
                            System.out.println("|ID   |Nombre               |Modelo     |Año  |Num. Pasajeros  |Asientos P.C.   |Dias Operación  |");
                            System.out.println("--------------------------------------------------------------------------------------------------");
                            for (int i = 0; i < listaActualF.size(); i++) {
                                System.out.printf("|%-4s |%-20s |%-10s |%-4s |%-15s |%-15s |%-15s |", listaActualF.get(i).getTren_id(), listaActualF.get(i).getNomdre(), listaActualF.get(i).getModelo(), listaActualF.get(i).getAño(), listaActualF.get(i).getNumero_pasajeros(), listaActualF.get(i).getAsientos_primera(), listaActualF.get(i).getDias_operacion());
                                System.out.println("");
                            }
                            System.out.println("--------------------------------------------------------------------------------------------------");
                            System.out.println("");
                        } else {
                            System.out.println("No existen trenes que trabajen el dia que ha ingresado");
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
            System.out.println("No existen trenes para mostrar");
        }

        System.out.println("");
    }

    public static void conexionIAE(String sentencia) {
    }
}
