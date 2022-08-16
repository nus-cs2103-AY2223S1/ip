/**
 * A task stores the state and description of a task.
 */
public class Task {
    private static final char MARKER_DONE = 'X';
    private static final char MARKER_NOT_DONE = ' ';
    private String name;
    private boolean done;
    Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }
    public void markAsNotDone() {
        this.done = false;
    }

    private char getDoneMarker() {
        return this.done ? MARKER_DONE : MARKER_NOT_DONE;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", getDoneMarker(), name);
    }
}
