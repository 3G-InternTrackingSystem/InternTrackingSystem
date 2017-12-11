import java.util.ArrayList;

public class Department {

    // ATTRIBUTES
    private String departmentName;
    private ArrayList<Supervisor> supervisorList;
    private ArrayList<Intern> internList;
    private ArrayList<Supervisor> unmatchedSupervisorList;
    private ArrayList<Intern> unmatchedInternList;

    // CONSTRUCTORS
    public Department( String departmentName){
        this.departmentName = departmentName;
        this.internList = new ArrayList<Intern>();
        this.supervisorList = new ArrayList<Supervisor>();
        this.unmatchedInternList = setUnmatchedInternList() ;
        this.unmatchedSupervisorList = setUnmatchedSupervisorList();
    }

    // METHODS
    // Getter and setter methods
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public ArrayList<Supervisor> getSupervisorList() {
        return supervisorList;
    }

    public void setSupervisorList(ArrayList<Supervisor> supervisorList) {
        this.supervisorList = supervisorList;
    }

    public ArrayList<Intern> getInternList() {
        return internList;
    }

    public void setInternList(ArrayList<Intern> internList) {
        this.internList = internList;
    }

    public ArrayList<Supervisor> getUnmatchedSupervisorList() {
        return unmatchedSupervisorList;
    }

    public ArrayList<Supervisor> setUnmatchedSupervisorList() {
        ArrayList<Supervisor> unmatched = new ArrayList<Supervisor>();

        for ( Supervisor s: supervisorList){
            if ( !s.isMatched()){
                unmatched.add(s);
            }
        }
        return unmatched;
    }

    public ArrayList<Intern> getUnmatchedInternList() {
        return unmatchedInternList;
    }

    public ArrayList<Intern> setUnmatchedInternList() {
        ArrayList<Intern> unmatched = new ArrayList<Intern>();

        for ( Intern i: internList){
            if ( !i.isMatched()){
                unmatched.add(i);
            }
        }
        return unmatched;
    }

    // Other functional methods

}
