package duke.task;

/**
 * Todo is a Task without any date/time attached.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for Todo with information on whether it is completed.
     *
     * @param description Description of the Todo.
     * @param isDone Whether the Todo is completed.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns the String representation of the Todo.
     *
     * @return String detailing the Todo.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Converts the Todo to data to be saved.
     *
     * @return Data representing the Todo.
     */
    @Override
    public String saveTask() {
        return String.format("T | %s", super.saveTask());
    }
}
