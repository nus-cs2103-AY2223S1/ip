package seedu.duke;

/**
 * Represents a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task.
     * @param description description of what task is about.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an X in string representation if the task is marked done.
     * @return X in string representation.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns description of Task object.
     * @return description of Task object.
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Returns a boolean indicating whether the Task object is marked done.
     * @return boolean.
     */
    public boolean getIsDone(){
        return this.isDone;
    }

    /**
     * Marks Task object as done.
     */
    public void mark(){
        this.isDone = true;
    }

    /**
     * Marks Task object as undone.
     */
    public void unmark(){
        this.isDone = false;
    }

    public String toString() {
        String output = String.format("[%s] %s", this.getStatusIcon(), this.description);
        return output;
    }
}