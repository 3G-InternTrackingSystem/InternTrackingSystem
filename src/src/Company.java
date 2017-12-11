import java.util.ArrayList;

public class Company extends User {

    public static final String USER_TYPE_COMPANY = "COMPANY";
    // ATTRIBUTES
    private String address;
    private ArrayList<Department> departments;
    private String companyDescription;

    // CONSTRUCTORS
    public Company(String name, String userName, String eMail, String phoneNumber,
                   String address, String companyDescription){

        super( name, userName, eMail, phoneNumber, USER_TYPE_COMPANY);
        this.address = address;
        this.departments = new ArrayList<Department>();
        this.companyDescription = companyDescription;
    }

    // METHODS
    // Getter and setter methods
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    // Other functional methods
    public void createDepartment( ArrayList<Intern> internList, ArrayList<Supervisor> supervisorList, String name){
        Department newDep = new Department( name);
        newDep.setInternList(internList);
        newDep.setSupervisorList(supervisorList);
    }
}
