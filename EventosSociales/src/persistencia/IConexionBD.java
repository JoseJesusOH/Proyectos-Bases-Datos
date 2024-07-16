package persistencia;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Conexion Clase.
 *
 * @author José Jesús Orozco Hernández ID; 00000229141
 */
public interface IConexionBD {

    /**
     * Crear conexion a la base de datos.
     *
     * @return La conexion creada
     * @throws SQLException Si esta no se puede conectar.
     */
    public Connection crearConexion() throws SQLException;
}
