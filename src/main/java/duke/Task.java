package duke;

/**
 * Encapsulates all tasks objects.
 */
abstract class Task {
    private final String name;
    private boolean isDone = false;

    /**
     * Create a new task.
     * @param name The task to be completed.
     */
    Task(String name) {
        this.name = name;
    }

    /**
     * Create a new task.
     * @param name The task to be completed.
     * @param isDone The status of the task.
     */
    Task(String name, Boolean isDone) {
        this(name);
        this.isDone = isDone;
    }

    String getName() {
        return name;
    }

    void setStatus(boolean b) {
        isDone = b;
    }

    boolean getStatus() {
        return isDone;
    }

    @Override
    public String toString() {
        return name;
    }
}
