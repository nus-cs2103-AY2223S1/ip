package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

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
