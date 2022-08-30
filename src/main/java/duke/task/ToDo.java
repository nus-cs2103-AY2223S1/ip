package duke.task;

/**
 * ToDo task with description.
 */
public class ToDo extends Task {
    /**
     * Creates the ToDo task
     *
     * @param description description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a ToDo from its encoded form in the saved file.
     *
     * @param encoded encoded string from the file
     * @param completed Whether the task has been completed.
     * @return The created ToDo.
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
