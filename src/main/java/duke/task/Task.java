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
    /** Stores the status of the task */
    protected boolean isDone;


    /**
     * Constructor for Task.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task.
     *
     * @param description Description of the Task.
     * @param isDone Completeness of Task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns true if task contains the keyword.
     *
     * @param keyword The keyword to search for.
     * @return true if task description contains keyword, false otherwise.
     */
    public boolean match(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Gets StatusIcon of the Task.
     *
     * @return Status Icon of the task: "X" if Done, else " ".
     */
    public String getStatusIcon() {
        // mark done task with X
        return (this.isDone ? "X" : " ");
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
     * Gets date of Task.
     *
     * @return Date of Task.
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
     * Stringify task for storage.
     *
     * @return a string representing the task
     */
    public String stringify() {
        return String.format("%s | %d", this.description, this.isDone ? 1 : 0);
    }

    /**
     * Returns the string representation of a Task.
     *
     * @return String representation of a Task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
