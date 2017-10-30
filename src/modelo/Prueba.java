/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Personal
 */
public class Prueba {
    
    int ID;
    String name;
    int phone;
    String country;
    int years;
    
    public Prueba(){
        
    }

    public Prueba(int ID, String name, int phone, String country, int years) {
        this.ID = ID;
        this.name = name;
        this.phone = phone;
        this.country = country;
        this.years = years;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    @Override
    public String toString() {
        return ("ID = " + ID + ", Nombre: " + name + ", Teléfono: " + phone + ", Ciudad: " + country + ", Edad: " + years); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
