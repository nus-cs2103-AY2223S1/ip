package duke.task;

import java.io.Serializable;

/**
 * Represents a generic Task object. Uses subclasses to determine the type of Task object.
 */
public abstract class Task implements Serializable {

    /** Description of the Task object */
    protected final String description;

    /** Flag to indicate whether the Task object has been completed */
    protected final boolean isDone;


    /**
     * Creates a new Task object.
     * 
     * @param description Description of the Task object.
     * @param isDone Boolean value to indicate whether the Task object has been completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    /**
     * Mark the Task object as complete.
     * 
     * @return A new Task object that is marked as complete.
     */
    public abstract Task markTask();


    /**
     * Mark the Task object as incomplete.
     * 
     * @return A new Task object that is marked as incomplete.
     */
    public abstract Task unmarkTask();
    


    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }


    /**
     * Returns the string representation of the Task object.
     * 
     * @return String representation of the Task object.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
    
}
