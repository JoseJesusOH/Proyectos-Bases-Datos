package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Clase que realiza la conexion entre la base de datos.
 * @author José Jesús Orozco HErnández ID; 00000229141
 */
public class ConexionBD implements IConexionBD {
/**
 * Cadena de conexion
 */
    final String CADENA_CONEXION = "jdbc:mysql://localhost/evento_sbd";
    /**
     * Usuario de la case de Datos
     */
    final String USUARIO = "root";
    /**
     * Contraseña del usuario de la base de datos
     */
    final String CONTRASEÑA = "123456";
/**
 * Metodo que crea la conexion
 * @return La conexion creada.
 * @throws SQLException Si la creacion tiene algun problema.
 */
    @Override
    public Connection crearConexion() throws SQLException{
        //Permite la conexion entre una base de datos
        Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASEÑA);
        return conexion;
    }

}
