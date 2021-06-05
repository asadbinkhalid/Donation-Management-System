/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fahad
 */
public class DmsDatabase {

    private static DmsDatabase instance = null;
    private Connection con;
    private Statement stmt;

    private DmsDatabase() {
        try {
            String s = "jdbc:sqlserver://localhost:1433;databaseName=DMS";
            con = DriverManager.getConnection(s, "sa", "12345678");
            stmt = con.createStatement();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static DmsDatabase getDatabaseInstance() {
        if (instance == null) {
            instance = new DmsDatabase();
        }

        return instance;
    }

    void displayAllDonors() {
        try {
            String s = "select * from donor";
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2));
            }

        } catch (SQLException e) {
            System.out.println("\nSql Exception: " + e);
        }
    }

    void insertDonor(Donor donor) {

        try {
            //id auto increment in table
            int rs = stmt.executeUpdate("Insert into Donor values ('" + donor.getName() + "')");

            System.out.println("\nInserted into Donor: rows affected + " + rs);

        } catch (SQLException e) {
            System.out.println("\nSql Exception Insert Donors: " + e);
        }

        //Getting inserted ids of object
        try {

            ResultSet resultSet = stmt.executeQuery("Select Max(donorId) DonorId from Donor");

            while (resultSet.next()) {

                donor.setId(resultSet.getInt(1));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    void addDonorPledgeList(Donor donor) {

        int size = donor.getPledgesList().size();
        if (size > 0) {
            try {
                int rs = stmt.executeUpdate("Insert into Donor_List values (" + donor.getId()
                        + "," + donor.getPledgesList().get(size - 1).getId() + ",NULL)");    //Inserting latest element of pledge list
            } catch (SQLException e) {
                System.out.println("\nSql Exception addDonorPledgeList: " + e);
            }
        }
    }

    void addDonorDonationList(Donor donor, Donation donation) {

        int size = donor.getDonationsList().size();
        if (size > 0) {
            try {
                int rs = stmt.executeUpdate("Insert into Donor_List values (" + donor.getId()
                        + ",NULL," + donation.getId() + ")");
                //Inserting latest element of donation list
                System.out.println("Inserted into donor donation list: rows affected +  " + rs);
            } catch (SQLException e) {
                System.out.println("\nSql Exception addDonorDonationList: " + e);
            }

        }
    }

    void insertDonation(Donation donation) {

        try {

            int rs = 0;

            if (donation.getType().equalsIgnoreCase("Fund")) {

                rs = stmt.executeUpdate("Insert into Donation values (" + donation.getProject().getId() + ",'" + donation.getType() + "',"
                        + donation.getAmount() + ")");
            } else if (donation.getType().equalsIgnoreCase("Goods")) {
                rs = stmt.executeUpdate("Insert into Donation values (" + donation.getProject().getId() + ",'" + donation.getType() + "',"
                        + donation.getItems() + ")");
            }

            //id auto increment in table
            System.out.println("\nInserted into Donation: rows affected + " + rs);

        } catch (SQLException e) {
            System.out.println("\nSql Exception Insert Donation: " + e);
        }

        //Getting inserted ids of object
        try {

            ResultSet resultSet = stmt.executeQuery("Select Max(donationId) donationId from Donation");

            while (resultSet.next()) {

                donation.setId(resultSet.getInt(1));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    void insertVolunteer(Volunteer volunteer, Project project) {

        try {
            //id auto increment in table
            int rs1 = stmt.executeUpdate("Insert into Volunteer values ('" + volunteer.getName() + "')");

            System.out.println("\nInserted into Volunteer: rows affected + " + rs1);

        } catch (SQLException e) {
            System.out.println("\nSql Exception Insert Volunteer: " + e);
        }

        //Getting inserted ids of object
        try {

            ResultSet resultSet = stmt.executeQuery("Select Max(volunteerId) from Volunteer");

            while (resultSet.next()) {
                volunteer.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        try {
            int rs2 = stmt.executeUpdate("Insert into dms.dbo.project_list values (" + project.getId() + ","
                    + volunteer.getId() + ", NULL,NULL)");
            System.out.println("\nInserted into Project_List: rows affected + " + rs2);
        } catch (SQLException ex) {
            System.out.println("\nSql Exception Insert into Project_List: " + ex);
        }

    }

    void insertNeedy(Project project, Needy needy) {
        try {
            //id auto increment in table
            int rs = stmt.executeUpdate("Insert into Needy values ('"
                    + needy.getName() + "'," + needy.getAmountNeeded() + ",'" + needy.getAddress() + "','"
                    + needy.getPhoneNo() + "','" + needy.getDob() + "'," + needy.getIncome() + ")");

            System.out.println("\nInserted into Needy: rows affected + " + rs);
        } catch (SQLException e) {
            System.out.println("\nSql Exception Insert Needy: " + e);
        }
        //Getting inserted ids of object
        try {

            ResultSet resultSet = stmt.executeQuery("Select Max(needyId) from Needy");

            while (resultSet.next()) {

                needy.setId(resultSet.getInt(1));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        try {
            int rs2 = stmt.executeUpdate("Insert into dms.dbo.project_list values (" + project.getId() + ",NULL,"
                    + needy.getId() + ",NULL)");
            System.out.println("\nInserted needyID into Project_List: rows affected + " + rs2);
        } catch (SQLException ex) {
            System.out.println("\nSql Exception Insert NEEDYID into Project_List: " + ex);
        }

    }

    void addNeedyDisburseFundList(Needy needy) {

        int size = needy.getRecievedFunds().size();
        if (size > 0) {
            try {
                int rs = stmt.executeUpdate("Insert into Needy_List values (" + needy.getId()
                        + "," + needy.getRecievedFunds().get(size - 1) + ")");    //Inserting latest element of disburse funds list
            } catch (SQLException e) {
                System.out.println("\nSql Exception addNeedyDisburseFundList: " + e);
            }
        }
    }

    void insertAssesor(Assessor assessor) {
        try {
            //id auto increment in table
            int rs = stmt.executeUpdate("Insert into Assessor values (" + assessor.getId() + "," + assessor.getBeneficiary().getId() + ")");

            System.out.println("\nInserted into Assessor: rows affected + " + rs);
        } catch (SQLException e) {
            System.out.println("\nSql Exception Insert Assesor: " + e);
        }

    }

    void insertPledge(Pledge pledge) {
        try {
            //pledge id auto set in database
            int rs = stmt.executeUpdate("Insert into Pledge values (" + pledge.getAmount() + ",'" + pledge.getDate() + "')");

            System.out.println("\nInserted into Pledge: rows affected + " + rs);
        } catch (SQLException e) {
            System.out.println("\nSql Exception Insert Pledge: " + e);
        }

        //Getting inserted ids of object
        try {

            ResultSet resultSet = stmt.executeQuery("Select Max(pledgeId) from Pledge");

            while (resultSet.next()) {

                pledge.setId(resultSet.getInt(1));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    void insertProject(Project project) {
        try {

            //added leadId in table of project with foreign key of volunteer id
            //pledge id auto set in database
            //BY ASAD: added name column in table of project
            int rs = stmt.executeUpdate("Insert into Project values ('" + project.getDescription()
                    + "'," + project.getAvailableFund() + ", NULL, '" + project.getName() + "' )");

            System.out.println("\nInserted into Project: rows affected + " + rs);
        } catch (SQLException e) {
            System.out.println("\nSql Exception Insert Project: " + e);
        }

        //Getting inserted ids of object
        try {

            ResultSet resultSet = stmt.executeQuery("Select Max(projectId) from Project");

            while (resultSet.next()) {

                project.setId(resultSet.getInt(1));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    void updateProjectTeamLead(Project project) {

        try {

            //added leadId in table of project with foreign key of volunteer id
            //project id auto set in database
            int rs = stmt.executeUpdate("UPDATE Project Set leadId = " + project.getTeamLead().getId()
                    + " Where projectId = " + project.getId());

            System.out.println("\nInserted TeamLead into Project: rows affected + " + rs);
        } catch (SQLException e) {
            System.out.println("\nSql Exception Update Project Team Lead: " + e);
        }

    }

    void addProjectTeamList(Project project) {

        int size = project.getTeamList().size();
        if (size > 0) {
            try {
                int rs = stmt.executeUpdate("Insert into Project_List values (" + project.getId()
                        + "," + project.getTeamList().get(size - 1).getId() + ",NULL,NULL)");    //Inserting latest element of team list
            } catch (SQLException e) {
                System.out.println("\nSql Exception addProjectTeamList: " + e);
            }
        }
    }

    void addProjectBenificiariesList(Project project) {

        int size = project.getBeneficiariesList().size();
        if (size > 0) //adding latest benificiary
        {
            try {
                int rs = stmt.executeUpdate("Insert into Project_List values (" + project.getId()
                        + ",NULL," + project.getBeneficiariesList().get(size - 1).getId() + ",NULL)");    //Inserting latest element of benificiaries list
            } catch (SQLException e) {
                System.out.println("\nSql Exception addProjectBenificiariesList: " + e);
            }
        }
    }

    void deleteAllData() {
        try {
            //Deleting data from tables
            int rs = stmt.executeUpdate("Delete from Assessor");
            rs += stmt.executeUpdate("Delete from Needy_List");
            rs += stmt.executeUpdate("Delete from Disburse_Fund");
            rs += stmt.executeUpdate("Delete from Needy");
            rs += stmt.executeUpdate("Delete from Project_List");
            rs += stmt.executeUpdate("Delete from Organization");
            rs += stmt.executeUpdate("Delete from Project");
            rs += stmt.executeUpdate("Delete from Volunteer");
            rs += stmt.executeUpdate("Delete from Donor_List");
            rs += stmt.executeUpdate("Delete from Pledge");
            rs += stmt.executeUpdate("Delete from Donor");
            rs += stmt.executeUpdate("Delete from Donation");

            //Reseting auto increment ids to zero
            rs += stmt.executeUpdate("DBCC CHECKIDENT (Donor,RESEED, 0)");
            rs += stmt.executeUpdate("DBCC CHECKIDENT (Needy,RESEED, 0)");
            rs += stmt.executeUpdate("DBCC CHECKIDENT (Project,RESEED, 0)");
            rs += stmt.executeUpdate("DBCC CHECKIDENT (Volunteer,RESEED, 0)");
            rs += stmt.executeUpdate("DBCC CHECKIDENT (Pledge,RESEED, 0)");
            rs += stmt.executeUpdate("DBCC CHECKIDENT (Donation,RESEED, 0)");
            rs += stmt.executeUpdate("DBCC CHECKIDENT (Disburse_Fund,RESEED, 0)");

            System.out.println("\nDeleted all the database data:\nRows Affected:\t" + rs);
        } catch (SQLException e) {
            System.out.println("\nSql Exception DELETE ALL DATA: " + e);
        }
    }

    public List<Donor> getDonorsList() {

        List<Donor> donors = new ArrayList<>();

        try {
            String s = "select * from donor";
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                Donor donor = new Donor(rs.getString(2));
                donor.setId(rs.getInt(1));
                donors.add(donor);
            }
            //System.out.println("SQLRESPONSE: donor data retrieved");
        } catch (SQLException e) {
            System.out.println("\nSql Exception: " + e);
        }

        return donors;
    }

    void removeProjectTeamList(Volunteer volunteer, Project project) {
        try {
            int rs = stmt.executeUpdate("Delete from Project_List Where volunteerId = " + volunteer.getId() + " AND projectId = " + project.getId());
            System.out.println("\nRemoved from Project Team List: rows affected + " + rs);
        } catch (SQLException e) {
            System.out.println("\nSql Exception removeProjectTeamList1: " + e);
        }
        //delete voluneteer from volunteer table
        try {
            int rs = stmt.executeUpdate("Delete from volunteer Where volunteerId = " + volunteer.getId());
            System.out.println("\nRemoved from Volunteer: rows affected + " + rs);
        } catch (SQLException e) {
            System.out.println("\nSql Exception removeProjectTeamList2: " + e);
        }
    }

    void replaceVolunteerProjectList(Project project, Volunteer volunteer, Volunteer volunteer2) {
        try {
            //id auto increment in table
            int rs1 = stmt.executeUpdate("Update Volunteer set name = '" + volunteer2.getName() + "' WHERE volunteerId ="
                    + volunteer.getId());

            System.out.println("\nUpdated volunter in Volunteer: rows affected + " + rs1);

        } catch (SQLException e) {
            System.out.println("\nSql Exception Insert Volunteer: " + e);
        }

        //Getting inserted ids of object
        try {

            ResultSet resultSet = stmt.executeQuery("Select Max(volunteerId) from Volunteer");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                volunteer.setId(resultSet.getInt(1));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        try {
            int rs = stmt.executeUpdate("Update Project_List set volunteerId = " + volunteer2.getId() + " Where projectId = " + project.getId()
                    + " And volunteerId =  " + volunteer.getId());
            System.out.println("\nUpdated Volunteer from Project Team List: rows affected + " + rs);
        } catch (SQLException e) {
            System.out.println("\nSql Exception removeProjectTeamList: " + e);
        }
    }

    void addOrganization(Organization organization) {
        //Checking if organization already presents
        try {
            ResultSet r = stmt.executeQuery("Select COUNT(*) from Organization Where name = 'Donation Management System'");
            int count = 0;
            while (r.next()) {
                count = r.getInt(1);
            }

            if (count == 0) {
                int rs = stmt.executeUpdate("Insert Into Organization Values ('" + organization.getName() + "',NULL,NULL)");

            }
        } catch (SQLException e) {
            System.out.println("\nSQL Exception add organization: " + e);
        }
    }

    void addOrganizationProject(Project project, Organization organization) {
        try {
            int rs = stmt.executeUpdate("Insert into Organization values ('" + organization.getName()
                    + "',NULL," + project.getId() + ")");    //Inserting latest element of team list
        } catch (SQLException e) {
            System.out.println("\nSql Exception addProjectTeamList: " + e);
        }
    }

    void addOrganizationDonor(Donor donor, Organization organization) {
        int size = organization.getDonorsList().size();
        if (size > 0) {
            try {
                int rs = stmt.executeUpdate("Insert into Organization values ('" + organization.getName()
                        + "," + donor.getId() + ",NULL)");    //Inserting latest element of team list
            } catch (SQLException e) {
                System.out.println("\nSql Exception addOrganizationDonorList: " + e);
            }
        }

    }

    public List<Integer> getTeamIdList(Project project) {
        List<Integer> volunteerIdList = new ArrayList<>();

        try {
            ResultSet rs = stmt.executeQuery("select volunteerId from dms.dbo.Project_List where projectId = "
                    + project.getId() + " AND needyId is null");
            while (rs.next()) {
                volunteerIdList.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("\nSql Exception getTeamList: " + e);
        }
        return volunteerIdList;
    }

    
    
    public Volunteer getVolunteerById(int id) {
        Volunteer volunteer = null;
        try {
            ResultSet rs2 = stmt.executeQuery("select * from dms.dbo.Volunteer where volunteerid = " + id);
            while (rs2.next()) {
                if (volunteer == null) {
                    volunteer = new Volunteer(rs2.getString(2));
                }
                volunteer.setId(rs2.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("\nSql Exception getVolunteerById: " + e);
        }
        return volunteer;
    }

    public List<Integer> getBeneficiaryIdList(Project project) {
        List<Integer> beneficiaryIdList = new ArrayList<>();

        try {
            ResultSet rs = stmt.executeQuery("select needyId from dms.dbo.Project_List where projectId = "
                    + project.getId() + " AND volunteerId is null AND donationId is null");
            while (rs.next()) {
                beneficiaryIdList.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("\nSql Exception getBeneficiaryList: " + e);
        }
        return beneficiaryIdList;
    }

    public Needy getBeneficiaryById(int id) {
        Needy needy = null;
        try {
            ResultSet rs = stmt.executeQuery("select * from dms.dbo.Needy where needyId = " + id);
            while (rs.next()) {
                if (needy == null) {
                    needy = new Needy(rs.getString(2));
                    needy.setId(rs.getInt(1));
                    needy.setAmountNeeded(rs.getInt(3));
                    needy.setInfo(rs.getString(4), rs.getString(5), rs.getString(6), Integer.parseInt(rs.getString(7)));
                }
            }
            return needy;
        } catch (SQLException e) {
            System.out.println("\nSql Exception getVolunteerById: " + e);
        }
        return needy;
    }

    public List<Integer> getReceivedFundsIdList(Needy needy) {
        List<Integer> receivedFundsIdList = new ArrayList<>();

        try {
            ResultSet rs = stmt.executeQuery("select disbFundId from dms.dbo.Needy_List where needyId = "
                    + needy.getId());
            while (rs.next()) {
                receivedFundsIdList.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("\nSql Exception getReceivedFundsIdList: " + e);
        }
        return receivedFundsIdList;
    }

//    public DisburseFund getDisburseFundById(int id) {
//        DisburseFund disburseFund = null;
//        try {
//            ResultSet rs = stmt.executeQuery("select * from dms.dbo.Disburse_Fund where disbFundId = " + id);
//            while (rs.next()) {
//                if (disburseFund == null) {
//                    disburseFund = new DisburseFund(rs.getInt(1), rs.getInt(2));
//                }
//            }
//            return disburseFund;
//        } catch (SQLException e) {
//            System.out.println("\nSql Exception getDisburseFundById: " + e);
//        }
//        return disburseFund;
//    }
    public DisburseFund getDisburseFundById(int id) {
        DisburseFund disburseFund = null;
        try {
            ResultSet rs = stmt.executeQuery("select * from dms.dbo.Disburse_Fund where disbFundId = " + id);
            while (rs.next()) {
                if (disburseFund == null) {
                    if (rs.getString(3).equalsIgnoreCase("loan")) {
                        disburseFund = new Loan(rs.getInt(1), rs.getInt(2), rs.getString(4));
                    } else if (rs.getString(3).equalsIgnoreCase("gift")) {
                        disburseFund = new Gift(rs.getInt(2));
                        disburseFund.setId(rs.getInt(1));
                    }
                }
            }
            return disburseFund;
        } catch (SQLException e) {
            System.out.println("\nSql Exception getDisburseFundById: " + e);
        }
        return disburseFund;
    }

    public List<Donation> getProjectDonationsList(Project project) {
        List<Donation> projectDonationsList = new ArrayList<>();

        try {
            ResultSet rs = stmt.executeQuery("select * from dms.dbo.Donation where projectId = " + project.getId());
            while (rs.next()) {
                Donation donation = null;
                if (rs.getString(3).equalsIgnoreCase("Fund")) {
                    donation = new Fund(project, rs.getInt(4));
                    donation.setId(rs.getInt(1));

                    projectDonationsList.add(donation);
                } else if (rs.getString(3).equalsIgnoreCase("Goods")) {
                    donation = new Goods(project, rs.getInt(4));
                    donation.setId(rs.getInt(1));

                    projectDonationsList.add(donation);
                }
            }
        } catch (SQLException e) {
            System.out.println("\nSql Exception getProjectDonationsList: " + e);
        }
        return projectDonationsList;
    }

    public List<Integer> getDonorDonationsIdList(Donor donor) {
        List<Integer> donorDonationsIdList = new ArrayList<>();

        try {
            ResultSet rs = stmt.executeQuery("select donationId from dms.dbo.Donor_List where donorId = "
                    + donor.getId() + " AND pledgeid is null");
            while (rs.next()) {
                donorDonationsIdList.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("\nSql Exception getDonorDonationsIdList: " + e);
        }
        return donorDonationsIdList;
    }
    
    

    public int getAssessorId(Needy needy) {
        int assessorId = 0;

        try {
            ResultSet rs = stmt.executeQuery("select assessorId from dms.dbo.Assessor where needyId = "
                    + needy.getId());
            while (rs.next()) {
                if (assessorId == 0) {
                    assessorId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("\nSql Exception getAssessorId: " + e);
        }
        return assessorId;
    }

    public Assessor getAssessorById(Needy needy) {
        int id = getAssessorId(needy);

        Assessor assessor = null;
        try {
            ResultSet rs = stmt.executeQuery("select name from dms.dbo.Volunteer where volunteerId = " + id);
            while (rs.next()) {
                if (assessor == null) {
                    assessor = new Assessor(rs.getString(1));
                    assessor.setBeneficiary(needy);
                }
            }
            return assessor;
        } catch (SQLException e) {
            System.out.println("\nSql Exception getAssessorById: " + e);
        }
        return assessor;
    }

    public int getLeadId(Project project) {
        int leadId = 0;

        try {
            ResultSet rs = stmt.executeQuery("SELECT leadId FROM DMS.dbo.Project where projectid = " + project.getId());
            while (rs.next()) {
                if (leadId == 0) {
                    leadId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("\nSql Exception getAssessorId: " + e);
        }
        return leadId;
    }

    public List<Integer> getDonorPledgeIdList(Donor donor) {
        List<Integer> donorPledgeIdList = new ArrayList<>();

        try {
            ResultSet rs = stmt.executeQuery("select pledgeId from dms.dbo.Donor_List where donorId = "
                    + donor.getId() + " AND donationId is null");
            while (rs.next()) {
                donorPledgeIdList.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("\nSql Exception getDonorPledgeIdList: " + e);
        }
        return donorPledgeIdList;
    }

    public Pledge getPledgeById(int id) {
        Pledge pledge = null;
        try {
            ResultSet rs = stmt.executeQuery("select * from dms.dbo.Pledge where pledgeId = " + id);
            while (rs.next()) {
                if (pledge == null) {
                    pledge = new Pledge(rs.getInt(2), rs.getString(2));
                    pledge.setId(rs.getInt(1));
                }
            }
            return pledge;
        } catch (SQLException e) {
            System.out.println("\nSql Exception getPledgeById: " + e);
        }
        return pledge;
    }

    public List<Project> getProjectsList() {

        List<Project> projectsList = new ArrayList<>();

        try {
            String s = "select * from project";
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                Project project = new Project(rs.getString(5), rs.getString(2));
                project.setId(rs.getInt(1));
                projectsList.add(project);
            }
        } catch (SQLException e) {
            System.out.println("\nSql Exception in getProjectsList: " + e);
        }

        return projectsList;
    }

    Project getProjectByName(String project_Name) {
            
        Project project = null;
        try{
                
            String s = "Select top 1 * from Project Where projName = '" + project_Name+"'";
            ResultSet rs = stmt.executeQuery(s);
            int id = -1;
            while (rs.next()) {
                if(project == null)
                    project = new Project(rs.getString(5), rs.getString(2));
    
                project.setId(rs.getInt(1));

                project.setAvailableFund(rs.getInt(3));
                id = rs.getInt(4);

            }

            if (project != null && id!= -1){
                Volunteer v = getVolunteerById(id);

                project.setTeamLead(v);

                List<Volunteer> volunteers = getVolunteersList(project.getId());

                if(volunteers.size()>0){
                    project.setTeamList(volunteers);
                }

                List<Needy> needyList = getNeedyList(project.getId());
                if(needyList.size()>0){
                    project.setBeneficiariesList(needyList);
                }
            }
               
        }
         catch(SQLException e){
             System.out.println("Sql Exception Get Project By Name :\t" +  e);
                
        }  
            return project;
    }
    
    
    public List<Volunteer> getVolunteersList(int projectId){
        
        List<Volunteer> volunteersList = new ArrayList<>();
        
        List<Integer> volunteerIds = new ArrayList<>();
        
        
        try{
            ResultSet rs;
            rs = stmt.executeQuery("Select * from Project_List where projectId = " + projectId);
            
            while(rs.next()){
               Integer volunteerId = rs.getInt(2);
               volunteerIds.add(volunteerId);
            }
            
            
        }catch(SQLException e){
            
            System.out.println("Sql Exception getVolunteer List 1:\t"+e);
        }
        
        
       try {
       
           for(int i = 0;i<volunteerIds.size();i++){
            Volunteer volunteer = null;

            ResultSet rs2 = stmt.executeQuery("select * from dms.dbo.Volunteer where volunteerid = " + volunteerIds.get(i));
             while(rs2.next()){
                if(volunteer == null)
                 volunteer = new Volunteer(rs2.getString(2));

                volunteer.setId(rs2.getInt(1));

             }
              
             if(volunteer != null){
                 volunteersList.add(volunteer);
             }
           }
        } catch (SQLException e) {
            System.out.println("\nSql Exception getVolunteerById: 2" + e);
        }

        return volunteersList;
    }
    


    private List<Needy> getNeedyList(int id) {
        
        List<Needy> needyList = new ArrayList<>();
                 
        
        List<Integer> needyIds = new ArrayList<>();
        
        
        try{
            ResultSet rs;
            rs = stmt.executeQuery("Select * from Project_List where projectId = " + id);
            
            while(rs.next()){
               Integer needyId = rs.getInt(3);
               needyIds.add(needyId);
            }
            
            
        }catch(SQLException e){
            
            System.out.println("Sql Exception getNeedy List 1:\t"+e);
        }
        
        
       try {
       
           for(int i = 0;i<needyIds.size();i++){
            Needy needy = null;

            ResultSet rs2 = stmt.executeQuery("select * from dms.dbo.Needy where needyId = " + needyIds.get(i));

            while(rs2.next()){
                if(needy == null)
                 needy = new Needy(rs2.getString(2));

                needy.setId(rs2.getInt(1));
                needy.setAmountNeeded(rs2.getInt(3));
                needy.setAddress(rs2.getString(4));
                needy.setPhoneNo(rs2.getString(5));
                needy.setDob(rs2.getString(6));
                needy.setIncome(rs2.getInt(7));
                
                //Disburse Funds List is not set yet.
  
            }
            
             if(needy != null){
                 needyList.add(needy);
             }
           }
        } catch (SQLException e) {
            System.out.println("\nSql Exception getVolunteerById: 2" + e);
        }
       
        return needyList;
    }
        
    Project getProjectByTeamLead(String teamLead) {
            
        Project project = null;
        try{
                
            String s = "Select top 1 * from Project Join Volunteer ON Project.leadId = Volunteer.volunteerId Where name = '" + teamLead + "'";
            ResultSet rs = stmt.executeQuery(s);
            int id = -1;
            while (rs.next()) {
                if(project == null)
                    project = new Project(rs.getString(5), rs.getString(2));
    
                project.setId(rs.getInt(1));

                project.setAvailableFund(rs.getInt(3));
                id = rs.getInt(4);

            }

            if (project != null && id!= -1){
                Volunteer v = getVolunteerById(id);

                project.setTeamLead(v);

                List<Volunteer> volunteers = getVolunteersList(project.getId());

                if(volunteers.size()>0){
                    project.setTeamList(volunteers);
                }

                List<Needy> needyList = getNeedyList(project.getId());
                if(needyList.size()>0){
                    project.setBeneficiariesList(needyList);
                }
            }
               
        }
         catch(SQLException e){
             System.out.println("Sql Exception Get Project By Name :\t" +  e);
                
        }  
            return project;
    }
    
    public void updateProjectBalance(Project project){
        try {
            int rs1 = stmt.executeUpdate("Update Project set availableFund = " + project.getAvailableFund() + " WHERE projectId ="
                    + project.getId());

            System.out.println("\nUpdated AvailableFund in Project: rows affected + " + rs1);

        } catch (SQLException e) {
            System.out.println("\nSql Exception Update AvailableFund in Project: " + e);
        }
    }

    void addProjectDonationList(Donation donation) {

        try {
            int rs = stmt.executeUpdate("Insert into Project_List values (" + donation.getProject().getId()
                    + ",NULL"  + ",NULL," + donation.getId() +")" );    //Inserting latest element of team list
        } catch (SQLException e) {
            System.out.println("\nSql Exception addProjectTeamList: " + e);
        }
        
    }
    
    void addDisburseFund(Needy needy, Gift disbFund){
        try{
            int rs = stmt.executeUpdate("Insert into Disburse_Fund values (" + disbFund.getAmount() + ", 'Gift', NULL)");     
                    
        } catch(SQLException e){
            System.out.println("\nSql Exception addDisburseFund: " + e);
        }
        
        try {
            ResultSet resultSet = stmt.executeQuery("Select Max(disbFundId) from Disburse_Fund");

            while (resultSet.next()) {
                disbFund.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        try{
            int rs = stmt.executeUpdate("Insert into Needy_List values (" + needy.getId() + "," + disbFund.getId() + ")");
        } catch (SQLException e){
            System.out.println(e);
        }
    }
}
