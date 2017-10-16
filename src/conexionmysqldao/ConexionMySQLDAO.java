/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionmysqldao;

/**
 *
 * @author Personal
 */
public class ConexionMySQLDAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         PruebaDao prueba = new PruebaDAOImpl();
        
        // agregar nuevo producto
       // prueba.insert(new Prueba(3, "Evaristo Lamela Sánchez", 354564565, "Illescas", 56));
        // obtener el producto con ID = 100
        Prueba p = prueba.select(3);
        System.out.println(p);
        
        // eliminar el producto con ID = 3
         prueba.delete(3);
        p = prueba.select(3);
        System.out.println(p);
        
    }
    
}
