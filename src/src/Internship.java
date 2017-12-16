import bean.Account;
import sun.rmi.runtime.Log;
import util.DatabaseConnection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Logger;


@Named( value = "internship")
@RequestScoped
public class Internship {

    @Inject
    private Account account;

    private Company company;

    public Internship(){ }



    public Intern createIntern( String name, String surname, String userName,
                              String eMail, String phoneNumber, Date birthDate,
                              String universityName, String departmentName){

        return new Intern( name, surname, userName, eMail, phoneNumber, birthDate, universityName, departmentName);
    }



}
