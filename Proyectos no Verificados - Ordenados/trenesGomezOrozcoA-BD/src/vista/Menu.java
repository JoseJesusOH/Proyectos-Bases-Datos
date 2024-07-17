package vista;

import java.util.Scanner;

public class Menu {

    private boolean salir = true;
   private Auxiliares a=new Auxiliares();
    public Menu() {
    }

    public void menuPrincipal() {
        while (salir) {
            Scanner teclado = new Scanner(System.in);
            System.out.println("\033[0;32m--------------------------------------------------");
            System.out.println("\033[0;32m|♥♦♣♠           Menu Principal            ♥♦♣♠|");
            System.out.println("\033[0;32m--------------------------------------------------\033[0m");
            System.out.println("\033[0;32m|\033[0m          \033[0;35mOpciones\033[0m          \033[0;32m|\033[0m       \033[0;35mTecla\033[0m       \033[0;32m|");
            System.out.println("\033[0;32m--------------------------------------------------\033[0m");
            System.out.println("\033[0;32m| \033[0;35m1. Lugares\033[0m                 \033[0;32m|          \033[0;35m1\033[0m        \033[0;32m| ");
            System.out.println("\033[0;32m| \033[0;35m2. Tren\033[0m                    \033[0;32m|          \033[0;35m2\033[0m        \033[0;32m| ");
            System.out.println("\033[0;32m| \033[0;35m3. Pasajero\033[0m                \033[0;32m|          \033[0;35m3\033[0m        \033[0;32m| ");
            System.out.println("\033[0;32m| \033[0;35m4. Salidas\033[0m                 \033[0;32m|          \033[0;35m4\033[0m        \033[0;32m| ");
            System.out.println("\033[0;32m| \033[0;35m5. Boletos\033[0m                 \033[0;32m|          \033[0;35m5\033[0m        \033[0;32m| ");
            System.out.println("\033[0;32m| \033[0;35m6. Salir\033[0m                   \033[0;32m|          \033[0;35m0\033[0m        \033[0;32m| ");
            System.out.println("\033[0;32m--------------------------------------------------");
            System.out.println("");
            Integer auxiliar = a.opcionesMenuRangos(5);
            switch (auxiliar) {
                case 1:
                    LugarVista lugarObjeto = new LugarVista();
                    lugarObjeto.menuLugar();
                    break;
                case 2:
                    TrenVista trenObjeto = new TrenVista();
                    trenObjeto.menuTren();
                    break;
                case 3:
                    PasajeroVista p = new PasajeroVista();
                    p.menuPasajero();
                    break;
                case 4:
                    SalidaVista s = new SalidaVista();
                    s.menuSalida();
                    break;
                case 5:
                    BoletoVista b = new BoletoVista();
                    b.menuBoleto();
                    break;
                case 0:
                    salir = false;
                    break;
                default:
                    break;
            }
        }
    }

}
