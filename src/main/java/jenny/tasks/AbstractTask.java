package jenny.tasks;

/**
 * An abstract class {@code AbstractTask} that serves as a foundation for all other tasks.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public abstract class AbstractTask {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructor of a {@code AbstractTask}.
     *
     * @param description Description of the task.
     */
    public AbstractTask(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the state of the task as a string.
     *
     * @return the state of the task as a string.
     */
    public String icon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the state (boolean) the task.
     *
     * @param isDone the state (boolean) the task.
     */
    public void markAsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a comma delimited {@code String} containing data of the {@code AbstractTask}.
     *
     * @return a comma delimited {@code String}.
     */
    public String save() {
        return String.format("%s,%s,%s",
                this.getClass().getSimpleName(), this.isDone,
                this.description);
    }

    /**
     * Returns the description of the task as a string.
     *
     * @return the description of the task as a string.
     */
    @Override
    public String toString() {
        return "[" + icon() + "] " + this.description;
    }
}
