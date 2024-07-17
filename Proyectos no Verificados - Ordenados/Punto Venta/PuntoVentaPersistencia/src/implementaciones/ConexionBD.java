package implementaciones;

import interfaces.IConexionBD;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * Clase que realiza la conexion a la base de datos.
 * @author Luis Gonzalo y José Jesús.
 */
public class ConexionBD implements IConexionBD {
    /**
     * Crear conexion a la case de datos.
     *
     * @return El EntityManager que establece la conexion.
     * @throws IllegalStateException Si algo en la conexion falla.
     */
    @Override
    public EntityManager crearConexion() throws IllegalStateException {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("PuntoVenta_DominioPU");
        EntityManager em = emFactory.createEntityManager();
        return em;
    }

}
