package bean;

import util.DatabaseConnection;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.enterprise.context.RequestScoped;

@ManagedBean
@RequestScoped

public class Supervisor extends User {

    public static final String USER_TYPE_SUPERVISOR = "SUPERVISOR";
    // ATTRIBUTES

    private String name;
    private String surname;
    private Date birthDate;
    private int userID;
    private String graduationUni;
    private String uniDepartment;
    private String compDepartment;
    private int totalSpace;
    private ArrayList<Intern> internList;
    private ArrayList<Team> teamList;
    private boolean matched;

    // CONSTRUCTORS
    public Supervisor( String name, String surname, String password, String userName,
                       String eMail, String phoneNumber, Date birthDate,
                       String graduationUni, String uniDepartment,
                       String compDepartment){

        super(name, userName, password, eMail, phoneNumber, USER_TYPE_SUPERVISOR);
        this.surname = surname;
        this.birthDate = birthDate;
        this.graduationUni = graduationUni;
        this.uniDepartment = uniDepartment;
        this.compDepartment = compDepartment;
        this.totalSpace = 0;
        this.internList = new ArrayList<Intern>();
        this.matched = false;


        File a = new File("supervisor.db");
        Boolean b = a.exists();
        if(!b) {

            Connection c = null;
            Statement stmt = null;

            try {
                c = DatabaseConnection.getConnInst();

                stmt = c.createStatement();
                String sql = "CREATE TABLE AllSupervisors " +
                        "(user_id INTEGER PRIMARY KEY      NOT NULL," +
                        " username           VARCHAR(45)    NOT NULL, " +
                        " name               VARCHAR(45)    NOT NULL, " +
                        " surname            VARCHAR(45)     NOT NULL, " +
                        " department        VARCHAR(45)    NOT NULL, " +
                        " birthDate         DATE     NOT NULL, "+
                        " graduationUni      VARCHAR(45)     NOT NULL ," +
                        " uniDepartment      VARCHAR(45)	  NOT NULL );" ;


                stmt.executeUpdate(sql);
                stmt.close();

                if (stmt != null) {
                    stmt.close();
                }
                if (c != null) {
                    c.close();
                }


            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                //System.exit(0);
            }

        }
    }
    public Supervisor(){}

    // METHODS
    // Getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        String surname = "";

        Connection ca = null;
        Statement stmta = null;

        try {
            ca = DatabaseConnection.getConnInst();
            ca.setAutoCommit(false);

            stmta = ca.createStatement();
            ResultSet rs = stmta.executeQuery( "SELECT surname, status FROM AllSupervisors;" );

            int idd =  rs.getInt("user_id");
            String surnamee =  rs.getString("surname");

            while( rs.next()) {
                idd = rs.getInt("user_id");
                surnamee =  rs.getString("surname");
                if( idd == userID) {

                    surname = surnamee;
                    ca.commit();
                    break;
                }
            }


            rs.close();
            stmta.close();
            ca.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            //System.exit(0);
        }
        return surname; 	//gets the surname as a return value


    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {

        Date birthDatee = null;

        Connection ca = null;
        Statement stmta = null;

        try {
            ca = DatabaseConnection.getConnInst();
            ca.setAutoCommit(false);

            stmta = ca.createStatement();
            ResultSet rs = stmta.executeQuery( "SELECT birthDate, status FROM AllSupervisors;" );

            int idd =  rs.getInt("user_id");
            Date birthDate =  rs.getDate("birthDate");

            while( rs.next()) {
                idd = rs.getInt("user_id");
                birthDate =  rs.getDate("birthDate");
                if( idd == userID) {

                    birthDatee = birthDate;
                    ca.commit();
                    break;
                }
            }


            rs.close();
            stmta.close();
            ca.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            //System.exit(0);
        }
        return birthDatee; 	//gets the graduationUni as a return value
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }



    public String getGraduationUni() {

        String graduationUnii = "";

        Connection ca = null;
        Statement stmta = null;

        try {
            ca = DatabaseConnection.getConnInst();
            ca.setAutoCommit(false);

            stmta = ca.createStatement();
            ResultSet rs = stmta.executeQuery( "SELECT graduationUni, status FROM AllSupervisors;" );

            int idd =  rs.getInt("user_id");
            String graduationUni =  rs.getString("graduationUni");

            while( rs.next()) {
                idd = rs.getInt("user_id");
                graduationUni =  rs.getString("graduationUni");
                if( idd == userID) {

                    graduationUnii = graduationUni;
                    ca.commit();
                    break;
                }
            }

            rs.close();
            stmta.close();
            ca.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            //System.exit(0);
        }
        return graduationUnii; 	//gets the graduationUni as a return value
    }

    public void setGraduationUni(String graduationUni) {
        this.graduationUni = graduationUni;
    }


    public String getUniDepartment() {

        String uniDepartmentt = "";

        Connection ca = null;
        Statement stmta = null;

        try {
            ca = DatabaseConnection.getConnInst();
            ca.setAutoCommit(false);

            stmta = ca.createStatement();
            ResultSet rs = stmta.executeQuery( "SELECT uniDepartment, status FROM AllSupervisors;" );

            int idd =  rs.getInt("user_id");
            String uniDepartment =  rs.getString("uniDepartment");

            while( rs.next()) {
                idd = rs.getInt("user_id");
                uniDepartment =  rs.getString("uniDepartment");
                if( idd == userID) {

                    uniDepartmentt = uniDepartment;
                    ca.commit();
                    break;
                }
            }


            rs.close();
            stmta.close();
            ca.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            //System.exit(0);
        }
        return uniDepartmentt;

    }

    public void setUniDepartment(String uniDepartment) {
        this.uniDepartment = uniDepartment;
    }

    public String getCompDepartment() {

        String departmentt = "";

        Connection ca = null;
        Statement stmta = null;

        try {
            ca = DatabaseConnection.getConnInst();
            ca.setAutoCommit(false);

            stmta = ca.createStatement();
            ResultSet rs = stmta.executeQuery( "SELECT department, status FROM AllSupervisors;" );

            int idd =  rs.getInt("user_id");
            String department =  rs.getString("department");

            while( rs.next()) {
                idd = rs.getInt("user_id");
                department =  rs.getString("department");
                if( idd == userID) {

                    departmentt = department;
                    ca.commit();
                    break;
                }
            }


            rs.close();
            stmta.close();
            ca.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            //System.exit(0);
        }
        return departmentt;
    }

    public void setCompDepartment(String compDepartment) {
        this.compDepartment = compDepartment;
    }

    public int getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(int totalSpace) {
        this.totalSpace = totalSpace;
    }

    public ArrayList<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(ArrayList<Team> teamList) {
        this.teamList = teamList;
    }

    public ArrayList<Intern> getInternList() {
        return internList;
    }

    public void setInternList(ArrayList<Intern> internList) {
        this.internList = internList;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    // Other functional methods
    public boolean addIntern( Intern n){
        if ( internList.size() <= totalSpace){
            internList.add(n);
            n.setMatched(true);
            return true;
        }
        else{
            this.setMatched(true);


            /*
               UI needs to be updated.
            * */

            return false;
        }
    }

    public void createTeam( ArrayList<Intern> internList, String nameOfTeam){
        Team team = new Team(nameOfTeam, internList.size());
        team.setInternList(internList);
    }

    public boolean addTask( Intern intern, Task task){
        for ( Intern i : internList){
            if( i.getUserName().equals(intern.getUserName())){
                 i.addInternTask(task);
                 return true;
            }
        }
        return false;
    }

    public boolean addTask(Team team, Task task){
        for ( Team t : teamList){
            if( t.getName().equals(team.getName())){
                t.addTeamTask(task);
                return true;
            }
        }
        return false;
    }


    public static class Team  {

        // ATTRIBUTES
        private String name;
        private int noOfInterns;
        private ArrayList<Intern> internList;
        private ArrayList<Task> taskList;

        // CONSTRUCTORS
        public Team( String name, int noOfInterns){
            this.name = name;
            this.noOfInterns = noOfInterns;
            this.internList = new ArrayList<Intern>();
        }

        // METHODS
        // Getter and setter methods
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNoOfInterns() {
            return noOfInterns;
        }

        public void setNoOfInterns(int noOfInterns) {
            this.noOfInterns = noOfInterns;
        }

        public ArrayList<Intern> getInternList() {
            return internList;
        }

        public void setInternList(ArrayList<Intern> internList) {
            this.internList = internList;
        }

        public void addTeamTask( Task task){
            this.taskList.add( task);
        }
    }
}
