
package club_nautico;

import entidades.Socio;
import guis.SociosForm;
import guis.SociosFormSin;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import persistencia.ConexionBD;
import persistencia.IConexionBD;
import persistencia.ISociosDAO;

public class Principal {

    public static void main(String[] args) {
//        // TODO code application logic here
//        String CADENA_CONEXION = "jdbc:mysql://localhost/club_nautico_15";
//        String USUARIO = "root";
//        String CONTRASEÑA = "123456";
//        try {
//            //Permite la conexion entre una base de datos
//            Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASEÑA);
//            
//            // Permite enviar codigo SQL
//            Statement comandoSQL = conexion.createStatement();
//            String codigoSQL = String.format("INSERT INTO socios(nombre,curp) VALUES('%s','%s');","Steve Jobs","STMA040110HEMSLWHF");
//            
//            //Sirve para realizar las modicaciones de datos (insert,delete,update)
//            int numeroRegistrosModificados=comandoSQL.executeUpdate(codigoSQL);
//            
//            //Cerramos la conexion para liberar recursos del servidor BD
//            conexion.close();
//            
//            if(numeroRegistrosModificados==1){
//                System.out.println("El socio se registro correctamente");    
//            }else{
//                System.out.println("No se pudo registrar al socio");
//            }
//            
//            
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
          IConexionBD conexion = new ConexionBD();
          ISociosDAO sociosDAO = new persistencia.SociosDAO(conexion);
//          sociosDAO.agregar(new Socio("Jimena","OMHJ040196MSRMHSR7"));
          
             /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SociosForm(sociosDAO).setVisible(true);
            }
        });
          

    }

}
