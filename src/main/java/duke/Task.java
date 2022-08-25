package duke;

/**
 * A Task class that represents a task inputted by user
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task Class
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A method that checks if a task is done, returns "X" if done and " " if undone
     * @return A String that corresponds to the status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // handleMark done task with X
    }

    /**
     * A method that marks a task as done
     */
    public void markAsDone() {
        this.isDone = true; // handleMark tasks as done
    }

    /**
     * A method that marks a task as undone
     */
    public void markAsUndone() {
        this.isDone = false; // handleMark task as undone
    }

    /**
     * Creates a string to be saved in the file
     * @return String to be displayed in the file
     */
    public abstract String savedString();

    /**
     * Creates a String to represent the deadline task during listing
     * @return String to be displayed when list
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
