package bean;

import util.Connector;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;



@ManagedBean
@RequestScoped
public class Company extends User {
    Connector connector;
    Connection connection;
    public static final String USER_TYPE_COMPANY = "COMPANY";
    // ATTRIBUTES
    private String address;
    private ArrayList<Department> departments;
    private String companyDescription;


    private ArrayList<Intern> ints;
    private ArrayList<Supervisor> sups;
    // CONSTRUCTORS
    public Company(String name, String userName, String password, String eMail, String phoneNumber, String address, String companyDescription) {

        super(name, userName, password, eMail, phoneNumber, USER_TYPE_COMPANY);
        this.address = address;
        this.departments = new ArrayList<Department>();
        this.companyDescription = companyDescription;

        try{
            connection = connector.getConnection1();
            PreparedStatement pStat = connection.prepareStatement("INSERT INTO account VALUES(?,?,?,?,?,?)");
            pStat.setString(2,userName);
            pStat.setString(3,password);
            pStat.setString(4,eMail);
            pStat.setString(5,USER_TYPE_COMPANY);
            pStat.setString(6,phoneNumber);
            pStat.executeUpdate();
            PreparedStatement pStat2 = connection.prepareStatement("INSERT INTO company VALUES(?,?,?,?)");
            pStat.setString(1,userName);
            pStat.setString(2,name);
            pStat.setString(3,address);
            pStat.setString(4,companyDescription);
            pStat.executeUpdate();
            connection.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public Company(){}

    // METHODS
    // Getter and setter methods
    public String getAddress() {
        ResultSet rs = null;
        StringBuffer buffer = new StringBuffer();
        try
        {
            connection = connector.getConnection1();
            Statement st = connection.createStatement();
            rs = st.executeQuery("SELECT address FROM company WHERE username = '" + getUserName() + "'");
        while(rs.next())
        {
            buffer.append(rs.getString("address"));
        }
        }catch(SQLException e)
        {

        }
        return buffer.toString();
    }

    public void setAddress(String address) {
        try {
            connection = connector.getConnection1();
            PreparedStatement pStat = connection.prepareStatement("UPDATE company SET address = " + address + " WHERE username = " + getUserName());
            pStat.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }

    public String getCompanyDescription() {
        ResultSet rs = null;
        StringBuffer sb = new StringBuffer();
        try {
            connection = connector.getConnection1();
            Statement st = connection.createStatement();
            rs = st.executeQuery("SELECT description FROM company WHERE username = '" + getUserName() + "'");


        while(rs.next())
        {
            sb.append(rs.getString("description"));
        }}
        catch(SQLException e)
        {

        }
        return sb.toString();
    }

    public void setCompanyDescription(String companyDescription) {
        try {
            connection = connector.getConnection1();
            PreparedStatement pStat = connection.prepareStatement("UPDATE company SET description = " + companyDescription + " WHERE username = " + getUserName());
            pStat.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    // Other functional methods
    public void createDepartment(ArrayList<Intern> internList, ArrayList<Supervisor> supervisorList, String name) {
        Department newDep = new Department(name);
        newDep.setInternList(internList);
        newDep.setSupervisorList(supervisorList);
        ArrayList<Department> res = new ArrayList<Department>();
        res = getDepartments();
        res.add(newDep);
        setDepartments(res);
        try {
            connection = connector.getConnection1();
            PreparedStatement pStat = connection.prepareStatement("INSERT INTO department VALUES(?,?)");
            pStat.setString(1, name);
            pStat.setString(2, getUserName());
            pStat.executeUpdate();
        }catch(SQLException e)
        {

        }

    }

    public String match(Intern intern, Supervisor supervisor){
        if(!intern.isMatched() && (intern.getDepartmentName() == supervisor.getCompDepartment())){
            supervisor.addIntern(intern);
            intern.setMatched(true);
            return intern.getName() + " " + intern.getSurname() + " is successfully matched to supervisor " + supervisor.getName() + " " + supervisor.getSurname() + "!";
        }
        else
            return intern.getName() + " " + intern.getSurname() + " already has a supervisor, you can not do this action!";
    }
    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }

    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }

    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }

    public ArrayList<Intern> getInts() {
        return ints;
    }

    public ArrayList<Supervisor> getSups() {
        return sups;
    }
}
