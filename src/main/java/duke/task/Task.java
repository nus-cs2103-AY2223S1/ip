package duke.task;

/**
 * The {@code Task} stores relevant information for a task.
 * It contains the {@link Task#taskName task name}, and a {@link Task#isDone boolean} to indicate if
 * it is done or not.
 */
public abstract class Task {

    final private String taskName;
    private boolean isDone;
    public static final String DONE = "DONE";
    public static final String UNDONE = "UNDONE";

    /**
     * Constructs a task. All task and undone from the start.
     *
     * @param taskName a string representing the name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Constructor for a task specifying if it is already done.
     *
     * @param taskName a string representing the name of the task.
     * @param isDone   a boolean specifying if a task is done.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Returns a string representing the task name.
     *
     * @return a string representing the task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks a task as undone.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Returns a string representing the task.
     *
     * @return a string representing the task.
     */
    @Override
    public String toString() {
        String checkbox = isDone ? "[X] " : "[ ] ";
        return checkbox + taskName;
    }

    /**
     * Returns a string representing the task for storage.
     *
     * @return a string representing the task for storage.
     */
    public String toStorageString() {
        String doneString = isDone ? DONE : UNDONE;
        return doneString + "\n";
    }
}