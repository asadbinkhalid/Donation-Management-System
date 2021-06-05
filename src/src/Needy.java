package src;

import java.util.List;

/**
 *
 * @author Fahad
 */
public class Needy extends Person {

    private String address;
    private String dob;
    private String phoneNo;
    private int income;
    
    private int amountNeeded;
    private List<DisburseFund> recievedFunds;   //aggregation
    private Assessor assessor; //association 2 way

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setRecievedFunds(List<DisburseFund> recievedFunds) {
        this.recievedFunds = recievedFunds;
    }
    
    public Needy(String name) {
        super(name);
        address = null;
        dob = null;
        phoneNo = null;
        income = 0;
        recievedFunds = null;
        assessor = null;
    }
    
    public void setAmountNeeded(int amount){
        amountNeeded = amount;
    }

    public List<DisburseFund> getRecievedFunds() {
        return recievedFunds;
    }
    
    public void addFund(DisburseFund obj){
        recievedFunds.add(obj);
    }
    
    public void setAssessor(Assessor obj){
        assessor = obj;
    }
    
    public int getAmountNeeded(){
        return amountNeeded;
    }
    
    public Assessor getAssessor(){
        return assessor;
    }
    
    public String getAddress(){
        return address;
    }
    
    public String getDob(){
        return dob;
    }
    
    public String getPhoneNo(){
        return phoneNo;
    }
    
    public int getIncome(){
        return income;
    }
    
    public void setInfo(String address, String dob, String phoneNo, int income){
        this.address = address;
        this.dob = dob;
        this.income = income;
        this.phoneNo = phoneNo;
    }
    
    @Override
       public String getType()
    {
        return "needy";
    }
    
}
