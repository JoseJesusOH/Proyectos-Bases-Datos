package principal;

import formularios.*;

public class Principal {

    public static void main(String[] args) {
 java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuForm().setVisible(true);
            }
        });
    }
    
}
