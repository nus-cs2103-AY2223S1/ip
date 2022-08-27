package hazell;

import hazell.exceptions.TaskDescriptionEmpty;

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

    public String serialise() {
        return String.format("%s | %s | %s",
                typeIcon,
                this.getIsDone() ? 1 : 0,
                this.getDescription());
    }

    public static ToDo unserialise(String[] words) {
        return new ToDo(
                words[1].equals("1"),
                words[2]);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", typeIcon, super.toString());
    }
}
