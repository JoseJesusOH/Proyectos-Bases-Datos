package implementaciones;

import entidades.Producto;
import entidades.Venta;
import interfaces.IConexionBD;
import interfaces.IProductosDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Clase que realiza todas la operaciones para los productos dentro de la base
 * de datos.
 *
 * @author Luis Gonzalo y José Jesús
 */
public class ProductosDAO implements IProductosDAO {

    /**
     * Conexion.
     */
    private IConexionBD conexion;

    /**
     * Constructor que inicializa la conexion.
     *
     * @param conexion La conexion.
     */
    public ProductosDAO(IConexionBD conexion) {
        this.conexion = new ConexionBD();
    }

    /**
     * Agregar Producto a la base de datos.
     *
     * @param producto Producto a agregar.
     * @return true si este fue agregado,false en caso Contrario.
     */
    @Override
    public boolean agregar(Producto producto) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException e) {
            System.err.println("No se pudo agregar el producto.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Actualizar producto de la base de datos.
     *
     * @param producto Producto a actualizar.
     * @return true si este fue agregado,false en caso Contrario.
     */
    @Override
    public boolean actualizar(Producto producto) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            Producto productoBuscado = em.find(Producto.class, producto.getId());
            if (productoBuscado != null) {
                Long idProducto=productoBuscado.getId();
                productoBuscado=producto;
                productoBuscado.setId(idProducto);
                em.merge(productoBuscado);
            }
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException e) {
            System.err.println("No se pudo actualizar el producto.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Eliminar Producto de la base de datos.
     *
     * @param idProducto ID del Producto a eliminar .
     * @return true si este fue agregado,false en caso Contrario.
     *
     */
    @Override
    public boolean eliminar(Long idProducto) {
        try {
            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            Producto productoBuscado = em.find(Producto.class, idProducto);
            if (productoBuscado != null) {
                Producto producto2 = em.merge(productoBuscado);
                em.remove(producto2);
            }
            em.getTransaction().commit();
            return true;
        } catch (IllegalStateException e) {
            System.err.println("No se pudo eliminar el producto.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Metodo que obtiene un producto, de la base de datos. Los cuales pueden
     * ser productos que tienen un stock igual a cero o mayor a cero.
     *
     * @param operacion Operacion a realizar. 1= Producto que coincida.
     * 2=Verifica si el stock del producto no es igual a cero. regresa.
     * @param idProducto El id del producto.
     * @return El producto a consultar.
     */
    @Override
    public Producto consultar(int operacion, Long idProducto) {
        EntityManager em = this.conexion.crearConexion();

        if (operacion == 1) {
            try {
                return em.find(Producto.class, idProducto);
            } catch (IllegalStateException e) {
                System.err.println("No se pudo buscar al producto; " + idProducto);
                e.printStackTrace();
                return null;
            }
        }
        Producto productoBuscado = null;
        if (operacion == 2) {
            try {
                productoBuscado = em.find(Producto.class, idProducto);
            } catch (IllegalStateException e) {
                System.err.println("No se pudo buscar al producto; " + idProducto);
                e.printStackTrace();
                return null;
            }
        }
        if (productoBuscado != null) {
            if (productoBuscado.getStock() == 0) {
                return null;
            } else {
                return productoBuscado;
            }
        }
        return null;
    }

    /**
     * Metodo para obtener todos dos tipos de lista de productos.Todos los
     * productos o una lista de los productos los cuales su stock sea mayor a
     * cero.
     *
     * @param operacion 1=Lista con todos los productos. 2=Lista con productos
     * con stock mayor a cero
     * @return La lista de productos que coincidad.
     */
    @Override
    public List<Producto> consultarTodos(int operacion) {
        EntityManager em = this.conexion.crearConexion();
        if (operacion == 1) {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery criteria = builder.createQuery(Producto.class);
            TypedQuery query = em.createQuery(criteria);
            return query.getResultList();
        }
        if (operacion == 2) {
            String jpqlQuery = "SELECT p FROM Producto p WHERE p.stock>0";
            TypedQuery query = em.createQuery(jpqlQuery, Venta.class);
            return query.getResultList();
        }
        return null;
    }

    /**
     * Metodo para obtenr una lista de productos que coincidan con el nombre y
     * que estos sean mayores a cero.
     *
     * @param nombre Nombre del producto.
     * @return La lista de productos que coincidad.
     */
    @Override
    public List<Producto> consultarProductosNombre(String nombre) {
        EntityManager em = this.conexion.crearConexion();
        String jpqlQuery = "SELECT p FROM Producto p WHERE p.nombre LIKE :nombre AND p.stock>0";
        TypedQuery query = em.createQuery(jpqlQuery, Venta.class);
        query.setParameter("nombre", "%" + nombre + "%");
        return query.getResultList();
    }

}
