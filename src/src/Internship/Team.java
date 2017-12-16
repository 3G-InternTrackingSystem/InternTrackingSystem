package Internship;

import java.util.ArrayList;
import TaskManagement.*;

public class Team  {

    // ATTRIBUTES
    private String name;
    private int noOfInterns;
    private ArrayList<Intern> internList;
    private ArrayList<Task> taskList;

    // CONSTRUCTORS
    public Team( String name, int noOfInterns){
        this.name = name;
        this.noOfInterns = noOfInterns;
        this.internList = new ArrayList<Intern>();
    }

    // METHODS
    // Getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoOfInterns() {
        return noOfInterns;
    }

    public void setNoOfInterns(int noOfInterns) {
        this.noOfInterns = noOfInterns;
    }

    public ArrayList<Intern> getInternList() {
        return internList;
    }

    public void setInternList(ArrayList<Intern> internList) {
        this.internList = internList;
    }

    public void addTeamTask( Task task){
        this.taskList.add( task);
    }
}
