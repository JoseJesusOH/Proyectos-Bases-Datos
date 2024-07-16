/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import objetosNegocio.Pasajero;
import objetosNegocio.Tren;

/**
 *
 * @author josej
 */
public class PasajeroPersistencia {

    Conexion conexion;

    public PasajeroPersistencia() {
        conexion = new Conexion();
    }

    public void insertarPasajero(Pasajero pasajero) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String insertar = String.format("INSERT INTO Pasajero (nombre,apellido_paterno,apellido_materno,fecha_nacimiento,genero,telefono,direccion) VALUES('%s','%s','%s','%s','%s','%s','%s')",
                    pasajero.getNombre(), pasajero.getApellido_paterno(), pasajero.getApellido_materno(), pasajero.getFecha_nacimiento(), pasajero.getGenero(), pasajero.getTelefono(), pasajero.getDireccion());
            s.executeUpdate(insertar);
            conexion.close();
            System.out.println("Se ha registrado exitosamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void actualizarPasajero(Pasajero pasajero) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String actualizar = String.format("UPDATE Pasajero SET nombre='%s',apellido_paterno='%s',apellido_materno='%s',fecha_nacimiento='%s',genero='%s',telefono='%s',direccion='%s' WHERE pasajero_id=%d",
                    pasajero.getNombre(), pasajero.getApellido_paterno(), pasajero.getApellido_materno(), pasajero.getFecha_nacimiento(), pasajero.getGenero(), pasajero.getTelefono(), pasajero.getDireccion(), pasajero.getPasajero_id());
            s.executeUpdate(actualizar);
            conexion.close();
            System.out.println("Se ha actualizado exitosamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void eliminarPasajero(int id) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String eliminar = "DELETE FROM Pasajero WHERE pasajero_id=" + id;
            s.executeUpdate(eliminar);
            conexion.close();
            System.out.println("Se ha eliminado exitosamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Pasajero obtenerPasajero(int id) {
        Pasajero p = new Pasajero();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = "select * from Pasajero where pasajero_id=" + id;
            ResultSet registro = s.executeQuery(consulta);
            if (registro.next()) {
                int pasajero_id = registro.getInt("pasajero_id");
                String nombre = registro.getString("nombre");
                String apellido_paterno = registro.getString("apellido_paterno");
                String fecha_nacimiento = registro.getString("fecha_nacimiento");
                String generoS = registro.getString("genero");
                char genero = generoS.charAt(0);
                String telefono = registro.getString("telefono");
                String direccion = registro.getString("direccion");
                p.setPasajero_id(pasajero_id);
                p.setNombre(nombre);
                p.setApellido_paterno(apellido_paterno);
                p.setApellido_materno(apellido_paterno);
                p.setFecha_nacimiento(fecha_nacimiento);
                p.setGenero(genero);
                p.setTelefono(telefono);
                p.setDireccion(direccion);
            }
            conexion.close();
            return p;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public int obtenerPasajeroLastID() {
        Pasajero p = new Pasajero();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = "select top 1 * from Pasajero  order by pasajero_id desc";
            ResultSet registro = s.executeQuery(consulta);
            if (registro.next()) {
                int pasajero_id = registro.getInt("pasajero_id");
                String nombre = registro.getString("nombre");
                String apellido_paterno = registro.getString("apellido_paterno");
                String fecha_nacimiento = registro.getString("fecha_nacimiento");
                String generoS = registro.getString("genero");
                char genero = generoS.charAt(0);
                String telefono = registro.getString("telefono");
                String direccion = registro.getString("direccion");
                p.setPasajero_id(pasajero_id);
                p.setNombre(nombre);
                p.setApellido_paterno(apellido_paterno);
                p.setApellido_materno(apellido_paterno);
                p.setFecha_nacimiento(fecha_nacimiento);
                p.setGenero(genero);
                p.setTelefono(telefono);
                p.setDireccion(direccion);
            }
            conexion.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return p.getPasajero_id();
    }

    public Pasajero obtenerPasajeroLast() {
        Pasajero p = new Pasajero();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = "select top 1 * from Pasajero  order by pasajero_id desc";
            ResultSet registro = s.executeQuery(consulta);
            if (registro.next()) {
                int pasajero_id = registro.getInt("pasajero_id");
                String nombre = registro.getString("nombre");
                String apellido_paterno = registro.getString("apellido_paterno");
                String fecha_nacimiento = registro.getString("fecha_nacimiento");
                String generoS = registro.getString("genero");
                char genero = generoS.charAt(0);
                String telefono = registro.getString("telefono");
                String direccion = registro.getString("direccion");
                p.setPasajero_id(pasajero_id);
                p.setNombre(nombre);
                p.setApellido_paterno(apellido_paterno);
                p.setApellido_materno(apellido_paterno);
                p.setFecha_nacimiento(fecha_nacimiento);
                p.setGenero(genero);
                p.setTelefono(telefono);
                p.setDireccion(direccion);
            }
            conexion.close();
            return p;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<Pasajero> consultarTodos() {
        ArrayList<Pasajero> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = "select * from Pasajero";
            ResultSet registro = s.executeQuery(consulta);
            while (registro.next()) {
                Pasajero p = new Pasajero();
                int pasajero_id = registro.getInt("pasajero_id");
                String nombre = registro.getString("nombre");
                String apellido_paterno = registro.getString("apellido_paterno");
                String fecha_nacimiento = registro.getString("fecha_nacimiento");
                String generoS = registro.getString("genero");
                char genero = generoS.charAt(0);
                String telefono = registro.getString("telefono");
                String direccion = registro.getString("direccion");
                p.setPasajero_id(pasajero_id);
                p.setNombre(nombre);
                p.setApellido_paterno(apellido_paterno);
                p.setApellido_materno(apellido_paterno);
                p.setFecha_nacimiento(fecha_nacimiento);
                p.setGenero(genero);
                p.setTelefono(telefono);
                p.setDireccion(direccion);
                lista.add(p);
            }
            conexion.close();
            return lista;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return lista;
    }

    public ArrayList<Pasajero> consultarTodosFiltro(String comandoFiltro) {
        ArrayList<Pasajero> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = comandoFiltro;
            ResultSet registro = s.executeQuery(consulta);
            while (registro.next()) {
                Pasajero p = new Pasajero();
                int pasajero_id = registro.getInt("pasajero_id");
                String nombre = registro.getString("nombre");
                String apellido_paterno = registro.getString("apellido_paterno");
                String fecha_nacimiento = registro.getString("fecha_nacimiento");
                String generoS = registro.getString("genero");
                char genero = generoS.charAt(0);
                String telefono = registro.getString("telefono");
                String direccion = registro.getString("direccion");
                p.setPasajero_id(pasajero_id);
                p.setNombre(nombre);
                p.setApellido_paterno(apellido_paterno);
                p.setApellido_materno(apellido_paterno);
                p.setFecha_nacimiento(fecha_nacimiento);
                p.setGenero(genero);
                p.setTelefono(telefono);
                p.setDireccion(direccion);
                lista.add(p);
            }
            conexion.close();
            return lista;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
