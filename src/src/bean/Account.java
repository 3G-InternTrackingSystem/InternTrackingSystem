package bean;

import sun.rmi.runtime.Log;
import util.DatabaseConnection;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;


@Named( value = "account")
@RequestScoped
public class Account {
    public static int USERTYPE_FREE_INTERN = 0;
    public static int USERTYPE_FREE_SUPERVISOR = 1;
    public static int USERTYPE_COMPANY = 2;
    public static int USERTYPE_INTERN = 3;
    public static int USERTYPE_SUPERVISOR = 4;


    private String userName;
    private String password;

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

    //TODO return string
    public void loginUser(){
        Logger.getLogger(getClass().getName()).info("bean.Account: username is " + userName);

        //Get connection
        try {

            Connection con = DatabaseConnection.getConnInst();

            //The code below is just a sample, not the correct operation, no insert!
            //TODO adjust the code below
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Account WHERE username = '"
                    + userName + "' AND password = '" + password+ "';");

            ResultSet resultSet = stmt.executeQuery();

            while( resultSet.next() ) {
                Logger.getLogger(getClass().getName()).info("Account: login: resultSet.next() while loop");
                userType = resultSet.getInt("usertype");
                eMail = resultSet.getString("email");
                userID = resultSet.getInt("userid");
            }

            if( userType != null) {
                    //TODO return according to user type
                    Logger.getLogger(getClass().getName() ).info("Account: userType is "+ userType);

            }
            else {
                Logger.getLogger(getClass().getName() ).info("Account: no user found");
                //TODO return invalid credentials
            }

            stmt.close();

        }catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).warning("SQLException: " + ex.getMessage());
            //TODO return error
        }
    }

    public void signUp() {
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

            Logger.getLogger(getClass().getName()).info( "Account: signupUserName = " + signupUserName);
            Logger.getLogger(getClass().getName()).info( "Account: signupPassword = " + signupPassword);
            Logger.getLogger(getClass().getName()).info( "Account: signupEmail = " + signupEmail);

            //TODO control whether all information is input (name, password, email, usertype)
            //TODO also control no same username or same e-mail

            stmt = con.prepareStatement("INSERT INTO Account VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, ++curId); //For now, current id is just sequentally increasing
            stmt.setString(2, signupUserName);
            stmt.setString(3, signupPassword);
            stmt.setString(4, signupEmail);
            stmt.setInt(5, userType);
            stmt.setString(6, ""); //Nothing initially
            int rows = stmt.executeUpdate();

            stmt.close();

            //TODO Return value according to user type User type
            if( userType == USERTYPE_FREE_INTERN) {

            }
            else if ( userType == USERTYPE_FREE_SUPERVISOR) {

            }
            else if( userType == USERTYPE_COMPANY) {

            }

        }catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).warning("SQLException: " + ex.getMessage());
            //TODO return error
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



}
