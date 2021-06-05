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
public class Fund extends Donation {
    
    private int amount;

    public Fund(Project obj, int amount) {
        super(obj);
        this.amount = amount;
        obj.setAvailableFund(obj.getAvailableFund() + amount);
    }
    
    public void setAmount(int amount){
        this.amount = amount;
    }
    
    public int getAmount(){
        return amount;
    }
    
    public String getType(){
        return "Fund";
    }

    @Override
    public int getItems() {
        return 0;
    }


    
}
