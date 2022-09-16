package hazell.entities;

import hazell.exceptions.TaskDescriptionEmpty;

/**
 * A task that can be done anytime.
 */
public class ToDo extends Task {
    private static final String typeIcon = "T";

    protected ToDo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Creates a Todo task object.
     *
     * @param description The description of the Todo
     * @return A new Todo
     * @throws TaskDescriptionEmpty If the description is empty
     */
    public static ToDo create(String description) throws TaskDescriptionEmpty {
        ToDo todo = new ToDo(false, description);
        todo.validate();
        return todo;
    }


    @Override
    public String serialise() {
        return String.format("%s | %s | %s",
                typeIcon,
                this.getIsDone() ? 1 : 0,
                this.getDescription());
    }

    /**
     * Recreates a Todo object from a string.
     *
     * @param words An array of words in which the original Todo was serialised into
     * @return The unserialised Todo object
     */
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
