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
public abstract class Donation {
 
    private int id;
    private Project project;
    
    public Donation(Project obj){
        
        project = obj;
        
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    
    public int getId(){
        return id;
    }
    
    public void setProject(Project obj){
        project = obj;
    }
    
    public Project getProject(){
        return project;
    }
    
    public abstract String getType();
    public abstract int getItems();
    public abstract int getAmount();
    
    
}
