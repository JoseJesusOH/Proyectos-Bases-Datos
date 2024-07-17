package implementaciones;

import interfaces.IClientesDAO;
import interfaces.IConexionBD;
import interfaces.IProductosDAO;
import interfaces.IRelacionProductosVentasDAO;
import interfaces.IVentasDAO;

/**
 * Clase que realiza las conexiones o que actua como fabrica de conexiones.
 *
 * @author Luis Gonzalo y José Jesús
 */
public class DAOSFactory {

    /**
     * Conexion
     */
    private static final IConexionBD conexion = new ConexionBD();

    /**
     * Crear clientes DAO.
     *
     * @return Clientes DAO.
     */
    public static IClientesDAO crearClientesDAO() {
        return new ClientesDAO(conexion);
    }

    /**
     * Crear Productos DAO.
     *
     * @return Productos DAO.
     */
    public static IProductosDAO crearProductosDAO() {
        return new ProductosDAO(conexion);
    }

    /**
     * Crear Ventas DAO.
     *
     * @return Ventas DAO.
     */
    public static IVentasDAO crearVentasDAO() {
        return new VentasDAO(conexion);
    }

    /**
     * Crear Relación Producto Venta DAO.
     *
     * @return Relación Producto Venta DAO.
     */
    public static IRelacionProductosVentasDAO crearRelacionProductosVentasDAO() {
        return new RelacionProductosVentasDAO(conexion);
    }

}
