/*
 * Manufactura.java
 * Creada el 30 de diciembre del 2021 18:44 PM
 */
package manufactura;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * CLase que permite la conexion entre la base de datos manufactura y la tabla
 * de componentes.{ Alumno; José Jesús Orozco Hernandez ID; 00000229141
 *
 * @author josej
 */
public class Manufactura {

    public static boolean fin = true;

    /**
     * Metodo Main
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        while (fin) {
            menu();
        }
        System.out.println("Gracias por usar el programa, see you later ♦♥♣♠");
    }

    /**
     * Menu principal.
     */
    public static void menu() {
        System.out.println("");
        System.out.println("--------------------------------------------------");
        System.out.println("|♦♣♠♥            Menu Componente          ♦♣♠♥|");
        System.out.println("--------------------------------------------------");
        System.out.println("| Opcion                   |         Tecla       |");
        System.out.println("--------------------------------------------------");
        System.out.println("| ♥. Ver componente        |          1          |");
        System.out.println("| ♦. Actualizar componente |          2          |");
        System.out.println("| ♣. Eliminar componente   |          3          |");
        System.out.println("| ♠. Ver lista             |          4          |");
        System.out.println("| ☺. Insertar componente   |          5          |");
        System.out.println("| ☻. Salir                 |          0          |");
        System.out.println("--------------------------------------------------");
        System.out.println("");
        Integer auxiliar = null;
        while (true) {
            try {
                Scanner teclado = new Scanner(System.in);
                System.out.println("Ingrese la opcion");
                int n = teclado.nextInt();
                if (n > 5 || n < 0) {
                    System.out.println("El digito ingresado no corresponde a una de las "
                            + "opciones del menu, ");
                } else {
                    auxiliar = n;
                    break;
                }
            } catch (Exception e) {
                System.out.println("Porfavor ingrese digitos");
            }

        }
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
            case 0:
                fin = false;
                break;
            default:
                break;
        }

    }

    /**
     * Ver componentes
     */
    public static void opcion1() {
        boolean sF = true;
        while (sF) {
            System.out.println("");
            System.out.println("------------------------------------------------------");
            System.out.println("|♦♣♠♥          Menu ver Componente            ♦♣♠♥|");
            System.out.println("------------------------------------------------------");
            System.out.println("| Opcion                       |         Tecla       |");
            System.out.println("------------------------------------------------------");
            System.out.println("| ♥. Por nombre  (palabra)     |          1          |");
            System.out.println("| ♦. Por descripcion (palabra) |          2          |");
            System.out.println("| ♣. Por id          (numero)  |          3          |");
            System.out.println("| ☻. Salir                     |          0          |");
            System.out.println("--------------------------------------------------");
            System.out.println("");
            Integer auxiliar = null;
            while (true) {
                try {
                    Scanner teclado = new Scanner(System.in);
                    System.out.println("Ingrese la opcion");
                    int n = teclado.nextInt();
                    if (n > 3 || n < 0) {
                        System.out.println("El digito ingresado no corresponde a una de las "
                                + "opciones del menu, ");
                    } else {
                        auxiliar = n;
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Porfavor ingrese digitos");
                }

            }
            switch (auxiliar) {
                case 1:
                    while (true) {
                        System.out.println("");
                        Scanner teclado = new Scanner(System.in);
                        System.out.println("Ingrese el nombre del componente(30 Caracteres)");
                        String cadenaD = teclado.nextLine();
                        if (cadenaD.length() != 0 && cadenaD.length() <= 30) {
                            try {
                                String cadena = "jdbc:sqlserver://LAPTOP-NGCTMFGS:1433;databaseName=Manufactura";
                                Connection conexion = DriverManager.getConnection(cadena, "sa", "123456");
                                Statement s = conexion.createStatement();
                                String consulta = "SELECT id_componente,nombre,descripcion FROM componente WHERE nombre LIKE '%" + cadenaD + "%'";
                                ResultSet registro = s.executeQuery(consulta);
                                System.out.println("");
                                System.out.println("--------------------------------------------------");
                                System.out.println("|        Tabla de componentes  por nombre        |");
                                System.out.println("--------------------------------------------------");
                                System.out.println("| ID  |Nombre                         | Descripcion");
                                System.out.println("------------------------------------------------------");
                                while (registro.next()) {
                                    System.out.printf("|%-4s |%-30s |%-50s", registro.getString("id_componente"), registro.getString("nombre"), registro.getString("descripcion"));
                                    System.out.println("");
                                }
                                System.out.println("------------------------------------------------------");
                                System.out.println("");
                                conexion.close();
                            } catch (SQLException ex) {
                                System.err.println(ex.getMessage());
                            }
                            break;
                        } else {
                            System.out.println("Porfavor escriba algo, o nombre muy largo");
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("");
                        Scanner teclado = new Scanner(System.in);
                        System.out.println("Ingrese la descripcion del componente(50 caracteres)");
                        String cadenaD = teclado.nextLine();
                        if (cadenaD.length() != 0 && cadenaD.length() <= 50) {
                            try {
                                String cadena = "jdbc:sqlserver://LAPTOP-NGCTMFGS:1433;databaseName=Manufactura";
                                Connection conexion = DriverManager.getConnection(cadena, "sa", "123456");
                                Statement s = conexion.createStatement();
                                String consulta = "SELECT id_componente,nombre,descripcion FROM componente WHERE nombre LIKE '%" + cadenaD + "%'";
                                ResultSet registro = s.executeQuery(consulta);
                                System.out.println("");
                                System.out.println("--------------------------------------------------");
                                System.out.println("|        Tabla de componentes  por descripcion        |");
                                System.out.println("--------------------------------------------------");
                                System.out.println("| ID  |Nombre                         | Descripcion");
                                System.out.println("------------------------------------------------------");
                                while (registro.next()) {
                                    System.out.printf("|%-4s |%-30s |%-50s", registro.getString("id_componente"), registro.getString("nombre"), registro.getString("descripcion"));
                                    System.out.println("");
                                }
                                System.out.println("------------------------------------------------------");
                                System.out.println("");
                                conexion.close();
                            } catch (SQLException ex) {
                                System.err.println(ex.getMessage());
                            }
                            break;
                        } else {
                            System.out.println("Porfavor escriba algo, o nombre muy largo");
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        System.out.println("");
                        Integer auxiliard = null;
                        try {
                            Scanner teclado = new Scanner(System.in);
                            System.out.println("Ingrese el id del componente");
                            int n = teclado.nextInt();
                            auxiliard = n;
                        } catch (Exception e) {
                            System.out.println("Porfavor ingrese digitos");
                        }
                        if (auxiliard != null) {
                            try {
                                String cadena = "jdbc:sqlserver://LAPTOP-NGCTMFGS:1433;databaseName=Manufactura";
                                Connection conexion = DriverManager.getConnection(cadena, "sa", "123456");
                                Statement s = conexion.createStatement();
                                String consulta = "select id_componente,nombre,descripcion from componente where id_componente=" + auxiliard;
                                ResultSet registro = s.executeQuery(consulta);
                                System.out.println("");
                                System.out.println("--------------------------------------------------");
                                System.out.println("| ID  |Nombre                         | Descripcion");
                                System.out.println("------------------------------------------------------");
                                if (registro.next()) {
                                    System.out.printf("|%-4s |%-30s |%-50s", registro.getString("id_componente"), registro.getString("nombre"), registro.getString("descripcion"));
                                    System.out.println("");
                                } else {
                                    System.out.println("No existe un componente con dicho id");
                                    System.out.println("");
                                }
                                System.out.println("--------------------------------------------------");
                                System.out.println("");
                                conexion.close();
                            } catch (SQLException ex) {
                                System.err.println(ex.getMessage());
                            }
                            break;
                        }
                    }
                    break;
                case 0:
                    sF = false;
                default:
                    break;
            }
        }
        System.out.println("");
        menu();
    }

    /**
     * Actualizar componente
     */
    public static void opcion2() {
        boolean ats = true;
        while (ats) {
            System.out.println("");
            System.out.println("--------------------------------------------------------");
            System.out.println("|♦♣♠♥          Menu  actualizar Componente      ♦♣♠♥|");
            System.out.println("---------------------------------------------------------");
            System.out.println("| Opcion                          |         Tecla       |");
            System.out.println("---------------------------------------------------------");
            System.out.println("| ♥. Actualizar componente por ID |          1          |");
            System.out.println("| ♦. Ver lista componentes        |          2          |");
            System.out.println("| ☻. Salir                        |          0          |");
            System.out.println("---------------------------------------------------------");
            System.out.println("");
            Integer auxiliar = null;
            while (true) {
                try {
                    Scanner teclado = new Scanner(System.in);
                    System.out.println("Ingrese la opcion");
                    int n = teclado.nextInt();
                    if (n > 3 || n < 0) {
                        System.out.println("El digito ingresado no corresponde a una de las "
                                + "opciones del menu, ");
                    } else {
                        auxiliar = n;
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Porfavor ingrese digitos");
                }

            }
            switch (auxiliar) {
                case 1:
                    String id_componenteC = "";
                    String nombreC = "";
                    String descripcionC = "";
                    boolean atres = true;
                    boolean des = true;
                    Integer auxiliarfos = null;
                    while (des) {
                        try {
                            Scanner teclado = new Scanner(System.in);
                            System.out.println("Ingrese el id del componente");
                            int foxi = teclado.nextInt();
                            auxiliarfos = foxi;
                            try {
                                String cadena = "jdbc:sqlserver://LAPTOP-NGCTMFGS:1433;databaseName=Manufactura";
                                Connection conexion = DriverManager.getConnection(cadena, "sa", "123456");
                                Statement s = conexion.createStatement();
                                String consulta = "select id_componente,nombre,descripcion from componente where id_componente=" + auxiliarfos;
                                ResultSet registro = s.executeQuery(consulta);
                                if (registro.next()) {
                                    id_componenteC = registro.getString("id_componente");
                                    nombreC = registro.getString("nombre");
                                    descripcionC = registro.getString("descripcion");
                                    des = false;
                                } else {
                                    teclado.nextLine();
                                    System.out.println("No existe un componente con dicho id");
                                    System.out.println("");
                                    do {
                                        System.out.println("Desea continuar; Yes = Y No = N");
                                        String finString = teclado.nextLine().toUpperCase();
                                        if (finString.charAt(0) == 'Y' || finString.charAt(0) == 'N') {
                                            if (finString.charAt(0) == 'N') {
                                                atres = false;
                                                des = false;
                                            }
                                            break;
                                        } else {
                                            System.out.println("Ingrese una opcion correcta.");
                                        }
                                    } while (true);

                                }
                                conexion.close();
                            } catch (SQLException ex) {
//                                    System.err.println(ex.getMessage());
                            }
                        } catch (Exception e) {
                            System.out.println("Porfavor ingrese digitos");
                        }

                    }
                    while (atres) {
                        System.out.println("");
                        System.out.println("--------------------------------------------------------");
                        System.out.println("|♦♣♠♥          Menu  actualizar Componente      ♦♣♠♥|");
                        System.out.println("---------------------------------------------------------");
                        System.out.println("| Opcion                          |         Tecla       |");
                        System.out.println("---------------------------------------------------------");
                        System.out.println("| ♥. Nombre y despcripción        |          1          |");
                        System.out.println("| ♦. Nombre                       |          2          |");
                        System.out.println("| ♦. Descripción                  |          3          |");
                        System.out.println("| ☻. Salir                        |          0          |");
                        System.out.println("---------------------------------------------------------");
                        System.out.println("");
                        Integer auxiliarf = null;
                        while (true) {
                            try {
                                Scanner teclado = new Scanner(System.in);
                                System.out.println("Ingrese la opcion");
                                int n = teclado.nextInt();
                                if (n > 3 || n < 0) {
                                    System.out.println("El digito ingresado no corresponde a una de las "
                                            + "opciones del menu, ");
                                } else {
                                    auxiliarf = n;
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("Porfavor ingrese digitos");
                            }

                        }
                        switch (auxiliarf) {
                            case 1:
                                System.out.println("");
                                String nombre = "",
                                 descripcion = "";
                                while (true) {
                                    System.out.println("");
                                    Scanner teclado = new Scanner(System.in);
                                    System.out.println("Ingrese el nombre del componente(30 caracteres)");
                                    String cadenaD = teclado.nextLine();
//                        if (cadenaD.length() != 0 && cadenaD.length()<=30) {
                                    if (cadenaD.length() != 0 && cadenaD.length() <= 30) {
                                        nombre = cadenaD;
                                        break;
                                    } else {
                                        System.out.println("Porfavor escriba algo, o nombre muy largo");
                                    }
                                }
                                while (true) {
                                    System.out.println("");
                                    Scanner teclado = new Scanner(System.in);
                                    System.out.println("Ingrese la descripcion del componente(50 caracteres)");
                                    String cadenaD = teclado.nextLine();
//                        if (cadenaD.length() != 0 && cadenaD.length()<=30) {
                                    if (cadenaD.length() != 0 && cadenaD.length() <= 50) {
                                        descripcion = cadenaD;
                                        break;
                                    } else {
                                        System.out.println("Porfavor escriba algo, o nombre muy largo");
                                    }
                                }
                                try {
                                    String cadena = "jdbc:sqlserver://LAPTOP-NGCTMFGS:1433;databaseName=Manufactura";
                                    Connection conexion = DriverManager.getConnection(cadena, "sa", "123456");
                                    Statement s = conexion.createStatement();
                                    s.executeUpdate("UPDATE componente set nombre='" + nombre + "',descripcion='" + descripcion + "' WHERE id_componente=" + auxiliarfos);
                                    conexion.close();
                                    System.out.println("");
                                    System.out.println("--------------------------");
                                    System.out.println("Se registro el componente");
                                    System.out.println("--------------------------");
                                    System.out.println("");
                                } catch (SQLException e) {
                                    System.out.println("");
                                    System.out.println("--------------------------");
                                    System.out.println("Se registro el componente");
                                    System.out.println("--------------------------");
                                    System.out.println("");
//                                    System.out.println("Error; " + e.getMessage());
                                }
                                atres = false;
                                break;
                            case 2:
                                System.out.println("");
                                String nombreK = "";
                                while (true) {
                                    System.out.println("");
                                    Scanner teclado = new Scanner(System.in);
                                    System.out.println("Ingrese el nombre del componente(30 caracteres)");
                                    String cadenaD = teclado.nextLine();
//                        if (cadenaD.length() != 0 && cadenaD.length()<=30) {
                                    if (cadenaD.length() != 0 && cadenaD.length() <= 30) {
                                        nombreK = cadenaD;
                                        break;
                                    } else {
                                        System.out.println("Porfavor escriba algo, o nombre muy largo");
                                    }
                                }
                                try {
                                    String cadena = "jdbc:sqlserver://LAPTOP-NGCTMFGS:1433;databaseName=Manufactura";
                                    Connection conexion = DriverManager.getConnection(cadena, "sa", "123456");
                                    Statement s = conexion.createStatement();
                                    s.executeUpdate("UPDATE componente set nombre='" + nombreK + " WHERE id_componente=" + auxiliarfos);
                                    conexion.close();
                                    System.out.println("");
                                    System.out.println("--------------------------");
                                    System.out.println("Se actualizo el componente");
                                    System.out.println("--------------------------");
                                    System.out.println("");
                                } catch (SQLException e) {
                                    System.out.println("");
                                    System.out.println("--------------------------");
//                                    System.out.println("Se actualizo el componente");
//                                    System.out.println("--------------------------");
//                                    System.out.println("");
//                                    System.out.println("Error; " + e.getMessage());
                                }
                                atres = false;
                                break;
                            case 3:
                                String descripcionK = "";
                                while (true) {
                                    System.out.println("");
                                    Scanner teclado = new Scanner(System.in);
                                    System.out.println("Ingrese la descripcion del componente(50 caracteres)");
                                    String cadenaD = teclado.nextLine();
//                        if (cadenaD.length() != 0 && cadenaD.length()<=30) {
                                    if (cadenaD.length() != 0 && cadenaD.length() <= 50) {
                                        descripcionK = cadenaD;
                                        break;
                                    } else {
                                        System.out.println("Porfavor escriba algo, o nombre muy largo");
                                    }
                                }
                                try {
                                    String cadena = "jdbc:sqlserver://LAPTOP-NGCTMFGS:1433;databaseName=Manufactura";
                                    Connection conexion = DriverManager.getConnection(cadena, "sa", "123456");
                                    Statement s = conexion.createStatement();
                                    s.executeUpdate("UPDATE componente set descripcion='" + descripcionK + " WHERE id_componente=" + auxiliarfos);
                                    conexion.close();
                                    System.out.println("");
                                    System.out.println("--------------------------");
                                    System.out.println("Se actualizo el componente");
                                    System.out.println("--------------------------");
                                    System.out.println("");
                                } catch (SQLException e) {
                                    System.out.println("");
                                    System.out.println("--------------------------");
//                                    System.out.println("Se actualizo el componente");
//                                    System.out.println("--------------------------");
//                                    System.out.println("");
//                                    System.out.println("Error; " + e.getMessage());
                                }
                                atres = false;
                                break;
                            case 0:
                                atres = false;
                                break;
                        }

                    }
                    try {
                        String cadena = "jdbc:sqlserver://LAPTOP-NGCTMFGS:1433;databaseName=Manufactura";
                        Connection conexion = DriverManager.getConnection(cadena, "sa", "123456");
                        Statement s = conexion.createStatement();
                        String consulta = "select id_componente,nombre,descripcion from componente";
                        ResultSet registro = s.executeQuery(consulta);
                        System.out.println("");
                        System.out.println("-------------------------------------------------------");
                        System.out.println("|                 Tabla de componentes                |");
                        System.out.println("-------------------------------------------------------");
                        System.out.println("| ID  |Nombre                         | Descripcion");
                        System.out.println("------------------------------------------------------");
                        while (registro.next()) {
                            System.out.printf("|%-4s |%-30s |%-50s", registro.getString("id_componente"), registro.getString("nombre"), registro.getString("descripcion"));
                            System.out.println("");
                        }
                        System.out.println("------------------------------------------------------");
                        System.out.println("");
                        conexion.close();
                    } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
                    }
                    break;
                case 2:
                    try {
                        String cadena = "jdbc:sqlserver://LAPTOP-NGCTMFGS:1433;databaseName=Manufactura";
                        Connection conexion = DriverManager.getConnection(cadena, "sa", "123456");
                        Statement s = conexion.createStatement();
                        String consulta = "select id_componente,nombre,descripcion from componente";
                        ResultSet registro = s.executeQuery(consulta);
                        System.out.println("");
                        System.out.println("-------------------------------------------------------");
                        System.out.println("|                 Tabla de componentes                |");
                        System.out.println("-------------------------------------------------------");
                        System.out.println("| ID  |Nombre                         | Descripcion");
                        System.out.println("------------------------------------------------------");
                        while (registro.next()) {
                            System.out.printf("|%-4s |%-30s |%-50s", registro.getString("id_componente"), registro.getString("nombre"), registro.getString("descripcion"));
                            System.out.println("");
                        }
                        System.out.println("------------------------------------------------------");
                        System.out.println("");
                        conexion.close();
                    } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
                    }
                    break;
                case 0:
                    ats = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("");
        menu();
    }

    /**
     * Elimina componente
     */
    public static void opcion3() {
        boolean sF = true;
        while (sF) {
            System.out.println("");
            System.out.println("------------------------------------------------------");
            System.out.println("|♦♣♠♥     Menu eliminar Componente            ♦♣♠♥|");
            System.out.println("------------------------------------------------------");
            System.out.println("| Opcion                       |         Tecla       |");
            System.out.println("------------------------------------------------------");
            System.out.println("| ♥. Ver componentes           |          1          |");
            System.out.println("| ♦. Eliminar por id           |          2          |");
            System.out.println("| ☻. Salir                     |          0          |");
            System.out.println("--------------------------------------------------");
            System.out.println("");
            Integer auxiliar = null;
            while (true) {
                try {
                    Scanner teclado = new Scanner(System.in);
                    System.out.println("Ingrese la opcion");
                    int n = teclado.nextInt();
                    if (n > 2 || n < 0) {
                        System.out.println("El digito ingresado no corresponde a una de las "
                                + "opciones del menu, ");
                    } else {
                        auxiliar = n;
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Porfavor ingrese digitos");
                }

            }
            switch (auxiliar) {
                case 1:
                    try {
                        String cadena = "jdbc:sqlserver://LAPTOP-NGCTMFGS:1433;databaseName=Manufactura";
                        Connection conexion = DriverManager.getConnection(cadena, "sa", "123456");
                        Statement s = conexion.createStatement();
                        String consulta = "select id_componente,nombre,descripcion from componente";
                        ResultSet registro = s.executeQuery(consulta);
                        System.out.println("");
                        System.out.println("-------------------------------------------------------");
                        System.out.println("|                 Tabla de componentes                |");
                        System.out.println("-------------------------------------------------------");
                        System.out.println("| ID  |Nombre                         | Descripcion");
                        System.out.println("------------------------------------------------------");
                        while (registro.next()) {
                            System.out.printf("|%-4s |%-30s |%-50s", registro.getString("id_componente"), registro.getString("nombre"), registro.getString("descripcion"));
                            System.out.println("");
                        }
                        System.out.println("------------------------------------------------------");
                        System.out.println("");
                        conexion.close();
                    } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("");
                        Integer auxiliard = null;
                        try {
                            Scanner teclado = new Scanner(System.in);
                            System.out.println("Ingrese el id del componente");
                            int n = teclado.nextInt();
                            auxiliard = n;
                        } catch (Exception e) {
                            System.out.println("Porfavor ingrese digitos");
                        }
                        if (auxiliard != null) {
                            try {
                                String cadena = "jdbc:sqlserver://LAPTOP-NGCTMFGS:1433;databaseName=Manufactura";
                                Connection conexion = DriverManager.getConnection(cadena, "sa", "123456");
                                Statement s = conexion.createStatement();
                                String consulta = "DELETE FROM componente WHERE id_componente=" + auxiliard;
                                ResultSet registro = s.executeQuery(consulta);
                                System.out.println("");
                                conexion.close();
                            } catch (SQLException ex) {
                                System.err.println("El componente se ha eliminado, correctamente");
                                System.out.println("--------------------------------------------");
                                try {
                                    String cadena = "jdbc:sqlserver://LAPTOP-NGCTMFGS:1433;databaseName=Manufactura";
                                    Connection conexion = DriverManager.getConnection(cadena, "sa", "123456");
                                    Statement s = conexion.createStatement();
                                    String consulta = "select id_componente,nombre,descripcion from componente";
                                    ResultSet registro = s.executeQuery(consulta);
                                    System.out.println("");
                                    System.out.println("-------------------------------------------------------");
                                    System.out.println("|                 Tabla de componentes                |");
                                    System.out.println("-------------------------------------------------------");
                                    System.out.println("| ID  |Nombre                         | Descripcion");
                                    System.out.println("------------------------------------------------------");
                                    while (registro.next()) {
                                        System.out.printf("|%-4s |%-30s |%-50s", registro.getString("id_componente"), registro.getString("nombre"), registro.getString("descripcion"));
                                        System.out.println("");
                                    }
                                    System.out.println("------------------------------------------------------");
                                    System.out.println("");
                                    conexion.close();
                                } catch (SQLException exd) {
//            System.err.println(ex.getMessage());
                                }
                                System.out.println("");
                            }
                            break;
                        }
                    }
                    break;
                case 0:
                    sF = false;
                default:
                    break;
            }
        }
        System.out.println("");
        menu();
    }

    /**
     * Nos muestra la lista de componentes
     */
    public static void opcion4() {
        try {
            String cadena = "jdbc:sqlserver://LAPTOP-NGCTMFGS:1433;databaseName=Manufactura";
            Connection conexion = DriverManager.getConnection(cadena, "sa", "123456");
            Statement s = conexion.createStatement();
            String consulta = "select id_componente,nombre,descripcion from componente";
            ResultSet registro = s.executeQuery(consulta);
            System.out.println("");
            System.out.println("-------------------------------------------------------");
            System.out.println("|                 Tabla de componentes                |");
            System.out.println("-------------------------------------------------------");
            System.out.println("| ID  |Nombre                         | Descripcion");
            System.out.println("------------------------------------------------------");
            while (registro.next()) {
                System.out.printf("|%-4s |%-30s |%-50s", registro.getString("id_componente"), registro.getString("nombre"), registro.getString("descripcion"));
                System.out.println("");
            }
            System.out.println("------------------------------------------------------");
            System.out.println("");
            conexion.close();
        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
        }
        menu();
    }

    /**
     * Inserta un componente
     */
    public static void opcion5() {
        System.out.println("");
        String nombre = "", descripcion = "";
        while (true) {
            System.out.println("");
            Scanner teclado = new Scanner(System.in);
            System.out.println("Ingrese el nombre del componente(30 caracteres)");
            String cadenaD = teclado.nextLine();
//                        if (cadenaD.length() != 0 && cadenaD.length()<=30) {
            if (cadenaD.length() != 0 && cadenaD.length() <= 30) {
                nombre = cadenaD;
                break;
            } else {
                System.out.println("Porfavor escriba algo, o nombre muy largo");
            }
        }
        while (true) {
            System.out.println("");
            Scanner teclado = new Scanner(System.in);
            System.out.println("Ingrese la descripcion del componente(50 caracteres)");
            String cadenaD = teclado.nextLine();
//                        if (cadenaD.length() != 0 && cadenaD.length()<=30) {
            if (cadenaD.length() != 0 && cadenaD.length() <= 50) {
                descripcion = cadenaD;
                break;
            } else {
                System.out.println("Porfavor escriba algo, o nombre muy largo");
            }
        }
        try {
            String cadena = "jdbc:sqlserver://LAPTOP-NGCTMFGS:1433;databaseName=Manufactura";
            Connection conexion = DriverManager.getConnection(cadena, "sa", "123456");
            Statement s = conexion.createStatement();
            s.executeUpdate("INSERT INTO componente(nombre,descripcion) VALUES ('" + nombre + "','" + descripcion + "')");
            conexion.close();
            System.out.println("");
            System.out.println("--------------------------");
            System.out.println("Se registro el componente");
            System.out.println("--------------------------");
            System.out.println("");
        } catch (SQLException e) {
            System.out.println("Error; " + e.getMessage());
        }
        try {
            String cadena = "jdbc:sqlserver://LAPTOP-NGCTMFGS:1433;databaseName=Manufactura";
            Connection conexion = DriverManager.getConnection(cadena, "sa", "123456");
            Statement s = conexion.createStatement();
            String consulta = "select id_componente,nombre,descripcion from componente";
            ResultSet registro = s.executeQuery(consulta);
            System.out.println("");
            System.out.println("-------------------------------------------------------");
            System.out.println("|                 Tabla de componentes                |");
            System.out.println("-------------------------------------------------------");
            System.out.println("| ID  |Nombre                         | Descripcion");
            System.out.println("------------------------------------------------------");
            while (registro.next()) {
                System.out.printf("|%-4s |%-30s |%-50s", registro.getString("id_componente"), registro.getString("nombre"), registro.getString("descripcion"));
                System.out.println("");
            }
            System.out.println("------------------------------------------------------");
            System.out.println("");
            conexion.close();
        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
        }
        menu();
    }

}
