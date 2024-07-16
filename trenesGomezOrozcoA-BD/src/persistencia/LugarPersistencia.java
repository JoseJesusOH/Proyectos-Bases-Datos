/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import objetosNegocio.Lugar;
import vista.Menu;

/**
 *
 * @author josej
 */
public class LugarPersistencia {

    Conexion conexion;

    public LugarPersistencia() {
        conexion = new Conexion();
    }

    public void insertarLugar(Lugar lugar) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String insertar = String.format("INSERT INTO Lugar (nombre_estacion,estado,ciudad) VALUES ('%s','%s','%s')",
                    lugar.getNombre_estacion(), lugar.getEstado(), lugar.getCiudad());
            s.executeUpdate(insertar);
            conexion.close();
            System.out.println("Se ha registrado exitosamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void actualizarLugar(Lugar lugar) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String actualizar = String.format("UPDATE Lugar SET nombre_estacion='%s', ciudad='%s',estado='%s' WHERE lugar_id=%d",
                    lugar.getNombre_estacion(), lugar.getCiudad(), lugar.getEstado(), lugar.getLugar_id());
            s.executeUpdate(actualizar);
            conexion.close();
            System.out.println("Se ha actualizado exitosamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void eliminarLugar(int id) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String eliminar = "DELETE FROM Lugar WHERE lugar_id=" + id;
            s.executeUpdate(eliminar);
            conexion.close();
            System.out.println("Se ha eliminado exitosamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Lugar obtenerLugar(int id) {
        Lugar l = new Lugar();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = "select * from Lugar where lugar_id=" + id;
            ResultSet registro = s.executeQuery(consulta);
            if (registro.next()) {
                int lugar_id = registro.getInt("lugar_id");
                String nombre_estacion = registro.getString("nombre_estacion");
                String estado = registro.getString("estado");
                String ciudad = registro.getString("ciudad");
                l.setLugar_id(lugar_id);
                l.setNombre_estacion(nombre_estacion);
                l.setEstado(estado);
                l.setCiudad(ciudad);
                return l;
            }
            conexion.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<Lugar> consultarTodos() {
        ArrayList<Lugar> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = "select * from Lugar";
            ResultSet registro = s.executeQuery(consulta);
            while (registro.next()) {
                Lugar l = new Lugar();
                int lugar_id = registro.getInt("lugar_id");
                String nombre_estacion = registro.getString("nombre_estacion");
                String estado = registro.getString("estado");
                String ciudad = registro.getString("ciudad");
                l.setLugar_id(lugar_id);
                l.setNombre_estacion(nombre_estacion);
                l.setEstado(estado);
                l.setCiudad(ciudad);
                lista.add(l);
            }
            conexion.close();
            return lista;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return lista;
    }

    public ArrayList<Lugar> consultarTodosFiltro(String comandoFiltro) {
        ArrayList<Lugar> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = comandoFiltro;
            ResultSet registro = s.executeQuery(consulta);
            while (registro.next()) {
                Lugar l = new Lugar();
                int lugar_id = registro.getInt("lugar_id");
                String nombre_estacion = registro.getString("nombre_estacion");
                String estado = registro.getString("estado");
                String ciudad = registro.getString("ciudad");
                l.setLugar_id(lugar_id);
                l.setNombre_estacion(nombre_estacion);
                l.setEstado(estado);
                l.setCiudad(ciudad);
                lista.add(l);
            }
            conexion.close();
            return lista;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
