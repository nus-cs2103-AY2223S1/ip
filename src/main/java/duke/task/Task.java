package duke.task;

/**
 * A task stores its state and description.
 */
public class Task {
    private static final char MARKER_DONE = 'X';
    private static final char MARKER_NOT_DONE = ' ';
    private String name;
    private boolean isDone;

    /**
     * Constructs a task which is not done.
     *
     * @param name Name of task.
     */
    public Task(String name) {
        this(name, false);
        assert name != null;
    }

    /**
     * Constructs a task.
     *
     * @param name Name of task.
     * @param isDone True if and only if the task is done.
     */
    public Task(String name, boolean isDone) {
        assert name != null;
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Checks if the task description matches the query.
     *
     * @param query Query for the task to check.
     * @return True if the task description matches the query.
     */
    public boolean isMatchingQuery(String query) {
        return name.contains(query);
    }

    private char getDoneMarker() {
        return isDone ? MARKER_DONE : MARKER_NOT_DONE;
    }

    /**
     * Gets the task description.
     *
     * @return Task description.
     */
    public String getDescription() {
        return name;
    }

    /**
     * Gets if the task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isTaskDone() {
        return isDone;
    }

    /**
     * Creates a string representation suitable for printing to screen.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return String.format("[%c] %s", getDoneMarker(), name);
    }

    /**
     * Creates a string array representation suitable for printing to files.
     *
     * @return String array representation.
     */
    public String[] getAsStringArray() {
        return new String[]{ "Task", name, String.valueOf(isDone) };
    }
}
