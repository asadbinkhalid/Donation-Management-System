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
public class Loan extends DisburseFund{
    
    private String returnDate;

    public Loan(int id, int amount, String returnDate) {
        super(id, amount);
        this.returnDate = returnDate;
    }
    
    public String getReturnDate(){
        return returnDate;
    }
    
}
