package task;

/**
 * Represents tasks in the task list.
 */
public abstract class Task {
    private final String name;
    private boolean isDone;

    /**
     * Constructs an unmarked {@link Task} object.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructs a marked or an unmarked {@link Task} object.
     *
     * @param name Name of the task.
     * @param isDone The marked status of the task.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Represents the object as a string.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatus(), this.getName());
    }

    /**
     * Describes the object in a specific format for saving it to the text file.
     *
     * @return String representation of the object.
     */
    public String toFileString() {
        return String.format("%s||%s||%s", this.getType(), this.getBooleanStatus(), this.getName());
    }

    /**
     * Gets the task name.
     *
     * @return Task name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the marked status of the task.
     *
     * @return Task mark status.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Gets the task time.
     *
     * @return Task time.
     */
    public abstract String getTime();

    /**
     * Gets the task type.
     *
     * @return Task type = ("T", "D", "E").
     */
    public abstract String getType();

    /**
     * Turns the {@code isDone} status into a string representation.
     *
     * @return X if done, empty string if not done.
     */
    public String getStatus() {
        return this.isDone() ? "X" : " ";
    }

    /**
     * Turns the {@code isDone} status into a string representation.
     *
     * @return true if done, false if not done.
     */
    public String getBooleanStatus() {
        return this.isDone() ? "true" : "false";
    }

    /**
     * Marks the object as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the object as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if the given keyword is in the name of the object.
     *
     * @param keyword The string keyword being checked.
     * @return The truth value of the check.
     */
    public boolean match(String keyword) {
        return this.getName().contains(keyword.trim());
    }
}
