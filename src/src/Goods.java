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
public class Goods extends Donation {
    
    private int items;

    public Goods(Project obj, int items) {
        super(obj);
        this.items = items;
        obj.setAvailableFund(obj.getAvailableFund() + (items * 10));
    }
    
    public void setItems(int items){
        this.items = items;
    }
    
    public int getItems(){
        return items;
    }
    
    public String getType(){
        return "Goods";
    }

    @Override
    public int getAmount() {
        return -1;
    }
    
}
