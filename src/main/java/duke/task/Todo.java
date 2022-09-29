package duke.task;

/**
 * A class to represent a to-do item.
 */
public class Todo extends Task {
    /**
     * Constructs a to-do item.
     *
     * @param description the description of the to-do item.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the description of the task, with a to-do tag.
     *
     * @return Description of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
