import exceptions.TaskDescriptionEmpty;

public class ToDo extends Task {
    private static final String typeIcon = "T";

    protected ToDo(boolean isDone, String description) throws TaskDescriptionEmpty {
        super(isDone, description);
    }

    public static ToDo create(String description) throws TaskDescriptionEmpty {
        return new ToDo(false, description);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", typeIcon, super.toString());
    }
}
