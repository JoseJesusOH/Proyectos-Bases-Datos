package interfaces;

import entidades.Cliente;
import java.util.List;

/**
 * Inteface de cliente DAO.
 *
 * @author Luis Gonzalo y José Jesús
 */
public interface IClientesDAO {

    /**
     * Agregar Cliente a la base de datos.
     *
     * @param cliente Cliente a agregar.
     * @return true si este fue agregado,false en caso Contrario.
     */
    public boolean agregar(Cliente cliente);

    /**
     * Actualizar cliente de la base de datos.
     *
     * @param cliente Cliente a actualizar.
     * @return true si este fue agregado,false en caso Contrario.
     */
    public boolean actualizar(Cliente cliente);

    /**
     * Eliminar Cliente de la base de datos.
     *
     * @param idCliente ID del cliente a eliminar .
     * @return true si este fue agregado,false en caso Contrario.
     *
     */
    public boolean eliminar(Long idCliente);

    /**
     * Consultar cliente de la base de datos.
     *
     * @param idCliente ID del cliente a consultar.
     * @return El ID del cliente a consultar
     */
    public Cliente consultar(Long idCliente);

    /**
     * Lista con todos los clientes de la base de datos.
     *
     * @return La lista de cliente.
     */
    public List<Cliente> consultarTodos();

    /**
     * Metodo para obtener un cliente en base al rfc.
     *
     * @param RFC RFC a consultar.
     * @return El cliente que coincida con el RFC, null en caso contrario.
     */
    public Cliente consultarCliente(String RFC);
}
