package persistencia;

import entidades.Evento;
import java.util.List;

/**
 * Interface para evento.
 *
 * @author José Jesus Orozco Hernández ID; 00000229141
 */
public interface IEventoDAO {

    /**
     * Agregar Evento a la base de datos.
     *
     * @param evento Evento a agregar
     * @return true si este fue agregado,false en caso Contrario.
     */
    public boolean agregar(Evento evento);

    /**
     * Actualizar evento de la base de datos
     *
     * @param evento Evento a actualizar.
     * @return true si este fue agregado,false en caso Contrario.
     */
    public boolean actualizar(Evento evento);

    /**
     * Eliminar Evento de la base de datos.
     *
     * @param idEvento ID del evento a eliminar }
     * @return true si este fue agregado,false en caso Contrario.
     *
     */
    public boolean eliminar(Integer idEvento);

    /**
     * Consultar evento de la base de datos.
     *
     * @param idEvento ID del evento a consultar.
     * @return El ID del cliente a consultar
     */
    public Evento consultar(Integer idEvento);

    /**
     * Obtener todos los eventos de la base de datos.
     *
     * @return Lista con todos los eventos.
     */
    public List<Evento> consultarTodos();
}
