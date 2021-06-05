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
public class Assessor extends Volunteer{
    
    private Needy beneficiaryAssess;     //association with needy

    public Assessor(String name) {
        super(name);
        beneficiaryAssess = null;
    }
    
    public void setBeneficiary(Needy obj){
        beneficiaryAssess = obj;
    }
    
    public Needy getBeneficiary(){
        return beneficiaryAssess;
    }
    
}
