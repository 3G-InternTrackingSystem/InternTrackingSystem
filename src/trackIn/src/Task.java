package Internship;

import java.sql.Date;
import java.lang.String;

public class Task {

    private String taskName;
    private int taskID;
    private int status;
    private Date dueDate;
    private boolean checked;

    public Task (String taskName, Date dueDate ) {
        this.taskName = taskName;
        this.status = 0;                                // 0 is not started yet. When task is assigned it is 0
        this.dueDate = dueDate;
        this.checked = false;

    }

    private String completeTask(int taskID) {

        status = 2;                                    // 2 is for completed
        String message = "The task " + taskName +  " has been completed.";
        return message;
    }

    private int getStatus() {
        return status;
    }


    private boolean getChecked() {
        return checked;
    }


    private void unCompleteTask(int taskID) {

        status = 1;                                    // 1 is for in progress
    }


    private void markCompleted(int taskID) {

        checked= true;
    }






}

