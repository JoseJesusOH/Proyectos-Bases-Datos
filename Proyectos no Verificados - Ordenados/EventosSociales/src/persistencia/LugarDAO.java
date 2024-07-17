package persistencia;

import entidades.Lugar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * LugarDAO la cual contiene todas las operaciones basicas para un Lugar.
 *
 * @author José Jesús Orozco Hernández ID; 00000229141
 */
public class LugarDAO implements ILugarDAO {

    /**
     * Conexion
     */
    private IConexionBD conexion;

    /**
     * Constructor que inicializa la conexion .
     *
     * @param conexion Coneion
     */
    public LugarDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    /**
     * Metodo para agregar un lugar a la base de datos.
     *
     * @param lugar Lugar a agregar.
     * @return true si este fue agregado,false en caso contrario.
     */
    @Override
    public boolean agregar(Lugar lugar) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL;
            codigoSQL = String.format("INSERT INTO lugares(nombre,direccion,latitud,longitud) VALUES('%s','%s'"
                    + ",'%s','%s');", lugar.getNombre(), lugar.getDireccion(), lugar.getLatitud(), lugar.getLongitd());
            int numeroRegistrosModificados = comandoSQL.executeUpdate(codigoSQL);
            conexion.close();
            return numeroRegistrosModificados == 1;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Metodo para actualizar un lugar de la base de datos.
     *
     * @param lugar Lugar a actualizar.
     * @return true si este fue actualizado,false en caso contrario.
     */
    @Override
    public boolean actualizar(Lugar lugar) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = String.format("UPDATE lugares SET nombre='%s', direccion='%s', latitud='%s'"
                    + ", longitud='%s' WHERE id_lugar=%d;",
                    lugar.getNombre(), lugar.getDireccion(), lugar.getLatitud(), lugar.getLongitd(), lugar.getIdLugar());
            int numeroRegistrosActualizados = comandoSQL.executeUpdate(codigoSQL);
            conexion.close();
            return numeroRegistrosActualizados == 1;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Metodo para eliminar un lugar de la base de datos.
     *
     * @param idLugar ID del lugar a eliminar.
     * @return true si este fue eliminado,false en caso contrario.
     */
    @Override
    public boolean eliminar(Integer idLugar) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = "DELETE FROM lugares WHERE id_lugar=" + idLugar;
            int numeroRegistrosEliminados = comandoSQL.executeUpdate(codigoSQL);
            conexion.close();
            return numeroRegistrosEliminados == 1;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Metodo que obtiene un lugar de la base de datos.
     *
     * @param idLugar ID del lugar a obtener
     * @return El lugar que se obtuvo
     */
    @Override
    public Lugar consultar(Integer idLugar) {
        Lugar lugar = null;
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = String.format("SELECT id_lugar,nombre,direccion,latitud,longitud"
                    + " from lugares where id_lugar=%d;", idLugar);
            ResultSet result = comandoSQL.executeQuery(codigoSQL);
            if (result.next()) {
                Integer id = result.getInt("id_lugar");
                String nombre = result.getString("nombre");
                String direccion = result.getString("direccion");
                Float latitud = result.getFloat("latitud");
                Float longitud = result.getFloat("longitud");
                lugar = new Lugar(id, nombre, direccion, latitud, longitud);
            }
            conexion.close();
            return lugar;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return lugar;
        }
    }

    /**
     * Metodo que obtiene todos los lugares de la base de datos.
     *
     * @return La lista con todos los lugares.
     */
    @Override
    public List<Lugar> consultarTodos() {
        List<Lugar> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = String.format("SELECT id_lugar,nombre,direccion,latitud,longitud from lugares");
            ResultSet result = comandoSQL.executeQuery(codigoSQL);
            while (result.next()) {
                Integer id = result.getInt("id_lugar");
                String nombre = result.getString("nombre");
                String direccion = result.getString("direccion");
                Float latitud = result.getFloat("latitud");
                Float longitud = result.getFloat("longitud");
                Lugar lugar = new Lugar(id, nombre, direccion, latitud, longitud);
                lista.add(lugar);
            }
            conexion.close();
            return lista;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return lista;
        }
    }

}
