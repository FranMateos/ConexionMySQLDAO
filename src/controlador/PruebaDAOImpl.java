/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Prueba;
import controlador.PruebaDao;
import modelo.Datos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Personal
 */
public class PruebaDAOImpl implements PruebaDao{

    private void registerDriver() {
        try {
            Class.forName(Datos.JDBC_DRIVER).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.err.println("ERROR: fallo al cargar el Driver de la BBDD");
            e.printStackTrace();
        }
    }
   @Override
    public void insert(Prueba prueba){
        
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(Datos.DB_URL, Datos.DB_USER, Datos.DB_PASS);
            // generar la query
            String query = "insert into " + Datos.DB_TABLE + " values(?, ?, ?, ?, ?)";
            // preparar el statement, introduciendo los datos
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                // enviar el commando insert
                        stmt.setInt(1, prueba.getID());
                        stmt.setString(2, prueba.getName());
                        stmt.setInt(3, prueba.getPhone());
                        stmt.setString(4, prueba.getCountry());
                        stmt.setInt(5, prueba.getYears());
                        // ejecutar la sentencia
                        int i=stmt.executeUpdate();
                        System.out.println("I vale " +i);
            }
        } catch (SQLException e) {
            // si algo sale mal lanzar la excepción
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    // cerrar conexión
                    conn.close();
                } catch (SQLException e) {
                    // si algo falla al cerrar la conexión enviar salida
                    e.getMessage();
                }
            }
        }
    }
    
    @Override
    public void update(Prueba prueba){
        
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(Datos.DB_URL, Datos.DB_USER, Datos.DB_PASS);
            // generar la query
            String query = "update " + Datos.DB_TABLE + " set id = ?, set name = ?, set phone = ?,"
                    + " set country = ?, set years = ? where ID = ?";
            // preparar el statement, introduciendo los datos
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                // enviar el commando insert
                        stmt.setInt(1, prueba.getID());
                        stmt.setString(2, prueba.getName());
                        stmt.setInt(3, prueba.getPhone());
                        stmt.setString(4, prueba.getCountry());
                        stmt.setInt(5, prueba.getYears());
                        stmt.setInt(6, prueba.getID());
                        // ejecutar la sentencia
                        stmt.executeUpdate();
            }
        } catch (SQLException e) {
            // si algo sale mal lanzar la excepción
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    // cerrar conexión
                    conn.close();
                } catch (SQLException e) {
                    // si algo falla al cerrar la conexión enviar salida
                    e.getMessage();
                }
            }
        }
    }
    
    @Override
    public void delete(Integer id){
        
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(Datos.DB_URL, Datos.DB_USER, Datos.DB_PASS);
            // generar la query
            String query = "delete from " + Datos.DB_TABLE + " where ID = ?";
            // preparar el Statement
            PreparedStatement stmt = conn.prepareStatement(query);
           // Insertar el valor 
            stmt.setInt(1, id);
            // ejecutar
            int i = stmt.executeUpdate();
            if (i > 0){
                System.out.println("Datos borrados correctamente");
            }else{
                // lanzar excepción
                throw new SQLException("Error");
            }
        }catch(SQLException e){
            System.out.println("Error de base de datos");
        }
        
    }
    
    @Override
    public Prueba select(Integer id){
        
        Connection conn = null;
        Prueba prueba = null;

        try {
            registerDriver();
            // abrir la conexion
            conn = DriverManager.getConnection(Datos.DB_URL, Datos.DB_USER, Datos.DB_PASS);
            // consulta select (selecciona el producto con ID especificado)
            try (PreparedStatement ps = conn.prepareStatement(
                    "select * from " + Datos.DB_TABLE + " where ID = ?")) {
                // indicar el ID que buscamos
                ps.setInt(1, id);
                // ejecutar la consulta
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // obtener cada una de la columnas y mapearlas a la clase Prueba
                        prueba = new Prueba(id,
                                rs.getString("name"),
                                rs.getInt("phone"),
                                rs.getString("country"),
                                rs.getInt("years"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return prueba;
    }
    
}
