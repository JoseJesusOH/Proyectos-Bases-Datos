package persistencia;

import entidades.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * ClienteDAO la cual contiene todas las operaciones basicas para un cliente.
 *
 * @author José Jesús Orozco Hernández ID; 00000229141
 */
public class ClienteDAO implements IClienteDAO {

    /**
     * Conexion
     */
    private IConexionBD conexion;

    /**
     * Constructor que inicializa la conexion .
     *
     * @param conexion Coneion
     */
    public ClienteDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    /**
     * Metodo para agregar un cliente a la base de datos.
     *
     * @param cliente Cliente a agregar.
     * @return true si este fue agregado,false en caso contrario.
     */
    @Override
    public boolean agregar(Cliente cliente) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = String.format("INSERT INTO clientes(nombre,direccion,telefono) VALUES('%s','%s','%s');",
                    cliente.getNombre(), cliente.getDireccion(), cliente.getTelefono());
            int numeroRegistrosModificados = comandoSQL.executeUpdate(codigoSQL);
            conexion.close();
            return numeroRegistrosModificados == 1;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Metodo para actualizar un cliente a la base de datos.
     *
     * @param cliente Cliente a actualizar.
     * @return true si este fue actualizado,false en caso contrario.
     */
    @Override
    public boolean actualizar(Cliente cliente) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = String.format("UPDATE clientes SET nombre='%s', direccion='%s', telefono='%s' WHERE id_cliente=%d;",
                    cliente.getNombre(), cliente.getDireccion(), cliente.getTelefono(), cliente.getIdCliente());
            int numeroRegistrosActualizados = comandoSQL.executeUpdate(codigoSQL);
            conexion.close();
            return numeroRegistrosActualizados == 1;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Metodo para eliminar un cliente a la base de datos.
     *
     * @param idCliente Id del Cliente a eliminar.
     * @return true si este fue eliminado,false en caso contrario.
     */
    @Override
    public boolean eliminar(Integer idCliente) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = "DELETE FROM clientes WHERE id_cliente=" + idCliente;
            int numeroRegistrosEliminados = comandoSQL.executeUpdate(codigoSQL);
            conexion.close();
            return numeroRegistrosEliminados == 1;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    /**
     * Metodo que obtiene un cliente de la base de datos.
     *
     * @param idCliente ID del cliente a obtener
     * @return El cliente que se obtuvo
     */
    @Override
    public Cliente consultar(Integer idCliente) {
        Cliente cliente = null;
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = String.format("SELECT id_cliente,nombre,direccion,telefono from clientes where id_cliente=%d;",
                    idCliente);
            ResultSet result = comandoSQL.executeQuery(codigoSQL);
            if (result.next()) {
                Integer id = result.getInt("id_cliente");
                String nombre = result.getString("nombre");
                String direccion = result.getString("direccion");
                String telefono = result.getString("telefono");
                cliente = new Cliente(id, nombre, direccion, telefono);
            }
            conexion.close();
            return cliente;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return cliente;
        }
    }

    /**
     * Metodo que obtiene todos los clientes de la base de datos.
     *
     * @return La lista con todos los clientes.
     */
    @Override
    public List<Cliente> consultarTodos() {
        List<Cliente> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = String.format("SELECT id_cliente,nombre,direccion,telefono from clientes");
            ResultSet result = comandoSQL.executeQuery(codigoSQL);
            while (result.next()) {
                Integer id = result.getInt("id_cliente");
                String nombre = result.getString("nombre");
                String direccion = result.getString("direccion");
                String telefono = result.getString("telefono");
                Cliente cliente = new Cliente(id, nombre, direccion, telefono);
                lista.add(cliente);
            }
            conexion.close();
            return lista;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return lista;
        }
    }

}
