/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author josej
 */
public class Conexion {

    private final String cadenaConexion = "jdbc:sqlserver://LAPTOP-NGCTMFGS:1433;databaseName=Trenes";
    private final String usuario = "sa";
    private final String contrasena = "123456";

    public Connection crearConexion() throws SQLException {
        Connection conexion = DriverManager.getConnection(cadenaConexion, usuario, contrasena);
        return conexion;
    }
}
