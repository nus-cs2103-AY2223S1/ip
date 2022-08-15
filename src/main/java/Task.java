/**
 * This class encapsulates a task created in the Chatbot.
 */
public class Task {

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
     * Toggles the isDone flag on or off.
     */
    public void toggleDone() {
        this.isDone = !this.isDone;
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
}
