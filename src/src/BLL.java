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
public class BLL {

    private static BLL instance = null;
    private final DmsDatabase database;

    private final Organization organization;

    private BLL() {
        database = DmsDatabase.getDatabaseInstance();
        organization = Organization.getOrganizationInstance("Donation Management System");
        //
        try{
            database.addOrganization(organization);
            organization.setProjectsList(database.getProjectsList());
            organization.setDonorsList(database.getDonorsList());
        }
        catch(Exception e)
        {
            System.out.println("Error Loading Projects and Donors Data:\t" + e);
        }
        try{
            for (int i = 0; i < organization.getProjectsList().size(); i++) {
                organization.getProjectsList().get(i).setTeamList(getTeamList(organization.getProjectsList().get(i)));
                organization.getProjectsList().get(i).setBeneficiariesList(getBeneficiaryList(organization.getProjectsList().get(i)));
                organization.getProjectsList().get(i).setDonationList(getProjectDonationsList(organization.getProjectsList().get(i)));
                organization.getProjectsList().get(i).setTeamLead(getTeamLead(organization.getProjectsList().get(i)));
                
                System.out.println("\n\nTeamList + BeneficiaryList + DonationsList + TeamLead Retrieved from databse successfully into Projects class' objects!\n");   

                //set Needy details for each beneficiary in the beneficiariesList
                for (int j = 0; j < organization.getProjectsList().get(i).getBeneficiariesList().size(); j++) {
                    organization.getProjectsList().get(i).getBeneficiariesList().get(j)
                            .setRecievedFunds(getDisburseFundList(organization.getProjectsList().get(i).getBeneficiariesList().get(j)));

                    organization.getProjectsList().get(i).getBeneficiariesList().get(j)
                            .setAssessor(database.getAssessorById(organization.getProjectsList().get(i).getBeneficiariesList().get(j)));
                }
                System.out.println("\n\nNeedy Inner Data Retrieved from databse successfully into each Needy class' objects!\n");   
            }
        }
        catch(Exception e)
        {
            System.out.println("Error Loading Projects Inner Data:\t" + e);
        }
        try{

            for (int i = 0; i < organization.getDonorsList().size(); i++) {
                organization.getDonorsList().get(i).setDonationsList(getDonationsListByIdList(organization.getDonorsList().get(i)));
                organization.getDonorsList().get(i).setPledgesList(getPledgeList(organization.getDonorsList().get(i)));
            }
            System.out.println("\n\nDonors Inner Data Retrieved from databse successfully!\n");           
        }

        catch(Exception e)
        {
            System.out.println("Error Loading Donors Inner Data:\t" + e);
        }
        
        System.out.println("\n\nData Retrieved from databse successfully!\n");

    }

    public static BLL getBllInstance() {
        if (instance == null) {
            instance = new BLL();
        }
        return instance;
    }
    
    public void refreshData(){
        database.addOrganization(organization);
        organization.setProjectsList(database.getProjectsList());
        organization.setDonorsList(database.getDonorsList());

        for (int i = 0; i < organization.getProjectsList().size(); i++) {
            organization.getProjectsList().get(i).setTeamList(getTeamList(organization.getProjectsList().get(i)));
            organization.getProjectsList().get(i).setBeneficiariesList(getBeneficiaryList(organization.getProjectsList().get(i)));
            organization.getProjectsList().get(i).setDonationList(getProjectDonationsList(organization.getProjectsList().get(i)));
            organization.getProjectsList().get(i).setTeamLead(getTeamLead(organization.getProjectsList().get(i)));

            //set Needy details for each beneficiary in the beneficiariesList
            for (int j = 0; j < organization.getProjectsList().get(i).getBeneficiariesList().size(); j++) {
                organization.getProjectsList().get(i).getBeneficiariesList().get(j)
                        .setRecievedFunds(getDisburseFundList(organization.getProjectsList().get(i).getBeneficiariesList().get(j)));

                organization.getProjectsList().get(i).getBeneficiariesList().get(j)
                        .setAssessor(database.getAssessorById(organization.getProjectsList().get(i).getBeneficiariesList().get(j)));

            }
        }

        for (int i = 0; i < organization.getDonorsList().size(); i++) {
            organization.getDonorsList().get(i).setDonationsList(getDonationsListByIdList(organization.getDonorsList().get(i)));
            organization.getDonorsList().get(i).setPledgesList(getPledgeList(organization.getDonorsList().get(i)));
        }

        System.out.println("\n\nData REFRESHED from databse successfully!\n");
    }

    public void addDonor(Donor donor) {
        organization.addDonor(donor);

//        database.addOrganizationDonor(donor, organization);        //Todo not yet implemented
        database.insertDonor(donor);
    }

    private void addDonorPledge(Donor donor) {
        database.addDonorPledgeList(donor);
    }

    public void addPledge(Pledge pledge, Donor donor) {
        List<Donor> donors = organization.getDonorsList();

        for (int i = 0; i < donors.size(); i++) {
            if (donors.get(i).getId() == donor.getId()) {
                donors.get(i).addPledge(pledge);
                database.insertPledge(pledge);
                addDonorPledge(donors.get(i));
            }
        }
    }

    public Project findProject(String projName) {
        Project project = null;
        for (int i = 0; i < organization.getProjectsList().size(); i++) {
            if (organization.getProjectsList().get(i).getName().equalsIgnoreCase(projName)) {
                project = organization.getProjectsList().get(i);
                break;
            }
        }
        return project;
    }
    
    public Needy findApplicant(Project project, String name){
        Needy needy = null;
        for (int i = 0; i < project.getBeneficiariesList().size(); i++) {
            if(project.getBeneficiariesList().get(i).getName().equalsIgnoreCase(name)){
                needy =  project.getBeneficiariesList().get(i);
                break;
            }
        }
        return needy;
    }

    public void DeleteAllData() {
        database.deleteAllData();
    }

    public boolean addProject(Project project) //Functionality No 1.
    {
    
        for(int i=0;i<organization.getProjectsList().size();i++){
            if(organization.getProjectsList().get(i).getName().equals(project.getName()))
                return false;
            
        }
        
        organization.addProject(project);
        database.insertProject(project);
        return true;
        
    }

    public void updateProjectTeamList(Project project) {
        List<Project> projects = organization.getProjectsList();
        for (int i = 0; i < projects.size(); i++) {
            if (project.getId() == projects.get(i).getId()) {
                database.addProjectTeamList(project);
                break;
            }
        }
    }

    public void setTeamLead(Project project, Volunteer lead) {

        project.setTeamLead(lead);
        addVolunteer(lead, project);
        database.updateProjectTeamLead(project);
    }

    public void addVolunteer(Volunteer volunteer, Project project) {

        List<Project> projects = organization.getProjectsList();
        for (int i = 0; i < projects.size(); i++) {
            if (project.getId() == projects.get(i).getId()) {
                database.insertVolunteer(volunteer, project);
                projects.get(i).addVolunteer(volunteer);
    
                break;
            }
        }
    }

    public int removeProjectTeamList(Volunteer volunteer, Project project) {
        List<Project> projects = organization.getProjectsList();
        for (int i = 0; i < projects.size(); i++) {
            if (project.getId() == projects.get(i).getId()) {
                
                if(project.getTeamLead().getId() == volunteer.getId())
                {
                    return -1;
                }
                else{
                    projects.get(i).removeTeamMember(volunteer);
                    database.removeProjectTeamList(volunteer, projects.get(i));                    
                }

                break;
            }
        }
       return 0;
    }

    public void addDonation(Donation donation, Donor donor) {
        List<Donor> donors = organization.getDonorsList();
        for (int i = 0; i < donors.size(); i++) {
            if (donors.get(i).getId() == donor.getId()) {
                donors.get(i).addDonation(donation);
                database.insertDonation(donation);
                database.addDonorDonationList(donor, donation);
                database.addProjectDonationList(donation);
                database.updateProjectBalance(donation.getProject());
            
            }
        }
    }

    public void addAssessor(Assessor assessor) {
        database.insertAssesor(assessor);        //Todo link to organization
    }

    public void addNeedy(Project project, Needy needy) {
        database.insertNeedy(project, needy);            //Todo link to organization
    }

    public void replaceVolunteer(Volunteer volunteer, Volunteer volunteer2, Project project) {

        List<Project> projects = organization.getProjectsList();
        for (int i = 0; i < projects.size(); i++) {
            if (project.getId() == projects.get(i).getId()) {
                if (project.replaceVolunteer(volunteer, volunteer2)) {
                    projects.get(i).replaceVolunteer(volunteer, volunteer2);

                    database.replaceVolunteerProjectList(projects.get(i), volunteer, volunteer2);
                }
                break;
            }
        }

    }

    public void setAssessorNeedy(Assessor assessor, Needy needy) {
        assessor.setBeneficiary(needy);
        needy.setAssessor(assessor);
    }

    private List<Volunteer> getTeamList(Project project) {
        List<Volunteer> volunteerList = new ArrayList<>();

        List<Integer> idList = database.getTeamIdList(project);
        for (int i = 0; i < idList.size(); i++) {

            volunteerList.add(database.getVolunteerById(idList.get(i)));
        }

        return volunteerList;
    }

    private List<Needy> getBeneficiaryList(Project project) {
        List<Needy> beneficiaryList = new ArrayList<>();

        List<Integer> idList = database.getBeneficiaryIdList(project);
        for (int i = 0; i < idList.size(); i++) {
            beneficiaryList.add(database.getBeneficiaryById(idList.get(i)));
            System.out.println("");
        }

        return beneficiaryList;
    }

    private List<DisburseFund> getDisburseFundList(Needy needy) {
        List<DisburseFund> receivedFundsList = new ArrayList<>();

        List<Integer> idList = database.getReceivedFundsIdList(needy);
        for (int i = 0; i < idList.size(); i++) {

            receivedFundsList.add(database.getDisburseFundById(idList.get(i)));
        }

        return receivedFundsList;
    }

    //              database.getAssessorById(Needy needy)
    private List<Donation> getProjectDonationsList(Project project) {
        return database.getProjectDonationsList(project);
    }

    private Volunteer getTeamLead(Project project) {
        return database.getVolunteerById(database.getLeadId(project));
    }

    private List<Donation> getDonationsListByIdList(Donor donor) {
        List<Donation> donationsList = new ArrayList<>();

        List<Integer> donationsIdList = database.getDonorDonationsIdList(donor);

        for (int i = 0; i < donationsIdList.size(); i++) {
            for (int j = 0; j < organization.getProjectsList().size(); j++) {
                for (int k = 0; k < organization.getProjectsList().get(j).getDonationList().size(); k++) {
                    if (donationsIdList.get(i) == organization.getProjectsList().get(j).getDonationList().get(k).getId()) {
                        donationsList.add(organization.getProjectsList().get(j).getDonationList().get(k));
                    }
                }
            }
        }

        return donationsList;
    }

    private List<Pledge> getPledgeList(Donor donor) {
        List<Pledge> pledgeList = new ArrayList<>();

        List<Integer> idList = database.getDonorPledgeIdList(donor);
        for (int i = 0; i < idList.size(); i++) {
            pledgeList.add(database.getPledgeById(idList.get(i)));
        }

        return pledgeList;
    }

    public Donor findDonor(String donorName) {
        Donor donor = null;
        for (int i = 0; i < organization.getDonorsList().size(); i++) {
            if (organization.getDonorsList().get(i).getName().equalsIgnoreCase(donorName)) {
                donor = organization.getDonorsList().get(i);
                break;
            }
        }
        return donor;
    }

    public Project  searchProject(String project_Name) {
        Project project = database.getProjectByName(project_Name);
       
        return project;
    }
    
    public Project  searchProjectByTeamLead(String teamLead) {
        Project project = database.getProjectByTeamLead(teamLead);
       
        return project;
    }

    public Donor searchDonors(String donorName) {
   
        Donor donor = null;
        List<Donor> donorsList = organization.getDonorsList();
        for(int i=0;i<donorsList.size();i++){
            if(donorsList.get(i).getName().equals(donorName)){
                return organization.getDonorsList().get(i);
            }
        }
        return donor;
    }
    


    public Needy searchNeedy(String benificiaryName) {
         Needy needy = null;

       for(int i =0;i<organization.getProjectsList().size();i++){
           
           for(int j =0;j<organization.getProjectsList().get(i).getBeneficiariesList().size();j++){
               if(organization.getProjectsList().get(i).getBeneficiariesList().get(j).getName().equalsIgnoreCase(benificiaryName))
                   return organization.getProjectsList().get(i).getBeneficiariesList().get(j);                   
           }
           
       }

         return needy;
    }

    public boolean disburseFund(Project project, Needy needy, Gift disbFund){
        
        if(project.getAvailableFund() > 0 && project.getAvailableFund()>disbFund.getAmount()){
            if(needy.getIncome() < 20000){
                project.setAvailableFund(project.getAvailableFund() - disbFund.getAmount());
                needy.addFund(disbFund);
                database.addDisburseFund(needy, disbFund);
                return true;
            }
            return false;
        }
        return false;        
    }
    
}
