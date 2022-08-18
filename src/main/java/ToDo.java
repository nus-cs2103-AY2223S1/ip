import exceptions.TaskDescriptionEmpty;

public class ToDo extends Task {
    private static final String typeIcon = "T";

    public ToDo(String description) throws TaskDescriptionEmpty {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", typeIcon, super.toString());
    }
}
