package bean;

import util.DatabaseConnection;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import Internship.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;


@Named( value = "internship")
@RequestScoped
public class Internship {

    /*
    @Inject
    private Account neededBean;

    public Account getAccount()
    {
        return neededBean;
    }

    public void setAccount(Account neededBean)
    {
        this.neededBean = neededBean;
    }
*/

    private Company company;
    private Intern intern = new Intern();
    private Supervisor supervisor;
    private Team team;

    private String userName;

    public void createInternData(){
        //Get connection
        try {
            Connection con = DatabaseConnection.getConnInst();

            //The code below is just a sample, not the correct operation, no insert!

            //Getting the last index no as user id
            PreparedStatement stmt = con.prepareStatement("SELECT lastUserID FROM UserIDGen LIMIT 1");
            ResultSet resultSet = stmt.executeQuery();
            int curId = 0;
            while (resultSet.next() ) {
                curId = resultSet.getInt("lastUserID");
            }
            //Logger.getLogger(getClass().getName()).info( "Account: current id is :" + curId);

            stmt.close();

            //Logger.getLogger(getClass().getName()).info( "Account: signupUserName = " + signupUserName);


            //TODO control whether all information is input (name, password, email, usertype)
            //TODO also control no same username or same e-mail

            /*stmt = con.prepareStatement("INSERT INTO account VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, ++curId); //For now, current id is just sequentally increasing
            stmt.setString(2, signupUserName);
            stmt.setString(3, signupPassword);
            stmt.setString(4, signupEmail);
            stmt.setInt(5, userType);
            stmt.setString(6, ""); //Nothing initially
            int rows = stmt.executeUpdate();*/

            stmt.close();

            //TODO Return value according to user type Internship.Internship.User type
            /*if( userType == USERTYPE_FREE_INTERN) {

            }
            else if ( userType == USERTYPE_FREE_SUPERVISOR) {

            }
            else if( userType == USERTYPE_COMPANY) {

            }*/

        }catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).warning("SQLException: " + ex.getMessage());
            //TODO return error
        }


    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Intern getIntern() {
        return intern;
    }

    public void setIntern(Intern intern) {
        this.intern = intern;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void lalala() {
        Account account = (Account) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("account");
        if( account != null)
        Logger.getLogger(getClass().getName()).info("Account is inject yo: "+ account.getUserName() );
    }
}
