package persistencia;

import club_nautico.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD implements IConexionBD {

    final String CADENA_CONEXION = "jdbc:mysql://localhost/club_nautico_15";
    final String USUARIO = "root";
    final String CONTRASEÑA = "123456";

    @Override
    public Connection crearConexion() throws SQLException{
        //Permite la conexion entre una base de datos
        Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASEÑA);
        return conexion;
    }

}
