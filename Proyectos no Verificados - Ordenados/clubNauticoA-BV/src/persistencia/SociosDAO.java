package persistencia;

import entidades.Socio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SociosDAO implements ISociosDAO {

    private IConexionBD conexion;

    public SociosDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean agregar(Socio socio) {
        try {
            //Permite la conexion entre una base de datos
            Connection conexion = this.conexion.crearConexion();

            // Permite enviar codigo SQL
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = String.format("INSERT INTO socios(nombre,curp) VALUES('%s','%s');", socio.getNombre(), socio.getCurp());

            //Sirve para realizar las modicaciones de datos (insert,delete,update)
            int numeroRegistrosModificados = comandoSQL.executeUpdate(codigoSQL);

            //Cerramos la conexion para liberar recursos del servidor BD
            conexion.close();

            return numeroRegistrosModificados == 1;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Socio socio) {
        try {
            //Permite la conexion entre una base de datos
            Connection conexion = this.conexion.crearConexion();

            // Permite enviar codigo SQL
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = String.format("UPDATE socios SET nombre='%s', curp='%s' WHERE id_socio=%d;", 
                    socio.getNombre(), socio.getCurp(), socio.getIdSocio());

            //Sirve para realizar las modicaciones de datos (insert,delete,update)
            int numeroRegistrosActualizados = comandoSQL.executeUpdate(codigoSQL);

            //Cerramos la conexion para liberar recursos del servidor BD
            conexion.close();

            return numeroRegistrosActualizados == 1;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean eliminar(Long idSocio) {
        try {
            //Permite la conexion entre una base de datos
            Connection conexion = this.conexion.crearConexion();

            // Permite enviar codigo SQL
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = "DELETE FROM socios WHERE id_socio=" + idSocio;

            //Sirve para realizar las modicaciones de datos (insert,delete,update)
            int numeroRegistrosEliminados = comandoSQL.executeUpdate(codigoSQL);

            //Cerramos la conexion para liberar recursos del servidor BD
            conexion.close();

            return numeroRegistrosEliminados == 1;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    @Override
    public Socio consultar(Long idSocio) {
        Socio socio = null;
        try {
            //Permite la conexion entre una base de datos
            Connection conexion = this.conexion.crearConexion();

            // Permite enviar codigo SQL
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = String.format("SELECT id_socio,nombre,curp from socios where id_socio=%d;", idSocio);

            ResultSet result = comandoSQL.executeQuery(codigoSQL);
            if (result.next()) {
                Long id = result.getLong("id_socio");
                String nombre = result.getString("nombre");
                String curp = result.getString("curp");
                socio = new Socio(idSocio, nombre, curp);
            }
            conexion.close();
            return socio;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return socio;
        }
    }

    @Override
    public List<Socio> consultarTodos() {
        List<Socio> lista = new ArrayList<>();
        try {
            //Permite la conexion entre una base de datos
            Connection conexion = this.conexion.crearConexion();

            // Permite enviar codigo SQL
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = String.format("SELECT id_socio,nombre,curp from socios");

            ResultSet result = comandoSQL.executeQuery(codigoSQL);
            while (result.next()) {
                Long idSocio = result.getLong("id_socio");
                String nombre = result.getString("nombre");
                String curp = result.getString("curp");
                Socio socio = new Socio(idSocio, nombre, curp);
                lista.add(socio);
            }
            conexion.close();
            return lista;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return lista;
        }
    }
}
