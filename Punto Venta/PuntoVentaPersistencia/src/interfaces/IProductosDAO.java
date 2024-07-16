package interfaces;

import entidades.Producto;
import java.util.List;

/**
 * Inteface de productos DAO.
 *
 * @author Luis Gonzalo y José Jesús
 */
public interface IProductosDAO {

    /**
     * Agregar Producto a la base de datos.
     *
     * @param producto Producto a agregar.
     * @return true si este fue agregado,false en caso Contrario.
     */
    public boolean agregar(Producto producto);

    /**
     * Actualizar producto de la base de datos.
     *
     * @param producto Producto a actualizar.
     * @return true si este fue agregado,false en caso Contrario.
     */
    public boolean actualizar(Producto producto);

    /**
     * Eliminar Producto de la base de datos.
     *
     * @param idProducto ID del Producto a eliminar .
     * @return true si este fue agregado,false en caso Contrario.
     *
     */
    public boolean eliminar(Long idProducto);

    /**
     * Metodo que obtiene un producto, de la base de datos. Los cuales pueden
     * ser productos que tienen un stock igual a cero o mayor a cero.
     *
     * @param operacion Operacion a realizar. 1= Producto que coincida.
     * 2=Verifica si el stock del producto no es igual a cero. regresa.
     * @param idProducto El id del producto.
     * @return El producto a consultar.
     */
    public Producto consultar(int operacion, Long idProducto);

    /**
     * Metodo para obtener todos dos tipos de lista de productos.Todos los
     * productos o una lista de los productos los cuales su stock sea mayor a
     * cero.
     *
     * @param operacion 1=Lista con todos los productos. 2=Lista con productos con stock mayor a cero
     * @return La lista de productos que coincidad.
     */
    public List<Producto> consultarTodos(int operacion);

    /**
     * Metodo para obtenr una lista de productos que coincidan con el nombre y
     * que estos sean mayores a cero.
     *
     * @param nombre Nombre del producto.
     * @return La lista de productos que coincidad.
     */
    public List<Producto> consultarProductosNombre(String nombre);

}
