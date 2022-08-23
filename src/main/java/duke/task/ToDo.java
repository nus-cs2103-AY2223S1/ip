package duke.task;

/**
 * A Task with a description and completion status.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a ToDo from its storage string representation/
     * @param encoded the storage string representation of the ToDo
     * @param completed the completion status of the todo.
     * @return the created ToDo.
     */
    public static ToDo decode(String encoded, boolean completed) {
        ToDo todo = new ToDo(encoded);
        todo.setDone(completed);
        return todo;
    }

    @Override
    public Type getType() {
        return Type.TODO;
    }

    @Override
    public String encodeData() {
        return this.description;
    }

    @Override
    public String getDisplayText() {
        return this.description;
    }
}
