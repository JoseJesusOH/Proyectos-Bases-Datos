package implementaciones;

import entidades.Producto;
import entidades.RelacionProductoVenta;
import entidades.Venta;
import interfaces.IConexionBD;
import interfaces.IRelacionProductosVentasDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Clase Relacion venta productos que agrega una relacion a la base de datos.
 *
 * @author Luis Gonzalo y José Jesús
 */
public class RelacionProductosVentasDAO implements IRelacionProductosVentasDAO {

    /**
     * Conexion.
     */
    private IConexionBD conexion;

    /**
     * Constructor que inicaliza la conexion dentro de la base de datos.
     *
     * @param conexion La conexion.
     */
    public RelacionProductosVentasDAO(IConexionBD conexion) {
        this.conexion = new ConexionBD();
    }

    /**
     * Metodo que agrega realaciones a la base de datos.
     *
     * @param rpv Lista de relaciones a agregar.
     * @return true si la relacion fue agregada, false en caso contrario.
     */
    @Override
    public boolean agregar(List<RelacionProductoVenta> rpv) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            for (int i = 0; i < rpv.size(); i++) {
                RelacionProductoVenta get = rpv.get(i);
                Venta venta = get.getVenta();
                Producto producto = get.getProducto();
                get.setVenta(null);
                get.setProducto(null);
                get.setProducto(producto);
                get.setVenta(venta);
                em.merge(get);
            }
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException e) {
            System.err.println("No se pudo agregar la venta.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Metodo para obtener las relaciones de ventas en base al id de esta.
     *
     * @param idVenta El id de la venta.
     * @return La lista de relaciones de la venta, null en caso de que este no
     * haya realizado alguna relacion.
     */
    @Override
    public List<RelacionProductoVenta> consultarRelacionesVentaProducto(Long idVenta) {
             EntityManager em = this.conexion.crearConexion();
            String jpqlQuery = "SELECT rpv FROM RelacionProductoVenta rpv WHERE rpv.venta.id=:idVen";
            TypedQuery query = em.createQuery(jpqlQuery, Venta.class);
            query.setParameter("idVen",idVenta);
            return query.getResultList();

    }
}
