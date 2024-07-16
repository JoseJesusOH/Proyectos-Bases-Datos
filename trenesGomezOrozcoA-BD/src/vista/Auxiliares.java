/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author josej
 */
public class Auxiliares {

    public Auxiliares() {
    }

    public int opcionesMenuRangos(int tamañoOpciones) {
        Scanner teclado = new Scanner(System.in);
        Integer auxiliar = null;
        while (true) {
            try {
                System.out.println("Ingrese la opcion");
                int n = teclado.nextInt();
                if (n > tamañoOpciones || n < 0) {
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
        return auxiliar;
    }

    public String regreso(String mensaje, int tamaño) {
        Scanner teclado = new Scanner(System.in);
        String cadena = "";
        do {
            System.out.println("");
            System.out.println(mensaje);
            String cadenaD = teclado.nextLine();
            if (cadenaD.length() != 0 && cadenaD.length() <= tamaño) {
                cadena = cadenaD;
                break;
            } else {
                if (cadenaD.length() == 0) {
                    System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                } else {
                    System.out.println("El maximo de caracteres permitos es de " + tamaño);
                }
            }
        } while (true);
        return cadena;
    }

    public String regresoActualizar(String mensaje, int tamaño, String cadenaAuxiliar) {
        Scanner teclado = new Scanner(System.in);
        String cadena = cadenaAuxiliar;
        do {
            System.out.println("");
            System.out.println(mensaje);
            String cadenaD = teclado.nextLine();
            if (cadenaD.length() != 0 && cadenaD.length() <= tamaño) {
                if (cadenaD.length() == 1) {
                    if (cadenaD.equalsIgnoreCase("*")) {
                        break;
                    } else {
                        System.out.println("No ha ingresado una opcion valida");
                    }
                } else {
                    cadena = cadenaD;
                    break;
                }
            } else {
                if (cadenaD.length() == 0) {
                    System.out.println("No ha escrito nada debe ingresar algo si desea continuar.");
                } else {
                    System.out.println("El maximo de caracteres permitos es de " + tamaño);
                }
            }
        } while (true);
        return cadena;
    }

    public boolean validaFecha(String s) {
        CharSequence cadena = s.trim();
        String reCadena = "^((0?[1-9])|([1-2]\\d)|([3][0-1]))\\/((0?[1-9])|(1[0-2]))\\/\\d{4}$";
        Pattern pattern = Pattern.compile(reCadena);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }

    public String validarDias(String fecha) {
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

    public boolean validaEntero(String s) {
        CharSequence cadena = s.trim();
        String reCadena = "^\\d+$";
        Pattern pattern = Pattern.compile(reCadena);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }

    public boolean validarHora(String s) {
        CharSequence cadena = s.trim();
        String reCadena = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$";
        Pattern pattern = Pattern.compile(reCadena);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }

    public boolean validarDobles(String as) {
        CharSequence cadena = as.trim();
        String reCadena = "^[0-9]+([.][0-9]+)?$";
        Pattern pattern = Pattern.compile(reCadena);
        Matcher matcher = pattern.matcher(cadena);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

}
