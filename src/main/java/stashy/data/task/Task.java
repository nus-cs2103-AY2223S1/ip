package stashy.data.task;

/**
 * Represents a task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor method.
     *
     * @param description The description of the task
     * @param isDone The status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Overloaded constructor method.
     *
     * @param description The description of the event
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Gives the status icon, [X] or [ ].
     *
     * @return The status icon
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks this task as not done.
     */
    public void unmarkAsNotDone() {
        this.isDone = false;
    }

    /**
     * Checks if the task description contains a particular text
     *
     * @param text The text of interest
     * @return A true or false boolean
     */
    public boolean containsText(String text) {
        return this.description.contains(text);
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}