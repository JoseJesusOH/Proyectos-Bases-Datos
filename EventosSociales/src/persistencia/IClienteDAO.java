package persistencia;

import entidades.Cliente;
import java.util.List;

/**
 * Interface para cliente.
 *
 * @author José Jesus Orozco Hernández ID; 00000229141
 */
public interface IClienteDAO {

    /**
     * Agregar Cliente a la base de datos.
     *
     * @param cliente Cliente a agregar
     * @return true si este fue agregado,false en caso Contrario.
     */
    public boolean agregar(Cliente cliente);

    /**
     * Actualizar cliente de la base de datos
     *
     * @param cliente Cliente a actualizar.
     * @return true si este fue agregado,false en caso Contrario.
     */
    public boolean actualizar(Cliente cliente);

    /**
     * Eliminar Cliente de la base de datos.
     *
     * @param idCliente ID del cliente a eliminar }
     * @return true si este fue agregado,false en caso Contrario.
     *
     */
    public boolean eliminar(Integer idCliente);

    /**
     * Consultar cliente de la base de datos.
     *
     * @param idCliente ID del cliente a consultar.
     * @return El ID del cliente a consultar
     */
    public Cliente consultar(Integer idCliente);

    /**
     * Obtener todos los clientes de la base de datos.
     *
     * @return Lista con todos los clientes.
     */
    public List<Cliente> consultarTodos();
}
