package task;

/**
 * Represents a task that is stored and processed by the chatbot
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Task constructor with the specified description.
     *
     * @param description a {@link String} of the description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Task constructor with the specified description and isDone.
     *
     * @param isDone a {@link String} indicating if the task has been marked as done
     * @param description a {@link String} indicating the task description
     */
    public Task(String isDone, String description) {
        this.isDone = isDone.equals("X");
        this.description = description;
    }

    /**
     * Returns isDone in a {@link String} representation
     *
     * @return String a String indicating if the task has been marked as done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Changes isDone to true
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Changes isDone to false
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns a {@link String} representation of a task
     *
     * @return String
     */
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns a {@link String} representation of a task
     *
     * @return String
     */
    public String toTxt() {
        return String.format("%s @@ %s", getStatusIcon(), description);
    }
}
