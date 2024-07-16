package interfaces;

import entidades.RelacionProductoVenta;
import entidades.Venta;
import java.util.List;

/**
 * Inteface de relacion producto ventas DAO.
 *
 * @author Luis Gonzalo y José Jesús
 */
public interface IRelacionProductosVentasDAO {

    /**
     * Metodo que agrega relaciones a la base de datos.
     *
     * @param rpv Lista de relaciones a agregar.
     * @return true si la relacion fue agregada, false en caso contrario.
     */
    public boolean agregar(List<RelacionProductoVenta> rpv);

    /**
     * Obtenr las relaciones de ventas producto en base al id.
     *
     * @param idVenta El id de la venta.
     * @return La lista de relaciones.
     */
    public List<RelacionProductoVenta> consultarRelacionesVentaProducto(Long idVenta);
}
