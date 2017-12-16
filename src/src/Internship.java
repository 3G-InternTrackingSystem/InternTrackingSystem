import bean.Account;
import bean.Company;
import bean.Intern;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;


@Named( value = "internship")
@RequestScoped
public class Internship {

    @Inject
    private Account account;

    private Company company;

    public Internship(){ }



    public Intern createIntern(String name, String surname, String userName,
                               String eMail, String phoneNumber, Date birthDate,
                               String universityName, String departmentName){

        return new Intern( name, surname, userName, eMail, phoneNumber, birthDate, universityName, departmentName);
    }



}
