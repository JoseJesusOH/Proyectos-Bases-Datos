package implementaciones;

import entidades.Cliente;
import interfaces.IClientesDAO;
import interfaces.IConexionBD;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Clase que realiza todas las oepraciones de cliente para la base de datos.
 *
 * @author Luis Gonzalo y José Jesús
 */
public class ClientesDAO implements IClientesDAO {

    /**
     * Conexion
     */
    private IConexionBD conexion;

    /**
     * Constructor que inicializa la conexion.
     *
     * @param conexion La conexion.
     */
    public ClientesDAO(IConexionBD conexion) {
        this.conexion = new ConexionBD();
    }

    /**
     * Agregar Cliente a la base de datos.
     *
     * @param cliente Cliente a agregar.
     * @return true si este fue agregado,false en caso Contrario.
     */
    @Override
    public boolean agregar(Cliente cliente) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException e) {
            System.err.println("No se pudo agregar el cliente.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Actualizar cliente de la base de datos.
     *
     * @param cliente Cliente a actualizar.
     * @return true si este fue agregado,false en caso Contrario.
     */
    @Override
    public boolean actualizar(Cliente cliente) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            Cliente clienteBuscado = em.find(Cliente.class, cliente.getId());
            if (clienteBuscado != null) {
                Long idCliente = clienteBuscado.getId();
                clienteBuscado = cliente;
                clienteBuscado.setId(idCliente);
                em.merge(clienteBuscado);
            }
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException e) {
            System.err.println("No se pudo actualizar el cliente.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Eliminar Cliente de la base de datos.
     *
     * @param idCliente ID del cliente a eliminar .
     * @return true si este fue agregado,false en caso Contrario.
     *
     */
    @Override
    public boolean eliminar(Long idCliente) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            Cliente clienteBuscado = em.find(Cliente.class, idCliente);
            if (clienteBuscado != null) {
                Cliente cliente2 = em.merge(clienteBuscado);
                em.remove(cliente2);
            }
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException e) {
            System.err.println("No se pudo eliminar el cliente.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Consultar cliente de la base de datos.
     *
     * @param idCliente ID del cliente a consultar.
     * @return El ID del cliente a consultar
     */
    @Override
    public Cliente consultar(Long idCliente) {
        try {
            EntityManager em = this.conexion.crearConexion();
            return em.find(Cliente.class, idCliente);
        } catch (IllegalStateException e) {
            System.err.println("No se pudo buscar al cliente ; " + idCliente);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Lista con todos los clientes de la base de datos.
     *
     * @return La lista de cliente.
     */
    @Override
    public List<Cliente> consultarTodos() {
        EntityManager em = this.conexion.crearConexion();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(Cliente.class);
        TypedQuery query = em.createQuery(criteria);
        return query.getResultList();
    }

    /**
     * Metodo para obtener un cliente en base al rfc.
     *
     * @param RFC RFC a consultar.
     * @return El cliente que coincida con el RFC, null en caso contrario.
     */
    @Override
    public Cliente consultarCliente(String RFC) {
        EntityManager em = this.conexion.crearConexion();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(Cliente.class);
        Root<Cliente> root = criteria.from(Cliente.class);
        criteria.select(root);
        criteria.where(builder.equal(root.<Cliente>get("rfc"), RFC));
        TypedQuery query = em.createQuery(criteria);
        List<Cliente> lista = query.getResultList();
        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

}
