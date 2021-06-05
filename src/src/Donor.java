/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fahad
 */
public class Donor extends Person {

    private List<Donation> donationsList;
    private List<Pledge> pledgesList;
    
    public Donor(String name) {
        super(name);
        donationsList = new ArrayList<>();
        pledgesList = new ArrayList<>();
    }
    
    public void addDonation(Donation obj){
        donationsList.add(obj);
    }
    
    public void addPledge(Pledge obj){
        pledgesList.add(obj);
    }
    
    public List<Pledge> getPledgesList(){
        return pledgesList;
    }
    
    public List<Donation> getDonationsList(){
        return donationsList;
    }
    
    @Override
    public String getType()
    {
        return "donor";
    }

    public void setDonationsList(List<Donation> donationsList) {
        this.donationsList = donationsList;
    }

    public void setPledgesList(List<Pledge> pledgesList) {
        this.pledgesList = pledgesList;
    }
    
}
