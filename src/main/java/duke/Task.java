package duke;

/**
 * This class is superclass of all task objects that
 * can be initialized in Duke.
 */
public class Task {
    /** The title or description of a task. */
    protected String description;

    /** Indicates if a task is completed or not. */
    protected boolean isDone;

    /**
     * The class constructor for a Task.
     * @param description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Outputs the corresponding status icon via a ternary
     * operator, according to the isDone field.
     *
     * @return String "X" if completed, "O" if otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "O"); // mark done task with X
    }

    /**
     * Sets the isDone field of a Task to true and outputs the
     * corresponding formatted text with the task description.
     *
     * @return String to indicate the task has been marked done.
     */
    public String markAsDone() {
        this.isDone = true; // mark task as true
        String result = String.format("Nice! I've marked this task as done:\n%s", this.toString());
        return result;
    }

    /**
     * Sets the isDone field of a loaded Task to be true. Only used for loading
     * tasks from a file. No output to prevent spamming the console.
     */
    public void loadDone() {
        this.isDone = true; // mark task as true
    }

    /**
     * Returns the description of a task.
     *
     * @return description of task.
     */
    public String getDescription() {
        return this.description; // get description of task
    }

    /**
     * Returns the formatted string representation of a Task via
     * getStatusIcon() and getDescription().
     *
     * @return formatted string representation of a Task.
     */
    public String toString() {
        String outputString = String.format("[%s] %s", getStatusIcon(), getDescription());
        return outputString;
    }

    /**
     * Returns a string representation of an Task object formatted
     * for writing into text file.
     *
     * @return String of the Event formatted to saved.
     */
    public String formatFileText() {
        String s = String.format("duke.Task | %s | %s", this.getStatusIcon(), this.getDescription());
        return s;
    }
}
