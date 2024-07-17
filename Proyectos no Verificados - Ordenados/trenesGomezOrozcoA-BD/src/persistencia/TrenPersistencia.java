package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import objetosNegocio.Lugar;
import objetosNegocio.Tren;

public class TrenPersistencia {

    Conexion conexion;

    public TrenPersistencia() {
        conexion = new Conexion();
    }

    public void insertarTren(Tren tren) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();                                                                                      //nombre,modelo,año,numero_pasajeros,asientos_primera,dias_operacion
            String insertar = String.format("INSERT INTO Tren (nombre,modelo,año,numero_pasajeros,asientos_primera,dias_operacion) VALUES ('%s','%s','%s','%s','%s','%s')", tren.getNomdre(), tren.getModelo(), tren.getAño(), tren.getNumero_pasajeros(), tren.getAsientos_primera(), tren.getDias_operacion());
            s.executeUpdate(insertar);
            conexion.close();
            System.out.println("Se ha registrado exitosamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void actualizarTren(Tren tren) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String actualizar = String.format("UPDATE Tren set nombre='%s', modelo='%s',año='%s',numero_pasajeros='%s',asientos_primera='%s',dias_operacion='%s' WHERE tren_id=%d",
                    tren.getNomdre(), tren.getModelo(), tren.getAño(), tren.getNumero_pasajeros(), tren.getAsientos_primera(), tren.getDias_operacion(), tren.getTren_id());
            s.executeUpdate(actualizar);
            conexion.close();
            System.out.println("Se ha actualizado exitosamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void eliminarTren(int id) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String eliminar = "DELETE FROM Tren WHERE tren_id=" + id;
            s.executeUpdate(eliminar);
            conexion.close();
            System.out.println("Se ha eliminado exitosamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Tren obtenerTren(int id) {
        Tren t = new Tren();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = "select * from Tren where tren_id=" + id;
            ResultSet registro = s.executeQuery(consulta);
            if (registro.next()) {
                int tren_id = registro.getInt("tren_id");
                String nombre = registro.getString("nombre");
                String modelo = registro.getString("modelo");
                short año = registro.getShort("año");
                short numero_pasajeros = registro.getShort("numero_pasajeros");
                short asientos_primera = registro.getShort("asientos_primera");
                String dias_operacion = registro.getString("dias_operacion");
                t.setTren_id(tren_id);
                t.setNomdre(nombre);
                t.setModelo(modelo);
                t.setAño(año);
                t.setNumero_pasajeros(numero_pasajeros);
                t.setAsientos_primera(asientos_primera);
                t.setDias_operacion(dias_operacion);
                return t;
            }
            conexion.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<Tren> consultarTodos() {
        ArrayList<Tren> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = "select * from Tren";
            ResultSet registro = s.executeQuery(consulta);
            while (registro.next()) {
                Tren t = new Tren();
                int tren_id = registro.getInt("tren_id");
                String nombre = registro.getString("nombre");
                String modelo = registro.getString("modelo");
                short año = registro.getShort("año");
                short numero_pasajeros = registro.getShort("numero_pasajeros");
                short asientos_primera = registro.getShort("asientos_primera");
                String dias_operacion = registro.getString("dias_operacion");
                t.setTren_id(tren_id);
                t.setNomdre(nombre);
                t.setModelo(modelo);
                t.setAño(año);
                t.setNumero_pasajeros(numero_pasajeros);
                t.setAsientos_primera(asientos_primera);
                t.setDias_operacion(dias_operacion);
                lista.add(t);
            }
            conexion.close();
            return lista;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return lista;
    }

    public ArrayList<Tren> consultarTodosFiltro(String comandoFiltro) {
        ArrayList<Tren> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = comandoFiltro;
            ResultSet registro = s.executeQuery(consulta);
            while (registro.next()) {
                Tren t = new Tren();
                int tren_id = registro.getInt("tren_id");
                String nombre = registro.getString("nombre");
                String modelo = registro.getString("modelo");
                short año = registro.getShort("año");
                short numero_pasajeros = registro.getShort("numero_pasajeros");
                short asientos_primera = registro.getShort("asientos_primera");
                String dias_operacion = registro.getString("dias_operacion");
                t.setTren_id(tren_id);
                t.setNomdre(nombre);
                t.setModelo(modelo);
                t.setAño(año);
                t.setNumero_pasajeros(numero_pasajeros);
                t.setAsientos_primera(asientos_primera);
                t.setDias_operacion(dias_operacion);
                lista.add(t);
            }
            conexion.close();
            return lista;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
