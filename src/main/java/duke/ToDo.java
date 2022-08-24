package duke;

/**
 * Encapsulate Todo which is-a Task.
 */
public class ToDo extends Task{

    /**
     * Class constructor for Todo.
     *
     * @param description Todo's description.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        return "T" + super.toStorageString();
    }
}
