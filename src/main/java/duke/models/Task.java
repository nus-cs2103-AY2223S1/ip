package duke.models;

/**
 * Task class with parameters of description and isDone state
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a Task object with a description
     * @param description A description detailing the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the isDone value of this task to true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the isDone value of this task to false
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon representing the isDone state of the task
     * @return "X" if task is done, " " if task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the string representation of the {@code Task} object
     * @return String representing the {@code Task} object
     */
    public abstract String getSymbol();

    /**
     * Returns the description of the {@code Task} object
     * @return
     */
    public abstract String getDescription();

    /**
     * Returns the string to write to file for the {@code Task} object
     * in a format that is convenient to save and load files
     * @return String to write to the file
     */
    public abstract String stringToWrite();

    /**
     * Returns the string representation of the {@code Task} object
     * @return
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
