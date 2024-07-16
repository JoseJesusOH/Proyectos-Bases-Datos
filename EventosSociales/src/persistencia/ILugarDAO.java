package persistencia;

import entidades.Lugar;
import java.util.List;

/**
 * Interface para Lugar.
 *
 * @author José Jesus Orozco Hernández ID; 00000229141
 */
public interface ILugarDAO {

    /**
     * Agregar Lugar a la base de datos.
     *
     * @param lugar Lugar a agregar
     * @return true si este fue agregado,false en caso Contrario.
     */
    public boolean agregar(Lugar lugar);

    /**
     * Actualizar lugar de la base de datos
     *
     * @param lugar Lugar a actualizar.
     * @return true si este fue agregado,false en caso Contrario.
     */
    public boolean actualizar(Lugar lugar);

    /**
     * Eliminar lugar de la base de datos.
     *
     * @param idLugar ID del lugar a eliminar }
     * @return true si este fue agregado,false en caso Contrario.
     *
     */
    public boolean eliminar(Integer idLugar);

    /**
     * Consultar lugar de la base de datos.
     *
     * @param idLugar ID del lugar a consultar.
     * @return El ID del cliente a consultar
     */
    public Lugar consultar(Integer idLugar);

    /**
     * Obtener todos los lugares de la base de datos.
     *
     * @return Lista con todos los lugares.
     */
    public List<Lugar> consultarTodos();
}

