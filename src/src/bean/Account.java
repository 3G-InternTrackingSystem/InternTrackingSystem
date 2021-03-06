package bean;

import org.primefaces.context.RequestContext;
import sun.rmi.runtime.Log;
import util.DatabaseConnection;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;


@Named( value = "account")
@SessionScoped
public class Account implements Serializable {
    public static int USERTYPE_FREE_INTERN = 0;
    public static int USERTYPE_FREE_SUPERVISOR = 1;
    public static int USERTYPE_COMPANY = 2;
    public static int USERTYPE_INTERN = 3;
    public static int USERTYPE_SUPERVISOR = 4;

    private String userName;
    private String password;
    private String invitationKey;

    //These are needed for the signup part, otherwise if we use the same properties
    //there occurs ambiguity

    private String signupUserName;
    private String signupPassword;
    private String signupEmail;

    private String eMail;
    private Integer userType;
    private int userID;

    public Account(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        //Logger.getLogger(getClass().getName()).info("Account: signUpUserName = " + signupUserName);
        //Logger.getLogger(getClass().getName()).info("Account: userType = " + userType);
        this.userType = userType;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getSignupUserName() {
        return signupUserName;
    }

    public void setSignupUserName(String signupUserName) {
        this.signupUserName = signupUserName;
    }

    public String getSignupPassword() {
        return signupPassword;
    }

    public void setSignupPassword(String signupPassword) {
        this.signupPassword = signupPassword;
    }

    public String getSignupEmail() {
        return signupEmail;
    }

    public void setSignupEmail(String signupEmail) {
        this.signupEmail = signupEmail;
    }

    public String getInvitationKey() {
        return invitationKey;
    }

    public void setInvitationKey(String invitationKey) {
        this.invitationKey = invitationKey;
    }

    public String loginUser(){
        Logger.getLogger(getClass().getName()).info("bean.Account: username is " + userName);
        Logger.getLogger(getClass().getName()).info("bean.Account: password is " + password);

        //Get connection
        try {

            Connection con = DatabaseConnection.getConnInst();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM account WHERE username = '"
                    + userName + "' AND password = '" + password+ "';");

            ResultSet resultSet = stmt.executeQuery();

            while( resultSet.next() ) {
                Logger.getLogger(getClass().getName()).info("Account: login: resultSet.next() while loop");
                userType = resultSet.getInt("type");
                eMail = resultSet.getString("email");
                userID = resultSet.getInt("userid");
            }

            stmt.close();


            if( userType == null) {
                Logger.getLogger(getClass().getName() ).info("Account: no user found");
                return "invalid-credentials";
            }
            else {
                Logger.getLogger(getClass().getName() ).info("Account: userType is "+ userType);

                if( userType == USERTYPE_INTERN){
                    return "signInIntern";
                }
                else if(userType == USERTYPE_FREE_INTERN) {
                    return "signInFreeIntern";
                }
                else if(userType == USERTYPE_SUPERVISOR) {
                    return "signInSupervisor";
                }
                else if(userType == USERTYPE_FREE_SUPERVISOR) {
                    Logger.getLogger(getClass().getName() ).info("Account, signInFreeSupervisor");
                    return "signInFreeSupervisor";
                }
                else if(userType == USERTYPE_COMPANY) {
                    return "signInCompany";

                }
                return "error";
            }

        }catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).warning("SQLException: " + ex.getMessage());
            return "error";
        }
    }



    public String signUp() {
        //Get connection
        try {
            Connection con = DatabaseConnection.getConnInst();

            //The code below is just a sample, not the correct operation, no insert!

            //Getting the last index no as user id
            /*
            PreparedStatement stmt = con.prepareStatement("SELECT lastUserID FROM UserIDGen LIMIT 1");
            ResultSet resultSet = stmt.executeQuery();
            int curId = 0;
            while (resultSet.next() ) {
                curId = resultSet.getInt("lastUserID");
            }
            //Logger.getLogger(getClass().getName()).info( "Account: current id is :" + curId);

            stmt.close();
            */

            Logger.getLogger(getClass().getName()).info( "Account: signupUserName = " + signupUserName);
            Logger.getLogger(getClass().getName()).info( "Account: signupPassword = " + signupPassword);
            Logger.getLogger(getClass().getName()).info( "Account: signupEmail = " + signupEmail);

            PreparedStatement stmt = con.prepareStatement("INSERT INTO account(username, password, email, type, phone) VALUES (?, ?, ?, ?, ?)");
            //stmt.setInt(1, ++curId); //For now, current id is just sequentally increasing
            stmt.setString(1, signupUserName);
            stmt.setString(2, signupPassword);
            stmt.setString(3, signupEmail);
            stmt.setInt(4, userType);
            stmt.setString(5, ""); //Nothing initially
            int rows = stmt.executeUpdate();


            stmt.close();




            if( userType == USERTYPE_FREE_INTERN) {
                return "signUpIntern";
            }
            else if ( userType == USERTYPE_FREE_SUPERVISOR) {
                return "signUpSupervisor";
            }
            else if( userType == USERTYPE_COMPANY) {
                return "signUpCompany";
            }
            return "error";

        }catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).warning("SQLException: " + ex.getMessage());
            return "error";
        }
    }

    public void validateName(FacesContext context, UIComponent component, Object value) {
        String nameToCheck = (String) value;
        try {
            Connection con = DatabaseConnection.getConnInst();

            //The code below is just a sample, not the correct operation, no insert!

            //Getting the last index no as user id
            PreparedStatement stmt = con.prepareStatement("SELECT userid FROM Account WHERE username = '" + nameToCheck + "';");
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Logger.getLogger(getClass().getName() ).info("AccountValidator: user is already in the db");

                context.addMessage(component.getClientId(), new FacesMessage("This user name is already used!"));
                context.validationFailed();
                ((UIInput) component).setValid(false);
            }

            stmt.close();
        }catch (SQLException ex) {
            Logger.getLogger(getClass().getName() ).info("AccountValidator: SQL Exception " + ex.getMessage());
        }
    }

    public void validateEmail(FacesContext context, UIComponent component, Object value) {
        String nameToCheck = (String) value;
        try {
            Connection con = DatabaseConnection.getConnInst();

            //The code below is just a sample, not the correct operation, no insert!

            //Getting the last index no as user id
            PreparedStatement stmt = con.prepareStatement("SELECT userid FROM Account WHERE email = '" + nameToCheck + "';");
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Logger.getLogger(getClass().getName() ).info("AccountValidator: user is already in the db");

                context.addMessage(component.getClientId(), new FacesMessage("This e-mail is already used!"));
                context.validationFailed();
                ((UIInput) component).setValid(false);
            }

            stmt.close();
        }catch (SQLException ex) {
            Logger.getLogger(getClass().getName() ).info("AccountValidator: SQL Exception " + ex.getMessage());
        }
    }

    public void validateUserType(FacesContext context, UIComponent component, Object value) {
        String nameToCheck = (String) value;

        if( value == null) {
            Logger.getLogger(getClass().getName()).info("AccountValidator: No user type");

            context.addMessage(component.getClientId(), new FacesMessage("This e-mail is already used!"));
            context.validationFailed();
            ((UIInput) component).setValid(false);
        }
    }

    //TODO this method should be a method of Internship class
    public void compareInvitationKey() {
        Logger.getLogger(getClass().getName() ).info("Account, deneme");

        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;

        //TODO get the companies unique key and do the comparison
        //For now just a sample is done
        String sampleKey = "xyz_123";

        if(invitationKey != null && invitationKey.equals(sampleKey) ) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "You are Accepted", invitationKey);
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Invalid invitation key");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
    }

}
