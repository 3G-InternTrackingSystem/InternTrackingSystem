import java.util.ArrayList;
import java.util.Date;

public class Supervisor extends User {

    public static final String USER_TYPE_SUPERVISOR = "SUPERVISOR";
    // ATTRIBUTES
    private String surname;
    private Date birthDate;
    private String graduationUni;
    private String uniDepartment;
    private String compDepartment;
    private int totalSpace;
    private ArrayList<Intern> internList;
    private ArrayList<Team> teamList;
    private boolean matched;

    // CONSTRUCTORS
    public Supervisor( String name, String surname, String userName,
                       String eMail, String phoneNumber, Date birthDate,
                       String graduationUni, String uniDepartment,
                       String compDepartment){

        super(name, userName, eMail, phoneNumber, USER_TYPE_SUPERVISOR);
        this.surname = surname;
        this.birthDate = birthDate;
        this.graduationUni = graduationUni;
        this.uniDepartment = uniDepartment;
        this.compDepartment = compDepartment;
        this.totalSpace = 0;
        this.internList = new ArrayList<Intern>();
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

    public String getGraduationUni() {
        return graduationUni;
    }

    public void setGraduationUni(String graduationUni) {
        this.graduationUni = graduationUni;
    }

    public String getUniDepartment() {
        return uniDepartment;
    }

    public void setUniDepartment(String uniDepartment) {
        this.uniDepartment = uniDepartment;
    }

    public String getCompDepartment() {
        return compDepartment;
    }

    public void setCompDepartment(String compDepartment) {
        this.compDepartment = compDepartment;
    }

    public int getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(int totalSpace) {
        this.totalSpace = totalSpace;
    }

    public ArrayList<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(ArrayList<Team> teamList) {
        this.teamList = teamList;
    }

    public ArrayList<Intern> getInternList() {
        return internList;
    }

    public void setInternList(ArrayList<Intern> internList) {
        this.internList = internList;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    // Other functional methods
    public boolean addIntern( Intern n){
        if ( internList.size() <= totalSpace){
            internList.add(n);
            n.setMatched(true);
            return true;
        }
        else{
            this.setMatched(true);


            /*
               UI needs to be updated.
            * */

            return false;
        }
    }

    public void createTeam( ArrayList<Intern> internList, String nameOfTeam){
        Team team = new Team(nameOfTeam, internList.size());
        team.setInternList(internList);
    }

    public boolean addTask( Intern intern, Task task){
        for ( Intern i : internList){
            if( i.getUserName().equals(intern.getUserName())){
                 i.addInternTask(task);
                 return true;
            }
        }
        return false;
    }

    public boolean addTask( Team team, Task task){
        for ( Team t : teamList){
            if( t.getName().equals(team.getName())){
                t.addTeamTask(task);
                return true;
            }
        }
        return false;
    }



}
