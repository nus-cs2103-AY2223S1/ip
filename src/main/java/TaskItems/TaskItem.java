package TaskItems;

/**
 * An abstract class TaskItem that serves as a foundation for all other Tasks.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public abstract class TaskItem {
    private final String description;
    private boolean isDone;

    /**
     * Constructor of a TaskItem.
     * @param description description of the task.
     */
    public TaskItem(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the state of the task as a string.
     * @return the state of the task as a string.
     */
    public String icon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the state (boolean) the task.
     * @param isDone the state (boolean) the task.
     */
    public void isDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task as a string.
     * @return the description of the task as a string.
     */
    @Override
    public String toString() {
        return "[" + icon() + "] " + this.description;
    }
}
