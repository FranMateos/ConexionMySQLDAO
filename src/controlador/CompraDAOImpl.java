/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Datos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Cliente;
import modelo.Compra;


/**
 *
 * @author Personal
 */
public class CompraDAOImpl implements DAO{
    
    private String DB_TABLE = "compra";

    private void registerDriver() {
        try {
            Class.forName(Datos.JDBC_DRIVER).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.err.println("ERROR: fallo al cargar el Driver de la BBDD");
            e.printStackTrace();
        }
    }
   @Override
    public void insert(Object object){
        
        Compra compra = (Compra) object;
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(Datos.DB_URL, Datos.DB_USER, Datos.DB_PASS);
            // generar la query
            String query = "insert into " + DB_TABLE + " values(?, ?, ?, ?)";
            // preparar el statement, introduciendo los datos
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                // enviar el commando insert
                        stmt.setInt(1, compra.getID());
                        stmt.setInt(2, compra.getClientID());
                        stmt.setInt(3, compra.getProductID());
                        stmt.setDate(4, compra.getDate());
                        // ejecutar la sentencia
                        System.out.println("La sentencia se ha ejecutado correctamente");
            }
        } catch (SQLException e) {
            // si algo sale mal lanzar la excepción
            System.out.println("Error de conexión con la BBDD, " + e.getMessage());
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
    public void update(Object object){
        
        Compra compra = (Compra) object;
        
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(Datos.DB_URL, Datos.DB_USER, Datos.DB_PASS);
            // generar la query
            String query = "update " + DB_TABLE + " set id = ?, set clientid = ?, set productid = ?,"
                    + " set date = ? where ID = ?";
            // preparar el statement, introduciendo los datos
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                // enviar el commando insert
                        stmt.setInt(1, compra.getID());
                        stmt.setInt(2, compra.getClientID());
                        stmt.setInt(3, compra.getProductID());
                        stmt.setDate(4, compra.getDate());
                        stmt.setInt(5, compra.getID());
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
            String query = "delete from " + DB_TABLE + " where ID = ?";
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
    public Object select(Integer id){
        
        Connection conn = null;
        Compra compra = null;

        try {
            registerDriver();
            // abrir la conexion
            conn = DriverManager.getConnection(Datos.DB_URL, Datos.DB_USER, Datos.DB_PASS);
            // consulta select (selecciona el producto con ID especificado)
            try (PreparedStatement ps = conn.prepareStatement(
                    "select * from " + DB_TABLE + " where ID = ?")) {
                // indicar el ID que buscamos
                ps.setInt(1, id);
                // ejecutar la consulta
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // obtener cada una de la columnas y mapearlas a la clase Prueba
                        compra = new Compra(id,
                                rs.getInt("idclient"),
                                rs.getInt("idproduct"),
                                rs.getDate("date"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión con la BBDD, " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return compra;
    }
    
}
