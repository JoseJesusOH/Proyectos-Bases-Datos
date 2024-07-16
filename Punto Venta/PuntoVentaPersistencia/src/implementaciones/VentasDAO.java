package implementaciones;

import entidades.Cliente;
import entidades.Venta;
import interfaces.IConexionBD;
import interfaces.IVentasDAO;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Clase que realiza todas las oepraciones de la venta para la base de datos.
 *
 * @author Luis Gonzalo y José Jesús
 */
public class VentasDAO implements IVentasDAO {

    /**
     * Conexion
     */
    private IConexionBD conexion;

    /**
     * Constructor que inicializa la conexion.
     *
     * @param conexion La conexion.
     */
    public VentasDAO(IConexionBD conexion) {
        this.conexion = new ConexionBD();
    }

    /**
     * Metedo que agregara una venta a la base de datos.
     *
     * @param venta La venta a agregar.
     * @return true si la operacion fue un exito, false en caso contrario.
     */
    @Override
    public boolean agregar(Venta venta) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
//            CLiente cliente=venta.ge
            em.merge(venta);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException e) {
            System.err.println("No se pudo agregar la venta.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Metodo que consultara una venta en base al id.
     *
     * @param idVenta Id de la venta a consultar.
     * @return La venta si fue posible obtenerla, null en caso contrario.
     */
    @Override
    public Venta consultar(Long idVenta) {
        try {
            EntityManager em = this.conexion.crearConexion();
            return em.find(Venta.class, idVenta);
        } catch (IllegalStateException e) {
            System.err.println("No se pudo buscar la venta ; " + idVenta);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo que obtiene la el id de la ultima venta registrada.
     *
     * @return El id de la venta.
     */
    @Override
    public Long idUltimaVenta() {
        EntityManager em = this.conexion.crearConexion();
        String jpqlQuery = "SELECT MAX(v.id) FROM Venta v";
        Long id = (Long) em.createQuery(jpqlQuery).getSingleResult();
        return id;
    }

    /**
     * Metodo que devuelve la lista con todas las ventas de la base de datos.
     *
     * @return La lista de ventas.
     */
    @Override
    public List<Venta> consultarTodos() {
        EntityManager em = this.conexion.crearConexion();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(Venta.class);
        TypedQuery query = em.createQuery(criteria);
        return query.getResultList();
    }

    /**
     * Metodo para obtener las ventas en base al id del cliente.
     *
     * @param idCliente El id del cliente.
     * @return La lista de ventas que realizo el cliente, null en caso de que
     * este no haya realizado alguna venta.
     */
    @Override
    public List<Venta> consultarVentasCliente(Long idCliente) {
        EntityManager em = this.conexion.crearConexion();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(Venta.class);
        Root<Venta> root = criteria.from(Venta.class);
        criteria.select(root);
        criteria.where(builder.equal(root.<Cliente>get("id"), idCliente));
        TypedQuery query = em.createQuery(criteria);
        return query.getResultList();
    }

    /**
     * Metodo para obtener las fechas de acuerdo a un determinado periodo de
     * fechas.
     *
     * @param calendarDesde La fecha inicial.
     * @param calendarHasta La fecha final.
     * @return La lista de ventas que esten dentro de ese rango, null en caso de
     * que no exista ninguna.
     */
    @Override
    public List<Venta> consultarVentasTodosPeriodo(Calendar calendarDesde, Calendar calendarHasta) {
        EntityManager em = this.conexion.crearConexion();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(Venta.class);
        Root<Venta> root = criteria.from(Venta.class);
        criteria.select(root);
        criteria.where(builder.between(root.<Calendar>get("fecha"), calendarDesde, calendarHasta));
        TypedQuery query = em.createQuery(criteria);
        return query.getResultList();
    }

    /**
     * Metodo que obtiene la lista de ventas en un determinado periodo de fechas
     * y que estas coincidan con el id del cliente.
     *
     * @param idCliente El id del cliente.
     * @param calendarDesde La fecha inicial.
     * @param calendarHasta La fecha final.
     * @return La lista de ventas que esten dentro de ese rango y que coincidad
     * con el id del cliente, null en caso de que no exista ninguna.
     */
    @Override
    public List<Venta> consultarVentasPeriodoCliente(Long idCliente, Calendar calendarDesde, Calendar calendarHasta) {
        EntityManager em = this.conexion.crearConexion();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(Venta.class);
        Root<Venta> root = criteria.from(Venta.class);
        criteria.select(root);
        criteria.where(builder.and(builder.equal(root.<Cliente>get("id"), idCliente), builder.between(root.<Calendar>get("fecha"), calendarDesde, calendarHasta)));
        TypedQuery query = em.createQuery(criteria);
        return query.getResultList();
    }

}
