package duke.task;

/**
 * Represents a to-do task.
 */
public class ToDos extends Task {

    /**
     * Constructs a new todo task with given description.
     *
     * @param description of the task.
     */
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toMemoryString() {
        return "T"
                + " | "
                + (this.isDone() ? "1" : "0")
                +  " | "
                + this.getName();
    }
}
