package duke.task;

/**
 * The Task class serves as a class to be extended by other classes of tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of Task class. 
     * @param description The task description is stored as a String.
     * @param isDone Tracks if Task is done. Set to false when Tasks are initialised.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter for the Task description.
     * @return Returns the Task description.
     */
    public String getTask() {
        return this.description;
    }

    /**
     * Gets the status of the Task.
     * @return Returns a String, a space if Task is not done, and X otherwise.  
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Alternate method to check if task is done. Used by other classes.
     * @return Returns a String, 0 if Task is not done, and 1 otherwise.
     */
    public String getBinaryStatus() {
        return (isDone ? "1" : "0");
    }

    /**
     * Sets the done status of the Task.
     * @param status Takes in a 0 to set Task to not done, and any other integer to set as done.
     */
    public void setStatus(int status) {
        this.isDone = status != 0;
    }

    /**
     * Unused method to be overwritten by subclasses of Task with deadlines.
     * @return Returns an empty String as it is not used.
     */
    public String getDue() {
        return "";
    }

    /**
     * Unused method to be overwritten by subclasses of Task to return the class of Task they are.
     * @return Returns an empty String as it is not used.
     */
    public String getTaskType() {
        return "";
    }

    /**
     * Overrides the default toString() method of Object.
     * @return Returns the Task description and its status.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
