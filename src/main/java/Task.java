/**
 * Tasks are added by the user for KKBot to store and keep track of.
 *
 * @author AkkFiros
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a task
     * @param description Description of the task that the user inputs
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Obtains and returns the task description
     * @return Task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Obtains and returns the completion status of the task
     * @return The completion status
     */
    public boolean getCompletion() {
        return this.isDone;
    }

    /**
     * Obtains and returns the icon representation of the completion status of the task
     * @return the icon representing the completion status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Sets a task to complete
     */
    public void setComplete() {
        isDone = true;
        System.out.println("Woohoo! You completed a task:\n" + getStatusIcon() + " " + getDescription());
    }

    /**
     * Sets a task to incomplete
     */
    public void setIncomplete() {
        isDone = false;
        System.out.println("Trynna cheat the system are we? Well, no you, task marked as incomplete:\n"
                + getStatusIcon() + " " + getDescription());
    }

    /**
     * toString method for a task when KKBot is listing out stored tasks
     * @return string representation of a task stored.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
