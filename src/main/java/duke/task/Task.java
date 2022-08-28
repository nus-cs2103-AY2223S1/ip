package duke.task;

public class Task {
    /** Description of the task */
    private final String description;

    /** Status of the task */
    private boolean isDone;

    /**
     * Creates a new undone task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns a symbol representing whether the task is done.
     * If the task is done, 'X' is returned.
     * If the task is undone, ' ' is returned.
     *
     * @return The status symbol of the task.
     */
    protected char getStatusSymbol() {
        return (isDone ? 'X' : ' '); // mark done task with X
    }

    /**
     * Sets the task's {@code isDone} attribute to {@code true}.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Sets the task's {@code isDone} attribute to {@code false}.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns a {@code char} representing the task type.
     *
     * @return '0'
     */
    public char getType() {
        return '0';
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", getStatusSymbol(), description);
    }
}
