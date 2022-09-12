package duke.task;

import java.time.LocalDateTime;

/**
 * A Task class that stores the Description and State of the Task.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Task {
    private static final String ICON_DONE = "X";
    private static final String ICON_NOTDONE = " ";
    /** Stores the description of the task */
    protected String description;
    /** Stores the status of the task */
    protected boolean isDone;


    /**
     * Constructor for Dummy Task.
     *
     */
    public Task() {
        this.description = "Dummy Description";
        this.isDone = false;
    }

    /**
     * Constructor for Task.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;

        //check the class invariant
        assert hasValidState() : "Construction failed - not valid state.";
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

        //check the class invariant
        assert hasValidState() : "Construction failed - not valid state.";
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
        return (this.isDone ? ICON_DONE : ICON_NOTDONE);
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
     * Implements the class invariant.
     *
     * Perform all checks on the state of the object.
     * One may assert that this method returns true at the end
     * of every public method.
     * @return true if valid State, false otherwise.
     */
    private boolean hasValidState() {
        return isValidDescription(this.description);
    }

    /**
     * Returns validity of description.
     *
     * @param description The description of the task.
     * @return true if valid description, false otherwise.
     */
    private boolean isValidDescription(String description) {
        return !description.isEmpty();
    }

    // Stringify adapted from https://github.com/dexter-sim/ip/blob/master/src/main/java/duke/task/Task.java
    /**
     * Stringify task for storage.
     *
     * @return a string representing the task
     */
    public String stringify() {
        return String.format("%s | %s", this.description, getStatusIcon());
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
