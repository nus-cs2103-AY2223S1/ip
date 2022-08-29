package main.stashy.data.task;

/**
 * One of the task types, specifically those with
 * no specific timing or deadline.
 */
public class ToDo extends Task {

    /**
     * Constructor method.
     *
     * @param description The description of this to-do
     * @param isDone Whether the task is done or not
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Constructor method.
     *
     * @param description The description of this to-do
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}