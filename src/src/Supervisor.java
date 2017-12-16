import util.DatabaseConnection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Supervisor extends User {

    public static final String USER_TYPE_SUPERVISOR = "SUPERVISOR";
    // ATTRIBUTES
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
    public Supervisor( String name, String surname, String userName,
                       String eMail, String phoneNumber, Date birthDate,
                       String graduationUni, String uniDepartment,
                       String compDepartment){

        super(name, userName, eMail, phoneNumber, USER_TYPE_SUPERVISOR);
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
                        " username           TEXT    NOT NULL, " +
                        " name               TEXT    NOT NULL, " +
                        " surname            TEXT     NOT NULL, " +
                        " department        TEXT    NOT NULL, " +
                        " birthDate         DATE     NOT NULL, "+
                        " graduationUni      TEXT     NOT NULL ," +
                        " uniDepartment      TEXT	  NOT NULL ," ;


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

    // METHODS
    // Getter and setter methods
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
        return birthDate;
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

    public boolean addTask( Team team, Task task){
        for ( Team t : teamList){
            if( t.getName().equals(team.getName())){
                t.addTeamTask(task);
                return true;
            }
        }
        return false;
    }



}
