package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import objetosNegocio.Salida;
import objetosNegocio.Tren;

public class SalidaPersistencia {

    Conexion conexion;

    public SalidaPersistencia() {
        conexion = new Conexion();
    }

    public void insertarSalida(Salida salida) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();                                                                                      //nombre,modelo,año,numero_pasajeros,asientos_primera,dias_operacion
            String insertar = String.format("INSERT INTO Salidas (tren_id,origen_id,destino_id,fecha_y_hora,precio_boleto_general,precio_boleto_p_clase,disponibilidad_a_primera_c,disponibilidad_general,disponibilidad_espera) VALUES ('%d','%d','%d','%s','%s','%s','%s ','%s ','%s')", salida.getTren_id(), salida.getOrigen_id(), salida.getDestino_id(), salida.getFecha() + " " + salida.getHora(), salida.getPrecio_boleto_general(), salida.getPrecio_boleto_p_clase(), 5, 5, 2);
            s.executeUpdate(insertar);
            conexion.close();
            System.out.println("Se ha registrado exitosamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void actualizarSalida(Salida salida) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String actualizar = String.format("UPDATE Salidas SET tren_id=%d,origen_id=%d,destino_id=%d,fecha_y_hora='%s',precio_boleto_general=%s,precio_boleto_p_clase=%s,disponibilidad_a_primera_c=%d,disponibilidad_general=%d,disponibilidad_espera=%d WHERE salida_id=%d",
                    salida.getTren_id(), salida.getOrigen_id(), salida.getDestino_id(), salida.getFecha() + " " + salida.getHora(), salida.getPrecio_boleto_general(), salida.getPrecio_boleto_p_clase(), salida.getDisponibilidad_a_primera_c(), salida.getDisponibilidad_general(), salida.getDisponibilidad_espera(), salida.getSalida_id());
            s.executeUpdate(actualizar);
            conexion.close();
            System.out.println("Se ha actualizado exitosamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void actualizarDisponibilidaGeneral(int salida_id, int oprecion) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String actualizar = "";
            if (oprecion == 0) {
                actualizar = "UPDATE Salidas SET disponibilidad_general=disponibilidad_general-1 WHERE salida_id=" + salida_id;
            } else if (oprecion == 1) {
                actualizar = "UPDATE Salidas SET disponibilidad_general=disponibilidad_general+1 WHERE salida_id=" + salida_id;
            }
            s.executeUpdate(actualizar);
            conexion.close();
            System.out.println("Se ha actualizado exitosamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void actualizarDisponibilidaPrimera(int salida_id, int opcion) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String actualizar = "";
            if (opcion == 0) {
                actualizar = "UPDATE Salidas SET disponibilidad_a_primera_c=disponibilidad_a_primera_c-1 WHERE salida_id=" + salida_id;
            } else if (opcion == 1) {
                actualizar = "UPDATE Salidas SET disponibilidad_a_primera_c=disponibilidad_a_primera_c+1 WHERE salida_id=" + salida_id;
            }
            s.executeUpdate(actualizar);
            conexion.close();
            System.out.println("Se ha actualizado exitosamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void actualizarDisponibilidaEspera(int salida_id, int opcion) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String actualizar = "";
            if (opcion == 0) {
                actualizar = "UPDATE Salidas SET disponibilidad_espera=disponibilidad_espera-1 WHERE salida_id=" + salida_id;
            } else if (opcion == 1) {
                actualizar = "UPDATE Salidas SET disponibilidad_espera=disponibilidad_espera+1 WHERE salida_id=" + salida_id;
            }
            s.executeUpdate(actualizar);
            conexion.close();
            System.out.println("Se ha actualizado exitosamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void eliminarSalida(int id) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String eliminar = "DELETE FROM Salidas WHERE salida_id=" + id;
            s.executeUpdate(eliminar);
            conexion.close();
            System.out.println("Se ha eliminado exitosamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Salida obtenerSalida(int id) {
        Salida sf = new Salida();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = "SELECT salida_id,tren_id,origen_id,destino_id,(CONVERT(date,fecha_y_hora)) AS Fecha,CONCAT(CONCAT(DATEPART(HOUR,fecha_y_hora),' ',':'),' ',DATEPART(MINUTE,fecha_y_hora)) AS Hora,precio_boleto_general,precio_boleto_p_clase,disponibilidad_general,disponibilidad_a_primera_c,disponibilidad_espera FROM Salidas where salida_id=" + id;
            ResultSet registro = s.executeQuery(consulta);
            if (registro.next()) {
                int salida_id = registro.getInt("salida_id");
                int tren_id = registro.getInt("tren_id");
                int origen_id = registro.getInt("origen_id");
                int destino_id = registro.getInt("destino_id");
                String fecha = registro.getString("Fecha");
                String horaAuxiliar = registro.getString("Hora");
                String hora = horaAuxiliar.length() == 3 ? horaAuxiliar + "0" : horaAuxiliar;
//             disponibilidad_general,disponibilidad_a_primera_c,disponibilidad_espera FROM Salidas where salida_id="+id;
                String precio_boleto_general = registro.getString("precio_boleto_general");
                String precio_boleto_p_clase = registro.getString("precio_boleto_p_clase");
                short disponibilidad_general = registro.getShort("disponibilidad_general");
                short disponibilidad_a_primera_c = registro.getShort("disponibilidad_a_primera_c");
                short disponibilidad_espera = registro.getShort("disponibilidad_espera");
                sf.setTren_id(tren_id);
                sf.setOrigen_id(origen_id);
                sf.setDestino_id(destino_id);
                sf.setFecha(fecha);
                sf.setHora(hora);
                sf.setPrecio_boleto_general(precio_boleto_general);
                sf.setPrecio_boleto_p_clase(precio_boleto_p_clase);
                sf.setDisponibilidad_general(disponibilidad_general);
                sf.setDisponibilidad_a_primera_c(disponibilidad_a_primera_c);
                sf.setDisponibilidad_espera(disponibilidad_espera);
                sf.setSalida_id(salida_id);
                return sf;
            }
            conexion.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<Salida> consultarTodos() {
        ArrayList<Salida> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = "SELECT salida_id,tren_id,origen_id,destino_id,(CONVERT(date,fecha_y_hora)) AS Fecha,CONCAT(CONCAT(DATEPART(HOUR,fecha_y_hora),' ',':'),' ',DATEPART(MINUTE,fecha_y_hora)) AS Hora,precio_boleto_general,precio_boleto_p_clase,disponibilidad_general,disponibilidad_a_primera_c,disponibilidad_espera FROM Salidas";
            ResultSet registro = s.executeQuery(consulta);
            while (registro.next()) {
                Salida sf = new Salida();
                int salida_id = registro.getInt("salida_id");
                int tren_id = registro.getInt("tren_id");
                int origen_id = registro.getInt("origen_id");
                int destino_id = registro.getInt("destino_id");
                String fecha = registro.getString("Fecha");
                String horaAuxiliar = registro.getString("Hora");
                String hora = horaAuxiliar.length() == 3 ? horaAuxiliar + "0" : horaAuxiliar;
                String precio_boleto_general = registro.getString("precio_boleto_general");
                String precio_boleto_p_clase = registro.getString("precio_boleto_p_clase");
                short disponibilidad_general = registro.getShort("disponibilidad_general");
                short disponibilidad_a_primera_c = registro.getShort("disponibilidad_a_primera_c");
                short disponibilidad_espera = registro.getShort("disponibilidad_espera");
                sf.setTren_id(tren_id);
                sf.setOrigen_id(origen_id);
                sf.setDestino_id(destino_id);
                sf.setFecha(fecha);
                sf.setHora(hora);
                sf.setPrecio_boleto_general(precio_boleto_general);
                sf.setPrecio_boleto_p_clase(precio_boleto_p_clase);
                sf.setDisponibilidad_general(disponibilidad_general);
                sf.setDisponibilidad_a_primera_c(disponibilidad_a_primera_c);
                sf.setDisponibilidad_espera(disponibilidad_espera);
                sf.setSalida_id(salida_id);
                lista.add(sf);
            }
            conexion.close();
            return lista;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return lista;
    }

    public ArrayList<Salida> consultarTodosFiltro(String comandoFiltro) {
        ArrayList<Salida> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = comandoFiltro;
            ResultSet registro = s.executeQuery(consulta);
            while (registro.next()) {
                Salida sf = new Salida();
                int salida_id = registro.getInt("salida_id");
                int tren_id = registro.getInt("tren_id");
                int origen_id = registro.getInt("origen_id");
                int destino_id = registro.getInt("destino_id");
                String fecha = registro.getString("Fecha");
                String horaAuxiliar = registro.getString("Hora");
                String hora = horaAuxiliar.length() == 3 ? horaAuxiliar + "0" : horaAuxiliar;
                String precio_boleto_general = registro.getString("precio_boleto_general");
                String precio_boleto_p_clase = registro.getString("precio_boleto_p_clase");
                short disponibilidad_general = registro.getShort("disponibilidad_general");
                short disponibilidad_a_primera_c = registro.getShort("disponibilidad_a_primera_c");
                short disponibilidad_espera = registro.getShort("disponibilidad_espera");
                sf.setTren_id(tren_id);
                sf.setOrigen_id(origen_id);
                sf.setDestino_id(destino_id);
                sf.setFecha(fecha);
                sf.setHora(hora);
                sf.setPrecio_boleto_general(precio_boleto_general);
                sf.setPrecio_boleto_p_clase(precio_boleto_p_clase);
                sf.setDisponibilidad_general(disponibilidad_general);
                sf.setDisponibilidad_a_primera_c(disponibilidad_a_primera_c);
                sf.setDisponibilidad_espera(disponibilidad_espera);
                sf.setSalida_id(salida_id);
                lista.add(sf);
            }
            conexion.close();
            return lista;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

}
