package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Task {
    protected String description;
    protected boolean isDone;
    protected int statusint;

    /**
     *Creates a task object
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.statusint = 0;
    }

    /**
     *Creates  a Task object and sets result to true
     * @param description
     * @param x
     */
    public Task(String description,int x){
        this.description = description;
        this.isDone = true;
        this.statusint = 1;
    }

    public String getTask(){
        return this.description;
    }

    /**
     *Gets status like tick or X
     * @return
     */

    public int getStatusint() {
        return this.statusint;
    }

    /**
     *Set the status to true from false.From X to tick
     */
    public void setStatus(){
        isDone = true;
        statusint = 1;
    }

    /**
     *Return the current status
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

}