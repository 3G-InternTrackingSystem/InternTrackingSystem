package bean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.io.File;
import java.lang.String;
public class Task {

    private String name;
    private int taskID;
    private int status;
    private Date dueDate;
    private boolean checked;

    public Task (String name, Date dueDate ) {
        this.name = name;
        this.status = 0;                                // 0 is not started yet. When task is assigned it is 0
        this.dueDate = dueDate;
        this.checked = false;

        File a = new File("Tasks.db");
        Boolean b = a.exists();
        if(!b) {

            Connection c = null;
            Statement stmt = null;

            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:Tasks.db");

                stmt = c.createStatement();
                String sql = "CREATE TABLE AllTasks " +
                        "(task_id INTEGER PRIMARY KEY      NOT NULL," +
                        " given_by           VARCHAR(45)   UNIQUE, " +
                        " given_to           VARCHAR(45)    NOT NULL, " +
                        " type               VARCHAR(45)     NOT NULL, " +
                        " description        VARCHAR(45)    NOT NULL, " +
                        " start_date         DATE     NOT NULL, "+
                        " end_date           DATE     NOT NULL ," +
                        " status             INT	  NOT NULL ," +
                        " checked            INT	  NOT NULL ," +
                        " doc_address        VARCHAR(45)     NOT NULL ";

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
    //done


    private  void addTask (String given_by, String given_to , String type, Date start_date, Date end_date, String description ) {

        Connection c = null;
        Statement stmt = null;

        int status  = 0;
        boolean checked = false;
        String doc_address = "";


        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Tasks.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();

            try {
                String sql = "INSERT INTO AllTask (given_by,given_to,type,description,start_date,end_date) " +
                        "VALUES ( '"+ given_by +"','"+ given_to +"','"+ type + "'," + description + "," + start_date + "," + end_date + "," + status + "," + checked +"," + doc_address + " );";
                stmt.executeUpdate(sql);

            }catch( Exception eee) {
                stmt.close();
                c.commit();
                c.close();
                return;
            }


            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            //System.exit(0);
        }
    }
    //done

    private boolean completeTask(int taskID) {

        //int returnStatus = -1;

        Connection ca = null;
        Statement stmta = null;

        try {
            Class.forName("org.sqlite.JDBC");
            ca = DriverManager.getConnection("jdbc:sqlite:Tasks.db");
            ca.setAutoCommit(false);

            stmta = ca.createStatement();
            ResultSet rs = stmta.executeQuery( "SELECT task_id, status FROM AllTasks;" );

            int idd =  rs.getInt("task_id");
            int statuss =  rs.getInt("status");


            while( rs.next()) {
                idd = rs.getInt("task_id");
                statuss =  rs.getInt("status");
                String sql;
                if( idd == taskID) {

                    statuss = 2;   //completed the task
                    sql = "UPDATE AllTask set status =" +statuss+" where task_id=" + taskID + ";";

                    stmta.executeUpdate(sql);
                    ca.commit();
                }
            }

            //returnStatus = statuss;

            rs.close();
            stmta.close();
            ca.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            //System.exit(0);
            return false;
            //return returnStatus;				//
        }
        return true;
        //return returnStatus;
    }
    //done

    private int getStatus() {

        int statusss = -1;

        Connection ca = null;
        Statement stmta = null;

        try {
            Class.forName("org.sqlite.JDBC");
            ca = DriverManager.getConnection("jdbc:sqlite:Tasks.db");
            ca.setAutoCommit(false);

            stmta = ca.createStatement();
            ResultSet rs = stmta.executeQuery( "SELECT task_id, status FROM AllTasks;" );

            int idd =  rs.getInt("task_id");
            int statuss =  rs.getInt("status");


            while( rs.next()) {
                idd = rs.getInt("task_id");
                statuss =  rs.getInt("status");
                if( idd == taskID) {


                    statusss = statuss;
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
        return statusss; 	//gets the status as a return value


    }
    //done

    private boolean getChecked() {
        boolean checkeddd = false;

        Connection ca = null;
        Statement stmta = null;

        try {
            Class.forName("org.sqlite.JDBC");
            ca = DriverManager.getConnection("jdbc:sqlite:Tasks.db");
            ca.setAutoCommit(false);

            stmta = ca.createStatement();
            ResultSet rs = stmta.executeQuery( "SELECT task_id, checked FROM AllTasks;" );

            int idd =  rs.getInt("task_id");
            boolean checkedd =  rs.getBoolean("checked");


            while( rs.next()) {
                idd = rs.getInt("task_id");
                checkedd =  rs.getBoolean("checked");
                if( idd == taskID) {

                    checkeddd = checkedd;
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
        return checkeddd; 	//gets the status as a return value
    }
    //done

    private void updateStatus(int taskID, int statusValue) {

        //int returnStatus = -1;

        Connection ca = null;
        Statement stmta = null;

        try {
            Class.forName("org.sqlite.JDBC");
            ca = DriverManager.getConnection("jdbc:sqlite:Tasks.db");
            ca.setAutoCommit(false);

            stmta = ca.createStatement();
            ResultSet rs = stmta.executeQuery( "SELECT task_id, status FROM AllTasks;" );

            int idd =  rs.getInt("task_id");
            int statuss =  rs.getInt("status");


            while( rs.next()) {
                idd = rs.getInt("task_id");
                statuss =  rs.getInt("status");
                String sql;
                if( idd == taskID) {

                    statuss = statusValue;   //task still in progress after changing
                    sql = "UPDATE AllTask set status =" +statuss+" where task_id=" + taskID + ";";

                    stmta.executeUpdate(sql);
                    ca.commit();
                }
            }

            //returnStatus = statuss;
            rs.close();
            stmta.close();
            ca.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            //System.exit(0);
            //return returnStatus;				//
        }
        //return returnStatus;
    }
    //done

    private void markChecked(int taskID) {

        //int returnStatus = -1;

        Connection ca = null;
        Statement stmta = null;

        try {
            Class.forName("org.sqlite.JDBC");
            ca = DriverManager.getConnection("jdbc:sqlite:Tasks.db");
            ca.setAutoCommit(false);

            stmta = ca.createStatement();
            ResultSet rs = stmta.executeQuery( "SELECT task_id, checked FROM AllTasks;" );

            int idd =  rs.getInt("task_id");
            boolean checkedd =  rs.getBoolean("checked");


            while( rs.next()) {
                idd = rs.getInt("task_id");
                checkedd =  rs.getBoolean("checked");
                String sql;
                if( idd == taskID) {

                    checkedd = true;   //completed the task
                    sql = "UPDATE AllTask set status =" +checkedd+" where task_id=" + taskID + ";";

                    stmta.executeUpdate(sql);
                    ca.commit();
                }
            }

            //returnStatus = statuss;

            rs.close();
            stmta.close();
            ca.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            //System.exit(0);
            //return returnStatus;
        }
        //return returnStatus;
    }
    //done





}