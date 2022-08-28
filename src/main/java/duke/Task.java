package duke;

/**
 * Represents the superclass of Deadline, Event and ToDo
 * classes.
 * @author Justin Cheng.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     * @param description The description of the Task.
     * @param isDone The boolean value of whether the Task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Toggles boolean value isDone to be true.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Toggles boolean value isDone to be false.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the String representation of whether
     * the Task is done or not.
     * @return String representation of Task's completion.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "O");
    }

    /**
     * Returns the description of the Task.
     * @return description in String.
     */
    public String getDescription() {
        return this.description;
    }
}
