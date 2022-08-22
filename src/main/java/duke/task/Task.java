package duke.task;

/**
 * A Task class that stores the Description and State of the Task.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Task {
    /** Stores the description of the task */
    protected String description;
    /** Type of task */
    protected String type;
    /** Stores the status of the task */
    protected boolean isDone;


    /**
     * Constructor for Task.
     *
     * @param description Description of the task.
     * @param type Type of the Task.
     */
    public Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    /**
     * Constructor for Task.
     *
     * @param description Description of the Task.
     * @param done Completeness of Task.
     * @param type Type of the Task.
     */
    public Task(String description, String done, String type) {
        this.description = description;
        this.type = type;

        if (done.equals("1")) {
            this.isDone = true;
        }
    }

    /**
     * Gets StatusIcon of the Task.
     * If task is done, return "X".
     * Else, " ".
     *
     * @return Status Icon of the task: "X" for Done, else " ".
     */
    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }

    /**
     * Gets status of the Task.
     *
     * @return Status of the Task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Gets type of the Task.
     *
     * @return Type of Task (Deadline, Event, Todo)
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets date of Task.
     *
     * @return Date of task.
     */
    public String getDate() {
        return "Not Applicable";
    }

    /**
     * Gets description of the Task.
     *
     * @return returns description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets Task to be done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets Task to not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of a task.
     *
     * @return String representation of a Task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
