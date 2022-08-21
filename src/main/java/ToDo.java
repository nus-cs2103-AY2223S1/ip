import exceptions.TaskDescriptionEmpty;

public class ToDo extends Task {
    private static final String typeIcon = "T";

    protected ToDo(boolean isDone, String description) {
        super(isDone, description);
    }

    public static ToDo create(String description) throws TaskDescriptionEmpty {
        ToDo todo = new ToDo(false, description);
        todo.validate();
        return todo;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", typeIcon, super.toString());
    }
}
