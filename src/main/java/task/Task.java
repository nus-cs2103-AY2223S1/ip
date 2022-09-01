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
     * Returns {@code isDone} in a {@link String} representation
     *
     * @return {@link String} a {@link String} indicating if the task has been marked as done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Changes {@code isDone} to {@code true} and returns task string representation
     *
     * @return {@link String} representation of the task
     */
    public String markAsDone() {
        isDone = true;

        return toString();
    }

    /**
     * Changes {@code isDone} to {@code false} and returns task string representation
     *
     * @return {@link String} representation of the task
     */
    public String markAsUndone() {
        isDone = false;

        return toString();
    }

    /**
     * Returns a {@code boolean} indicating if the task has the specified keyword
     *
     * @param keyword a {@link String} that wants to be searched
     * @return a {@link boolean}
     */
    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Returns a {@link String} representation of a task
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns a {@link String} representation of a task
     *
     * @return {@link String}
     */
    public String toTxt() {
        return String.format("%s @@ %s", getStatusIcon(), description);
    }
}
