/**
 * A TaskItem to complete.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class TaskItem {
    private final String description;
    private boolean isDone;

    /**
     * Constructor for TaskItem.
     * @param description description of the TaskItem.
     */
    public TaskItem(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the state of the TaskItem as a String.
     * @return state of the TaskItem as a String.
     */
    public String icon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the state the TaskItem.
     * @param isDone state of the TaskItem.
     */
    public void isDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the description of the TaskItem as a String.
     * @return description of the TaskItem as a String.
     */
    @Override
    public String toString() {
        return "[" + icon() + "]" + this.description;
    }
}
