package duke.task;

/**
 * A task stores the state and description of a task.
 */
public class Task {
    private static final char MARKER_DONE = 'X';
    private static final char MARKER_NOT_DONE = ' ';
    private String name;
    private boolean done;
    Task(String name) {
        this(name, false);
    }

    Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.done = false;
    }

    private char getDoneMarker() {
        return this.done ? MARKER_DONE : MARKER_NOT_DONE;
    }

    /**
     * Creates a string representation suitable for printing to screen.
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return String.format("[%c] %s", getDoneMarker(), name);
    }

    /**
     * Creates a string array representation suitable for printing to files.
     * @return String array representation.
     */
    public String[] getAsStringArray() {
        return new String[]{ "Task", name, String.valueOf(done) };
    }
}
