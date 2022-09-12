package duke;

/**
 * Task with only name.
 */
public class Todo extends Task {
    private static final String TYPE = "[T]";

    /**
     * Constructor for new todo instance.
     *
     * @param name name of task.
     * @throws MissingDescriptionException missing name of task.
     */
    public Todo(String name) throws MissingDescriptionException {
        super(name);
    }

    /**
     * Returns string representation of task.
     *
     * @return string with type, completed or not, name, and date time(if applicable).
     */
    @Override
    public String toString() {
        String comp = this.isCompleted
                ? "[X]"
                : "[ ]";
        String priorityLevel = this.priority + " priority";
        return TYPE + comp + name + " " + priorityLevel.toLowerCase();
    }

    /**
     * Returns string representation of task to be written in text file.
     *
     * @return string representation to be written in text file.
     */
    @Override
    public String toData() {
        String type = "T";
        String completed = this.isCompleted ? "1" : "0";
        return type + FIELD_DIVIDER + completed + FIELD_DIVIDER + name + FIELD_DIVIDER + priority;
    }
}
