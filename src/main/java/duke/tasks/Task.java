package duke.tasks;

/**
 * Represents a Task on the task list
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor Method for Task class
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor Method for Task class
     * @param isDone
     * @param description
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status of the task, whether it is done or not, in a string
     * @return X if task is done or " " if task is not
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks current task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks task as not done
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Converts task to String
     * @return string representation of Task Object
     */
    @Override
    public String toString() {
        String s = "[%s] %s";
        return String.format(s, getStatusIcon(), description);
    }

    public abstract String toShortString();
    /**
     * Converts task to String to be stored in a file
     * @return condensed String representation of Task Object
     */
    public String toFile() {
        String s = "%s,%s";
        return String.format(s, getStatusIcon(), description);
    }

    public boolean contains(String s) {
        return description.contains(s);
    }
}
