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
import objetosNegocio.BoletoDetalle;
import objetosNegocio.Lugar;

/**
 *
 * @author josej
 */
public class BoletoPersistencia {

    Conexion conexion;

    public BoletoPersistencia() {
        conexion = new Conexion();
    }

    public void insertarBoleto(BoletoDetalle boleto) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String insertar = String.format("INSERT INTO Boletos (salida_id, pasajero_id,tipo_boleto,estatus,fecha_y_hora) VALUES (%d,%d,'%s','%s','%s')", boleto.getSalida_id(), boleto.getPasajero_id(), boleto.getTipo_boleto(), boleto.getFecha_boleto(), boleto.getEstatus());
            s.executeUpdate(insertar);
            conexion.close();
            System.out.println("Se ha registrado exitosamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public BoletoDetalle obtenerBoletoSimple(int id) {
        BoletoDetalle b = null;
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = "SELECT * FROM Boletos WHERE boleto_id=" + id;
            ResultSet registro = s.executeQuery(consulta);
            if (registro.next()) {
                b = new BoletoDetalle();
                int boleto_id = registro.getInt("boleto_id");
                int salida_id = registro.getInt("salida_id");
                int pasajero_id = registro.getInt("pasajero_id");
                String tipo_boleto = registro.getString("tipo_boleto");
                String estatus = registro.getString("estatus");
                String fecha_y_hora = registro.getString("fecha_y_hora");
                b.setBoleto_id(boleto_id);
                b.setPasajero_id(pasajero_id);
                b.setSalida_id(salida_id);
                b.setTipo_boleto(tipo_boleto);
                b.setFecha_boleto(fecha_y_hora);
                b.setEstatus(estatus);
            }
            conexion.close();
        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
        }
        return b;
    }

    public int obtenerBoletoLast() {
        BoletoDetalle b = null;
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = "SELECT top 1 * FROM Boletos order by boleto_id desc";
            ResultSet registro = s.executeQuery(consulta);
            if (registro.next()) {
                b = new BoletoDetalle();
                int boleto_id = registro.getInt("boleto_id");
                int salida_id = registro.getInt("salida_id");
                int pasajero_id = registro.getInt("pasajero_id");
                String tipo_boleto = registro.getString("tipo_boleto");
                String estatus = registro.getString("estatus");
                String fecha_y_hora = registro.getString("fecha_y_hora");
                b.setBoleto_id(boleto_id);
                b.setPasajero_id(pasajero_id);
                b.setSalida_id(salida_id);
                b.setTipo_boleto(tipo_boleto);
                b.setFecha_boleto(fecha_y_hora);
                b.setEstatus(estatus);
            }
            conexion.close();
        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
        }
        return b.getBoleto_id();
    }

    public BoletoDetalle obtenerBoletoDetallado(int id) {
        BoletoDetalle b = null;
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = "SELECT b.boleto_id,b.pasajero_id, p.nombre AS Nombre_Pasajero,b.salida_id,o.lugar_id AS Origen_ID,o.nombre_estacion AS Estacion_Origen,d.lugar_id AS Destino_ID,d.nombre_estacion AS Estacion_Destino, t.tren_id, t.nombre AS Nombre_tren, b.tipo_boleto,b.fecha_y_hora AS Fecha_Boleto, b.estatus FROM Boletos AS b INNER JOIN Pasajero AS P ON b.pasajero_id=p.pasajero_id INNER JOIN Salidas as s ON b.salida_id=s.salida_id INNER JOIN Lugar AS o ON s.origen_id=o.lugar_id INNER JOIN Lugar AS d ON d.lugar_id=s.destino_id INNER JOIN Tren AS t ON t.tren_id=s.tren_id WHERE b.boleto_id=" + id;
            ResultSet registro = s.executeQuery(consulta);
            if (registro.next()) {
                b = new BoletoDetalle();
                int boleto_id = registro.getInt("boleto_id");
                int pasajero_id = registro.getInt("pasajero_id");
                String nombre_pasajero = registro.getString("Nombre_Pasajero");
                int salida_id = registro.getInt("salida_id");
                int origen_id = registro.getInt("Origen_ID");
                String origen_nombre = registro.getString("Estacion_Origen");
                int destino_id = registro.getInt("Destino_id");
                String destino_nombre = registro.getString("Estacion_Destino");
                int tren_id = registro.getInt("tren_id");
                String nombre_tren = registro.getString("Nombre_tren");
                String tipo_boleto = registro.getString("tipo_boleto");
                String fecha_y_hora = registro.getString("Fecha_Boleto");
                String estatus = registro.getString("estatus");
                b.setBoleto_id(boleto_id);
                b.setPasajero_id(pasajero_id);
                b.setNombre_pasajero(nombre_pasajero);
                b.setSalida_id(salida_id);
                b.setOrigen_id(origen_id);
                b.setOrigen_nombre(origen_nombre);
                b.setDestino_id(destino_id);
                b.setDestino_nombre(destino_nombre);
                b.setTren_id(tren_id);
                b.setTren_nombre(nombre_tren);
                b.setTipo_boleto(tipo_boleto);
                b.setFecha_boleto(fecha_y_hora);
                b.setEstatus(estatus);
            }
            conexion.close();
        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
        }
        return b;
    }

    public void cancelarBoleto(int id) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String cancelar = "UPDATE Boletos SET estatus='Cancelado' WHERE boleto_id=" + id;
            s.executeUpdate(cancelar);
            conexion.close();
            System.out.println("Se ha cancelado exitosamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void restablecerBoleto(int id) {
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String cancelar = "UPDATE Boletos SET estatus='Confirmado' WHERE boleto_id=" + id;
            s.executeUpdate(cancelar);
            conexion.close();
            System.out.println("Se ha cancelado exitosamente");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ArrayList<BoletoDetalle> consultarTodosEsperaParaPreguntar(int salida) {
        ArrayList<BoletoDetalle> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = "SELECT b.boleto_id,b.pasajero_id, p.nombre AS Nombre_Pasajero,b.salida_id,o.lugar_id AS Origen_ID,o.nombre_estacion AS Estacion_Origen,d.lugar_id AS Destino_ID,d.nombre_estacion AS Estacion_Destino, t.tren_id, t.nombre AS Nombre_tren, b.tipo_boleto,b.fecha_y_hora AS Fecha_Boleto, b.estatus FROM Boletos AS b INNER JOIN Pasajero AS P ON b.pasajero_id=p.pasajero_id INNER JOIN Salidas as s ON b.salida_id=s.salida_id INNER JOIN Lugar AS o ON s.origen_id=o.lugar_id INNER JOIN Lugar AS d ON d.lugar_id=s.destino_id INNER JOIN Tren AS t ON t.tren_id=s.tren_id WHERE b.estatus='Espera' AND s.salida_id=" + salida;
            ResultSet registro = s.executeQuery(consulta);
            while (registro.next()) {
                BoletoDetalle b = new BoletoDetalle();
                int boleto_id = registro.getInt("boleto_id");
                int pasajero_id = registro.getInt("pasajero_id");
                String nombre_pasajero = registro.getString("Nombre_Pasajero");
                int salida_id = registro.getInt("salida_id");
                int origen_id = registro.getInt("Origen_ID");
                String origen_nombre = registro.getString("Estacion_Origen");
                int destino_id = registro.getInt("Destino_id");
                String destino_nombre = registro.getString("Estacion_Destino");
                int tren_id = registro.getInt("tren_id");
                String nombre_tren = registro.getString("Nombre_tren");
                String tipo_boleto = registro.getString("tipo_boleto");
                String fecha_y_hora = registro.getString("Fecha_Boleto");
                String estatus = registro.getString("estatus");
                b.setBoleto_id(boleto_id);
                b.setPasajero_id(pasajero_id);
                b.setNombre_pasajero(nombre_pasajero);
                b.setSalida_id(salida_id);
                b.setOrigen_id(origen_id);
                b.setOrigen_nombre(origen_nombre);
                b.setDestino_id(destino_id);
                b.setDestino_nombre(destino_nombre);
                b.setTren_id(tren_id);
                b.setTren_nombre(nombre_tren);
                b.setTipo_boleto(tipo_boleto);
                b.setFecha_boleto(fecha_y_hora);
                b.setEstatus(estatus);
                lista.add(b);
            }
            conexion.close();
            return lista;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<BoletoDetalle> consultarTodosVendidos() {
        ArrayList<BoletoDetalle> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = "SELECT b.boleto_id,b.pasajero_id, p.nombre AS Nombre_Pasajero,b.salida_id,o.lugar_id AS Origen_ID,o.nombre_estacion AS Estacion_Origen,d.lugar_id AS Destino_ID,d.nombre_estacion AS Estacion_Destino, t.tren_id, t.nombre AS Nombre_tren, b.tipo_boleto,b.fecha_y_hora AS Fecha_Boleto, b.estatus FROM Boletos AS b INNER JOIN Pasajero AS P ON b.pasajero_id=p.pasajero_id INNER JOIN Salidas as s ON b.salida_id=s.salida_id INNER JOIN Lugar AS o ON s.origen_id=o.lugar_id INNER JOIN Lugar AS d ON d.lugar_id=s.destino_id INNER JOIN Tren AS t ON t.tren_id=s.tren_id WHERE b.estatus='Confirmado';";
            ResultSet registro = s.executeQuery(consulta);
            while (registro.next()) {
                BoletoDetalle b = new BoletoDetalle();
                int boleto_id = registro.getInt("boleto_id");
                int pasajero_id = registro.getInt("pasajero_id");
                String nombre_pasajero = registro.getString("Nombre_Pasajero");
                int salida_id = registro.getInt("salida_id");
                int origen_id = registro.getInt("Origen_ID");
                String origen_nombre = registro.getString("Estacion_Origen");
                int destino_id = registro.getInt("Destino_id");
                String destino_nombre = registro.getString("Estacion_Destino");
                int tren_id = registro.getInt("tren_id");
                String nombre_tren = registro.getString("Nombre_tren");
                String tipo_boleto = registro.getString("tipo_boleto");
                String fecha_y_hora = registro.getString("Fecha_Boleto");
                String estatus = registro.getString("estatus");
                b.setBoleto_id(boleto_id);
                b.setPasajero_id(pasajero_id);
                b.setNombre_pasajero(nombre_pasajero);
                b.setSalida_id(salida_id);
                b.setOrigen_id(origen_id);
                b.setOrigen_nombre(origen_nombre);
                b.setDestino_id(destino_id);
                b.setDestino_nombre(destino_nombre);
                b.setTren_id(tren_id);
                b.setTren_nombre(nombre_tren);
                b.setTipo_boleto(tipo_boleto);
                b.setFecha_boleto(fecha_y_hora);
                b.setEstatus(estatus);
                lista.add(b);
            }
            conexion.close();
            return lista;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<BoletoDetalle> consultarBoletosFiltroEspecial(String filtro) {
        ArrayList<BoletoDetalle> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = filtro;
            ResultSet registro = s.executeQuery(consulta);
            while (registro.next()) {
                BoletoDetalle b = new BoletoDetalle();
                int boleto_id = registro.getInt("boleto_id");
                int pasajero_id = registro.getInt("pasajero_id");
                String nombre_pasajero = registro.getString("Nombre_Pasajero");
                int salida_id = registro.getInt("salida_id");
                int origen_id = registro.getInt("Origen_ID");
                String origen_nombre = registro.getString("Estacion_Origen");
                int destino_id = registro.getInt("Destino_id");
                String destino_nombre = registro.getString("Estacion_Destino");
                int tren_id = registro.getInt("tren_id");
                String nombre_tren = registro.getString("Nombre_tren");
                String tipo_boleto = registro.getString("tipo_boleto");
                String fecha_y_hora = registro.getString("Fecha_Boleto");
                String estatus = registro.getString("estatus");
                b.setBoleto_id(boleto_id);
                b.setPasajero_id(pasajero_id);
                b.setNombre_pasajero(nombre_pasajero);
                b.setSalida_id(salida_id);
                b.setOrigen_id(origen_id);
                b.setOrigen_nombre(origen_nombre);
                b.setDestino_id(destino_id);
                b.setDestino_nombre(destino_nombre);
                b.setTren_id(tren_id);
                b.setTren_nombre(nombre_tren);
                b.setTipo_boleto(tipo_boleto);
                b.setFecha_boleto(fecha_y_hora);
                b.setEstatus(estatus);
                lista.add(b);
            }
            conexion.close();
            return lista;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<BoletoDetalle> consultarTodosFiltroVendidos(String filtro) {
        ArrayList<BoletoDetalle> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consultaBase = "SELECT b.boleto_id,b.pasajero_id, p.nombre AS Nombre_Pasajero,b.salida_id,o.lugar_id AS Origen_ID,o.nombre_estacion AS Estacion_Origen,d.lugar_id AS Destino_ID,d.nombre_estacion AS Estacion_Destino, t.tren_id, t.nombre AS Nombre_tren, b.tipo_boleto,b.fecha_y_hora AS Fecha_Boleto, b.estatus FROM Boletos AS b INNER JOIN Pasajero AS P ON b.pasajero_id=p.pasajero_id INNER JOIN Salidas as s ON b.salida_id=s.salida_id INNER JOIN Lugar AS o ON s.origen_id=o.lugar_id INNER JOIN Lugar AS d ON d.lugar_id=s.destino_id INNER JOIN Tren AS t ON t.tren_id=s.tren_id WHERE b.estatus='Confirmado' AND ";
            String consulta = consultaBase + filtro;
            ResultSet registro = s.executeQuery(consulta);
            while (registro.next()) {
                BoletoDetalle b = new BoletoDetalle();
                int boleto_id = registro.getInt("boleto_id");
                int pasajero_id = registro.getInt("pasajero_id");
                String nombre_pasajero = registro.getString("Nombre_Pasajero");
                int salida_id = registro.getInt("salida_id");
                int origen_id = registro.getInt("Origen_ID");
                String origen_nombre = registro.getString("Estacion_Origen");
                int destino_id = registro.getInt("Destino_id");
                String destino_nombre = registro.getString("Estacion_Destino");
                int tren_id = registro.getInt("tren_id");
                String nombre_tren = registro.getString("Nombre_tren");
                String tipo_boleto = registro.getString("tipo_boleto");
                String fecha_y_hora = registro.getString("Fecha_Boleto");
                String estatus = registro.getString("estatus");
                b.setBoleto_id(boleto_id);
                b.setPasajero_id(pasajero_id);
                b.setNombre_pasajero(nombre_pasajero);
                b.setSalida_id(salida_id);
                b.setOrigen_id(origen_id);
                b.setOrigen_nombre(origen_nombre);
                b.setDestino_id(destino_id);
                b.setDestino_nombre(destino_nombre);
                b.setTren_id(tren_id);
                b.setTren_nombre(nombre_tren);
                b.setTipo_boleto(tipo_boleto);
                b.setFecha_boleto(fecha_y_hora);
                b.setEstatus(estatus);
                lista.add(b);
            }
            conexion.close();
            return lista;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<BoletoDetalle> consultarTodosEspera() {
        ArrayList<BoletoDetalle> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = "SELECT b.boleto_id,b.pasajero_id, p.nombre AS Nombre_Pasajero,b.salida_id,o.lugar_id AS Origen_ID,o.nombre_estacion AS Estacion_Origen,d.lugar_id AS Destino_ID,d.nombre_estacion AS Estacion_Destino, t.tren_id, t.nombre AS Nombre_tren, b.tipo_boleto,b.fecha_y_hora AS Fecha_Boleto, b.estatus FROM Boletos AS b INNER JOIN Pasajero AS P ON b.pasajero_id=p.pasajero_id INNER JOIN Salidas as s ON b.salida_id=s.salida_id INNER JOIN Lugar AS o ON s.origen_id=o.lugar_id INNER JOIN Lugar AS d ON d.lugar_id=s.destino_id INNER JOIN Tren AS t ON t.tren_id=s.tren_id WHERE b.estatus='Espera'";
            ResultSet registro = s.executeQuery(consulta);
            while (registro.next()) {
                BoletoDetalle b = new BoletoDetalle();
                int boleto_id = registro.getInt("boleto_id");
                int pasajero_id = registro.getInt("pasajero_id");
                String nombre_pasajero = registro.getString("Nombre_Pasajero");
                int salida_id = registro.getInt("salida_id");
                int origen_id = registro.getInt("Origen_ID");
                String origen_nombre = registro.getString("Estacion_Origen");
                int destino_id = registro.getInt("Destino_id");
                String destino_nombre = registro.getString("Estacion_Destino");
                int tren_id = registro.getInt("tren_id");
                String nombre_tren = registro.getString("Nombre_tren");
                String tipo_boleto = registro.getString("tipo_boleto");
                String fecha_y_hora = registro.getString("Fecha_Boleto");
                String estatus = registro.getString("estatus");
                b.setBoleto_id(boleto_id);
                b.setPasajero_id(pasajero_id);
                b.setNombre_pasajero(nombre_pasajero);
                b.setSalida_id(salida_id);
                b.setOrigen_id(origen_id);
                b.setOrigen_nombre(origen_nombre);
                b.setDestino_id(destino_id);
                b.setDestino_nombre(destino_nombre);
                b.setTren_id(tren_id);
                b.setTren_nombre(nombre_tren);
                b.setTipo_boleto(tipo_boleto);
                b.setFecha_boleto(fecha_y_hora);
                b.setEstatus(estatus);
                lista.add(b);
            }
            conexion.close();
            return lista;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<BoletoDetalle> consultarTodosFiltroEspera(String filtro) {
        ArrayList<BoletoDetalle> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consultaBase = "SELECT b.boleto_id,b.pasajero_id, p.nombre AS Nombre_Pasajero,b.salida_id,o.lugar_id AS Origen_ID,o.nombre_estacion AS Estacion_Origen,d.lugar_id AS Destino_ID,d.nombre_estacion AS Estacion_Destino, t.tren_id, t.nombre AS Nombre_tren, b.tipo_boleto,b.fecha_y_hora AS Fecha_Boleto, b.estatus FROM Boletos AS b INNER JOIN Pasajero AS P ON b.pasajero_id=p.pasajero_id INNER JOIN Salidas as s ON b.salida_id=s.salida_id INNER JOIN Lugar AS o ON s.origen_id=o.lugar_id INNER JOIN Lugar AS d ON d.lugar_id=s.destino_id INNER JOIN Tren AS t ON t.tren_id=s.tren_id WHERE b.estatus='Espera' AND ";
            String consulta = consultaBase + filtro;
            ResultSet registro = s.executeQuery(consulta);
            while (registro.next()) {
                BoletoDetalle b = new BoletoDetalle();
                int boleto_id = registro.getInt("boleto_id");
                int pasajero_id = registro.getInt("pasajero_id");
                String nombre_pasajero = registro.getString("Nombre_Pasajero");
                int salida_id = registro.getInt("salida_id");
                int origen_id = registro.getInt("Origen_ID");
                String origen_nombre = registro.getString("Estacion_Origen");
                int destino_id = registro.getInt("Destino_id");
                String destino_nombre = registro.getString("Estacion_Destino");
                int tren_id = registro.getInt("tren_id");
                String nombre_tren = registro.getString("Nombre_tren");
                String tipo_boleto = registro.getString("tipo_boleto");
                String fecha_y_hora = registro.getString("Fecha_Boleto");
                String estatus = registro.getString("estatus");
                b.setBoleto_id(boleto_id);
                b.setPasajero_id(pasajero_id);
                b.setNombre_pasajero(nombre_pasajero);
                b.setSalida_id(salida_id);
                b.setOrigen_id(origen_id);
                b.setOrigen_nombre(origen_nombre);
                b.setDestino_id(destino_id);
                b.setDestino_nombre(destino_nombre);
                b.setTren_id(tren_id);
                b.setTren_nombre(nombre_tren);
                b.setTipo_boleto(tipo_boleto);
                b.setFecha_boleto(fecha_y_hora);
                b.setEstatus(estatus);
                lista.add(b);
            }
            conexion.close();
            return lista;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<BoletoDetalle> consultarTodosCancelados() {
        ArrayList<BoletoDetalle> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consulta = "SELECT b.boleto_id,b.pasajero_id, p.nombre AS Nombre_Pasajero,b.salida_id,o.lugar_id AS Origen_ID,o.nombre_estacion AS Estacion_Origen,d.lugar_id AS Destino_ID,d.nombre_estacion AS Estacion_Destino, t.tren_id, t.nombre AS Nombre_tren, b.tipo_boleto,b.fecha_y_hora AS Fecha_Boleto, b.estatus FROM Boletos AS b INNER JOIN Pasajero AS P ON b.pasajero_id=p.pasajero_id INNER JOIN Salidas as s ON b.salida_id=s.salida_id INNER JOIN Lugar AS o ON s.origen_id=o.lugar_id INNER JOIN Lugar AS d ON d.lugar_id=s.destino_id INNER JOIN Tren AS t ON t.tren_id=s.tren_id WHERE b.estatus='Cancelado' ";
            ResultSet registro = s.executeQuery(consulta);
            while (registro.next()) {
                BoletoDetalle b = new BoletoDetalle();
                int boleto_id = registro.getInt("boleto_id");
                int pasajero_id = registro.getInt("pasajero_id");
                String nombre_pasajero = registro.getString("Nombre_Pasajero");
                int salida_id = registro.getInt("salida_id");
                int origen_id = registro.getInt("Origen_ID");
                String origen_nombre = registro.getString("Estacion_Origen");
                int destino_id = registro.getInt("Destino_id");
                String destino_nombre = registro.getString("Estacion_Destino");
                int tren_id = registro.getInt("tren_id");
                String nombre_tren = registro.getString("Nombre_tren");
                String tipo_boleto = registro.getString("tipo_boleto");
                String fecha_y_hora = registro.getString("Fecha_Boleto");
                String estatus = registro.getString("estatus");
                b.setBoleto_id(boleto_id);
                b.setPasajero_id(pasajero_id);
                b.setNombre_pasajero(nombre_pasajero);
                b.setSalida_id(salida_id);
                b.setOrigen_id(origen_id);
                b.setOrigen_nombre(origen_nombre);
                b.setDestino_id(destino_id);
                b.setDestino_nombre(destino_nombre);
                b.setTren_id(tren_id);
                b.setTren_nombre(nombre_tren);
                b.setTipo_boleto(tipo_boleto);
                b.setFecha_boleto(fecha_y_hora);
                b.setEstatus(estatus);
                lista.add(b);
            }
            conexion.close();
            return lista;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<BoletoDetalle> consultarTodosFiltroCancelados(String filtro) {
        ArrayList<BoletoDetalle> lista = new ArrayList<>();
        try {
            Connection conexion = this.conexion.crearConexion();
            Statement s = conexion.createStatement();
            String consultaBase = "SELECT b.boleto_id,b.pasajero_id, p.nombre AS Nombre_Pasajero,b.salida_id,o.lugar_id AS Origen_ID,o.nombre_estacion AS Estacion_Origen,d.lugar_id AS Destino_ID,d.nombre_estacion AS Estacion_Destino, t.tren_id, t.nombre AS Nombre_tren, b.tipo_boleto,b.fecha_y_hora AS Fecha_Boleto, b.estatus FROM Boletos AS b INNER JOIN Pasajero AS P ON b.pasajero_id=p.pasajero_id INNER JOIN Salidas as s ON b.salida_id=s.salida_id INNER JOIN Lugar AS o ON s.origen_id=o.lugar_id INNER JOIN Lugar AS d ON d.lugar_id=s.destino_id INNER JOIN Tren AS t ON t.tren_id=s.tren_id WHERE b.estatus='Cancelado' AND ";
            String consulta = consultaBase + filtro;
            ResultSet registro = s.executeQuery(consulta);
            while (registro.next()) {
                BoletoDetalle b = new BoletoDetalle();
                int boleto_id = registro.getInt("boleto_id");
                int pasajero_id = registro.getInt("pasajero_id");
                String nombre_pasajero = registro.getString("Nombre_Pasajero");
                int salida_id = registro.getInt("salida_id");
                int origen_id = registro.getInt("Origen_ID");
                String origen_nombre = registro.getString("Estacion_Origen");
                int destino_id = registro.getInt("Destino_id");
                String destino_nombre = registro.getString("Estacion_Destino");
                int tren_id = registro.getInt("tren_id");
                String nombre_tren = registro.getString("Nombre_tren");
                String tipo_boleto = registro.getString("tipo_boleto");
                String fecha_y_hora = registro.getString("Fecha_Boleto");
                String estatus = registro.getString("estatus");
                b.setBoleto_id(boleto_id);
                b.setPasajero_id(pasajero_id);
                b.setNombre_pasajero(nombre_pasajero);
                b.setSalida_id(salida_id);
                b.setOrigen_id(origen_id);
                b.setOrigen_nombre(origen_nombre);
                b.setDestino_id(destino_id);
                b.setDestino_nombre(destino_nombre);
                b.setTren_id(tren_id);
                b.setTren_nombre(nombre_tren);
                b.setTipo_boleto(tipo_boleto);
                b.setFecha_boleto(fecha_y_hora);
                b.setEstatus(estatus);
                lista.add(b);
            }
            conexion.close();
            return lista;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

}
