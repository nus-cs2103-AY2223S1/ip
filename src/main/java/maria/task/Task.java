package maria.task;

/**
 * Represents a Task, which can have many types.
 */
public abstract class Task {

    private final String name;
    private boolean isDone;

    /**
     * Creates a task.
     * @param name The name of the task
     * @param isDone If the task is completed
     * @throws TaskNoNameException If the name is empty
     */
    public Task(String name, boolean isDone) throws TaskNoNameException {

        if (name.isEmpty()) {
            throw new TaskNoNameException("The name for a task cannot be empty.");
        }

        this.name = name;
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean done) {
        this.isDone = done;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Checks if the name of the task contains a string.
     * @param s The string to be checked against
     * @return If the name contains s
     */
    public boolean doesNameContainsString(String s) {
        return this.name.contains(s);
    }

    /**
     * Gets the string representation of the task.
     * @return The string representation of the task
     */
    @Override
    public String toString() {
        return this.name + " [" + (this.isDone ? "Done" : "Not Done") + "]";
    }

    /**
     * Gets the storage string representation of the task.
     * @return The storage string representation of the task
     */
    public String toStorageString() {
        return this.name + "|||" + this.isDone;
    }

}
