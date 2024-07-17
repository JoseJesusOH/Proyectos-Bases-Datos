package persistencia;

import entidades.Evento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * EventoDAO la cual contiene todas las operaciones basicas para un evento.
 *
 * @author José Jesús Orozco Hernández ID; 00000229141
 */
public class EventoDAO implements IEventoDAO {

    /**
     * Conexion
     */
    private IConexionBD conexion;

    /**
     * Constructor que inicializa la conexion .
     *
     * @param conexion Coneion
     */
    public EventoDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }
    /**
     * Metodo para agregar un evento a la base de datos.
     *
     * @param evento Evento a agregar.
     * @return true si este fue agregado,false en caso contrario.
     */
    @Override
    public boolean agregar(Evento evento) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = String.format("INSERT INTO eventos(nombre,fecha_hora,numero_asistentes"
                    + ",id_cliente,id_lugar) VALUES('%s','%s','%s','%s','%s');",
                    evento.getNombre(), evento.getFechaHora().toString() + " " + evento.getHora().toString(), evento.getNumeroAsistentes(), evento.getIdCliente(),
                    evento.getIdLugar());
            int numeroRegistrosModificados = comandoSQL.executeUpdate(codigoSQL);
            conexion.close();
            return numeroRegistrosModificados == 1;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Metodo para actualizar un evento a la base de datos.
     *
     * @param evento Evento a actualizar.
     * @return true si este fue actualizado,false en caso contrario.
     */
    @Override
    public boolean actualizar(Evento evento) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = String.format("UPDATE eventos SET nombre='%s',fecha_hora='%s',"
                    + "numero_asistentes='%s',id_cliente='%d',id_lugar='%d' WHERE id_evento='%d';",
                    evento.getNombre(), evento.getFechaHora().toString() + " " + evento.getHora().toString(), evento.getNumeroAsistentes(), evento.getIdCliente(),
                    evento.getIdLugar(), evento.getIdEvento());
            int numeroRegistrosActualizados = comandoSQL.executeUpdate(codigoSQL);
            conexion.close();
            return numeroRegistrosActualizados == 1;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
    /**
     * Metodo para eliminar un evento a la base de datos.
     *
     * @param idEvento ID del Evento a eliminar.
     * @return true si este fue eliminado,false en caso contrario.
     */
    @Override
    public boolean eliminar(Integer idEvento) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = "DELETE FROM eventos WHERE id_evento=" + idEvento;
            int numeroRegistrosEliminados = comandoSQL.executeUpdate(codigoSQL);
            conexion.close();
            return numeroRegistrosEliminados == 1;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Metodo que obtiene un evento de la base de datos.
     *
     * @param idEvento ID del evento a obtener
     * @return El evento que se obtuvo
     */
    @Override
    public Evento consultar(Integer idEvento) {
        Evento evento = null;
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = "SELECT e.id_evento,e.nombre,date(e.fecha_hora) AS fecha,time(e.fecha_hora) AS hora ,\n"
                    + "e.numero_asistentes,e.id_cliente,c.nombre,e.id_lugar,l.nombre from eventos as e\n"
                    + "inner join lugares as l on l.id_lugar=e.id_lugar inner join clientes as c on\n"
                    + "c.id_cliente=e.id_cliente where e.id_evento=" + idEvento + ";";
//            String codigoSQL = "SELECT id_evento,nombre,date(fecha_hora) AS fecha,time(fecha_hora) AS hora ,numero_asistentes,id_cliente,id_lugar from eventos"
//                    + "where id_evento=" + idEvento;
            ResultSet result = comandoSQL.executeQuery(codigoSQL);
            if (result.next()) {
                Integer id = result.getInt("e.id_evento");
                String nombre = result.getString("e.nombre");
                Date fecha_hora = result.getDate("fecha");
                Time hora = result.getTime("hora");
                Integer numero_asistentes = result.getInt("e.numero_asistentes");
                Integer id_cliente = result.getInt("e.id_cliente");
                String nombreCliente = result.getString("c.nombre");
                Integer id_lugar = result.getInt("id_lugar");
                String nombreLugar = result.getString("l.nombre");
                evento = new Evento(id, nombre, fecha_hora, hora, numero_asistentes, id_cliente, nombreCliente, id_lugar, nombreLugar);
            }
            conexion.close();
            return evento;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return evento;
        }
    }

    /**
     * Metodo que obtiene todos los eventos de la base de datos.
     *
     * @return La lista con todos los eventos.
     */
    @Override
    public List<Evento> consultarTodos() {
        List<Evento> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement comandoSQL = conexion.createStatement();
            String codigoSQL = "SELECT e.id_evento,e.nombre,date(e.fecha_hora) AS fecha,time(e.fecha_hora) AS hora ,\n"
                    + "e.numero_asistentes,e.id_cliente,c.nombre,e.id_lugar,l.nombre from eventos as e\n"
                    + "inner join lugares as l on l.id_lugar=e.id_lugar inner join clientes as c on\n"
                    + "c.id_cliente=e.id_cliente;";
//            String codigoSQL = "SELECT id_evento,nombre,date(fecha_hora) AS fecha,time(fecha_hora) AS hora ,numero_asistentes,id_cliente,id_lugar from eventos";
            ResultSet result = comandoSQL.executeQuery(codigoSQL);
            while (result.next()) {
                Integer id = result.getInt("e.id_evento");
                String nombre = result.getString("e.nombre");
                Date fecha_hora = result.getDate("fecha");
                Time hora = result.getTime("hora");
                Integer numero_asistentes = result.getInt("e.numero_asistentes");
                Integer id_cliente = result.getInt("e.id_cliente");
                String nombreCliente = result.getString("c.nombre");
                Integer id_lugar = result.getInt("id_lugar");
                String nombreLugar = result.getString("l.nombre");
                Evento evento = new Evento(id, nombre, fecha_hora, hora, numero_asistentes, id_cliente, nombreCliente, id_lugar, nombreLugar);
                lista.add(evento);
            }
            conexion.close();
            return lista;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return lista;
        }
    }
}
