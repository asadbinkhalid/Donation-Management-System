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
public class Organization {
    
    private static Organization instance = null;
    
    private String name;
    private List<Donor> donorsList;
    private List<Project> projectsList;
    
    
    
    private Organization(String name){
        this.name = name;
        donorsList = new ArrayList<>();
        projectsList = new ArrayList<>();
    }
    
    public static Organization getOrganizationInstance(String name)
    {
        if(instance == null)
            instance = new Organization(name);
        
        return instance;
    }
    
    public String getName(){
        return name;
    }
    
    public void addDonor(Donor obj){
        donorsList.add(obj);
    }
    
    public void addProject(Project obj){
        projectsList.add(obj);
    }
    
    public List<Donor> getDonorsList(){
        return donorsList;
    }
    
    public List<Project> getProjectsList(){
        return projectsList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDonorsList(List<Donor> donorsList) {
        this.donorsList = donorsList;
    }

    public void setProjectsList(List<Project> projectsList) {
        this.projectsList = projectsList;
    }
    
    
    
}
