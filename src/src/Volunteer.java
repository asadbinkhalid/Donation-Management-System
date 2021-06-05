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
public class Volunteer extends Person{

    public Volunteer(String name) {
        super(name);
    }
    
    
    @Override
       public String getType()
    {
        return "volunteer";
    }
   
}
