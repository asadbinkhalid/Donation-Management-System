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
public class Pledge {
    

    private int id;
    private int amount;
    private String date;
    
    public Pledge(int amount, String date){
        this.amount = amount;
        this.date = date;
    }
    
    public String getDate(){
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getAmount(){
        return amount;
    }

    public int getId() {
        return id;
    }
}
