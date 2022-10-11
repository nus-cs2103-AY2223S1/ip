package duke;

/**
 * A class that represents all tasks.
 *
 * @author Safwan A0235287X
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor that creates a Task object.
     * @param description Details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method that checks if a tasks is done.
     * @return A string of a notation on the task description.
     */
    public String getStatusIcon() {
      return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Method to mark a task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Method to mark a task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Method that overrides Java.toString() method to convert the Task
     * object to a string.
     * @return A string of the Task object.
     */
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
