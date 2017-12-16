import java.util.ArrayList;
import java.util.Date;

public class Intern extends User {

    public static final String USER_TYPE_INTERN = "INTERN";
    // ATTRIBUTES
    private String surname;
    private Date birthDate;
    private String universityName;
    private String departmentName;
    private ArrayList<Task> tasks;
    private boolean matched;

    // CONSTRUCTORS

    public Intern(){  }

    public Intern( String name, String surname, String userName,
                   String eMail, String phoneNumber, Date birthDate,
                   String universityName, String departmentName){

        super(name, userName, eMail, phoneNumber, USER_TYPE_INTERN);
        this.surname = surname;
        this.birthDate = birthDate;
        this.universityName = universityName;
        this.departmentName = departmentName;
        this.tasks = new ArrayList<Task>();
        this.matched = false;
    }



    // METHODS
    // Getter and setter methods
    public String getSurname() {
        return surname;
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

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    // Other functional methods
    public void addInternTask( Task task){
        this.tasks.add(task);
    }

    public String setTest(){ return "test";}
}
