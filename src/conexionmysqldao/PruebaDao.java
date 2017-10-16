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
public interface PruebaDao {
    
    public void insert(Prueba prueba);
    public void update(Prueba prueba);
    public void delete(Integer id);
    public Prueba select(Integer id);
    
}
