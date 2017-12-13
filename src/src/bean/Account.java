package bean;

import util.DatabaseConnection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;


@Named( value = "account")
@RequestScoped
public class Account {
    public static int USERTYPE_COMPANY = 0;
    public static int USERTYPE_INTERN = 1;
    public static int USERTYPE_SUPERVISOR = 2;
    public static int USERTYPE_FREE_INTERN = 3;
    public static int USERTYPE_FREE_SUPERVISOR = 4;

    private String userName;
    private String password;

    //These are needed for the signup part, otherwise if we use the same properties
    //there occurs ambiguity

    private String signupUserName;
    private String signupPassword;
    private String signupEmail;

    private String eMail;
    private int userType;
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

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
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

    public void loginUser(){
        Logger.getLogger(getClass().getName()).info("bean.Account: username is " + userName);
        //Logger.getLogger(getClass().getName()).info( getClass().getName() );

        //Get connection
        try {
            Connection con = DatabaseConnection.getConnInst();

            //The code below is just a sample, not the correct operation, no insert!
            //TODO adjust the code below
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Account VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, 0);
            stmt.setString(2, userName);
            stmt.setString(3, password);
            stmt.setString(4, "example@example.com");
            stmt.setInt(5, 1);
            stmt.setString(6, "123456789");
            int rows = stmt.executeUpdate();

            stmt.close();

        }catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).warning("SQLException: " + ex.getMessage());
        }

        //TODO Get the other information, email, usertype, userid
    }
}
