package maria.task;

/**
 * Represents a Task, which can have many types.
 */
public abstract class Task {

    private final String name;
    private boolean done;

    /**
     * Creates a task.
     * @param name The name of the task
     * @param done If the task is completed
     * @throws TaskNoNameException If the name is empty
     */
    public Task(String name, boolean done) throws TaskNoNameException {

        if (name.isEmpty()) {
            throw new TaskNoNameException("The name for a task cannot be empty.");
        }

        this.name = name;
        this.done = done;
    }

    /**
     * Sets if the task is done
     * @param done If the task is done
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Gets the string representation of the task.
     * @return The string representation of the task
     */
    @Override
    public String toString() {
        return "[" + (this.done ? "X" : " ") + "] " + this.name;
    }

    /**
     * Gets the storage string representation of the task.
     * @return The storage string representation of the task
     */
    public String toStorageString() {
        return this.name + "|||" + this.done;
    }

}
