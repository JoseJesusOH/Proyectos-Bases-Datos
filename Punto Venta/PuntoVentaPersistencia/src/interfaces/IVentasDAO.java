package interfaces;

import entidades.Venta;
import java.util.Calendar;
import java.util.List;

/**
 * Inteface de ventas DAO.
 *
 * @author Luis Gonzalo y José Jesús
 */
public interface IVentasDAO {

    /**
     * Metedo que agregara una venta a la base de datos.
     *
     * @param venta La venta a agregar.
     * @return true si la operacion fue un exito, false en caso contrario.
     */
    public boolean agregar(Venta venta);

    /**
     * Metodo que consultara una venta en base al id.
     *
     * @param idVenta Id de la venta a consultar.
     * @return La venta si fue posible obtenerla, null en caso contrario.
     */
    public Venta consultar(Long idVenta);

    /**
     * Metodo que obtiene la el id de la ultima venta registrada.
     *
     * @return El id de la venta.
     */
    public Long idUltimaVenta();

    /**
     * Metodo que devuelve la lista con todas las ventas de la base de datos.
     *
     * @return La lista de ventas.
     */
    public List<Venta> consultarTodos();

    /**
     * Metodo para obtener las ventas en base al id del cliente.
     *
     * @param idCliente El id del cliente.
     * @return La lista de ventas que realizo el cliente, null en caso de que
     * este no haya realizado alguna venta.
     */
    public List<Venta> consultarVentasCliente(Long idCliente);

    /**
     * Metodo para obtener las fechas de acuerdo a un determinado periodo de
     * fechas.
     *
     * @param fechaDesde La fecha inicial.
     * @param fechaHasta La fecha final.
     * @return La lista de ventas que esten dentro de ese rango, null en caso de
     * que no exista ninguna.
     */
    public List<Venta> consultarVentasTodosPeriodo(Calendar fechaDesde, Calendar fechaHasta);

    /**
     * Metodo que obtiene la lista de ventas en un determinado periodo de fechas
     * y que estas coincidan con el id del cliente.
     *
     * @param idCliente El id del cliente.
     * @param fechaDesde La fecha inicial.
     * @param fechaHasta La fecha final.
     * @return La lista de ventas que esten dentro de ese rango y que coincidad
     * con el id del cliente, null en caso de que no exista ninguna.
     */
    public List<Venta> consultarVentasPeriodoCliente(Long idCliente, Calendar fechaDesde, Calendar fechaHasta);
}
