/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author Fahad
 */
public abstract class Person {
    
   private String name;
   private int id;

    public void setId(int id) {
        this.id = id;
    }

    public Person(String name) {
        this.name = name;
        
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    
    public abstract String getType();
    
}
 