package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Task {
    protected String description;
    protected boolean isDone;
    protected int statusint;
    /*test*/
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.statusint = 0;
    }

    public Task(String description,int x){
        this.description = description;
        this.isDone = true;
        this.statusint = 1;
    }

    public String getTask(){
        return this.description;
    }

    public int getStatusint() {
        return this.statusint;
    }


    public void setStatus(){
        isDone = true;
        statusint = 1;
    }


    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

}