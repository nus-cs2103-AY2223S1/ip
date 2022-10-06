package duke;

import java.time.LocalDate;

/**
 * This class encapsulates a task created in the Chatbot.
 *
 * @author Andrew Lo Zhi Sheng
 * @version CS2103T AY22/23 Semester 1
 */
abstract class Task {

    // Fields
    protected boolean isDone; // Indicates if task is completed
    protected String description; // Name of the task

    /**
     * Constructor for the task instance.
     *
     * @param name a String containing the task's name
     */
    public Task(String name) {
        this.description = name;
        this.isDone = false;
    }

    /**
     * Toggles the isDone flag on.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Toggles the isDone flag off.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Gets the string representation of this task.
     *
     * @return A string containing a checkbox indicating if the task is complete and its name.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    /**
     * Gets the status icon for the current task.
     *
     * @return A String with a checked checkbox if the task is completed
     *         A String with an unchecked box otherwise
     */
    private String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Gets the string representation of this task for storage in a file.
     *
     * @return a String containing the task description and whether it is completed.
     */
    public String encode() {
        return String.format("%s # %s",
                             this.isDone ? "done" : "not done",
                             this.description);
    }

    /**
     * Gets the deadline of the duke.Task, if any.
     *
     * @return A LocalDate representing the duke.Task's deadline if it exists
     *         null if no such deadline exists
     */
    public LocalDate getDeadline() {
        return null;
    }
}
