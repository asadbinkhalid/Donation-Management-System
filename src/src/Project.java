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
public class Project {

    
    
    private String name, description;
    private int id, availableFund;    
 
    private List<Volunteer> teamList;
    private Volunteer teamLead;
    private List<Needy> beneficiariesList;
    
    private List<Donation> donationList;        //association from donation

    
    
    public Project(String name, String description) {
        this.name = name;
        this.description = description;
        this.availableFund = 0;
        this.beneficiariesList = new ArrayList<>();
        this.teamList = new ArrayList<>();
        this.donationList = new ArrayList<>();
        teamLead = null;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addVolunteer(Volunteer obj){
        teamList.add(obj);
    }
    
    public void setTeamLead(Volunteer obj){
        teamLead = obj;
    }
    
    public void addNeedy(Needy obj){
        beneficiariesList.add(obj);
    }
    
    public void addDonation(Donation obj){
        donationList.add(obj);
    }
    
    public void addToAvailableFund(int fund){
        availableFund = availableFund + fund;
    }
    
    public int getAvailableFund(){
        return availableFund;
    }
    
    public List<Volunteer> getTeamList(){
        return teamList;
    }
    
    public List<Needy> getBeneficiariesList(){
        return beneficiariesList;
    }
    
    public int getId(){
        return id;
    }
    
    public String getDescription(){
        return description;
    }
    
    public List<Donation> getDonationList(){
        return donationList;
    }
    
    public Volunteer getTeamLead(){
        return teamLead;
    }
    
    public boolean replaceVolunteer(Volunteer old, Volunteer newV){
        for(int i=0; i<teamList.size(); i++){
            if(old.getId() == teamList.get(i).getId()){
                teamList.set(i, newV);
                return true;
            }
        }
        return false;    
    }
    
    public boolean replaceVolunteer(int oldId, Volunteer newV){
        for(int i=0; i<teamList.size(); i++){
            if(oldId == teamList.get(i).getId()){
                teamList.set(i, newV);
                return true;
            }
        }
        return false;    
    }

    void removeTeamMember(Volunteer member) {
    
        for(int i = 0;i<teamList.size();i++){
            
            if(member.getId() == teamList.get(i).getId()){
                teamList.remove(i);
                break;
            }
        }

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvailableFund(int availableFund) {
        this.availableFund = availableFund;
    }

    public void setTeamList(List<Volunteer> teamList) {
        this.teamList = teamList;
    }

    public void setBeneficiariesList(List<Needy> beneficiariesList) {
        this.beneficiariesList = beneficiariesList;
    }

    public void setDonationList(List<Donation> donationList) {
        this.donationList = donationList;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
