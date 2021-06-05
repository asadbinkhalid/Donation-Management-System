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
public class DisburseFund {
    
    private int id, amount;
    
    public DisburseFund(int id, int amount){
        this.id = id;
        this.amount = amount;        
    }
    public DisburseFund(int amount){
        this.amount = amount;        
    }
    
    public int getId(){
        return id;
    }
    
    public int getAmount(){
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setAmount(int amount){
        this.amount = amount;
    }
 
}
