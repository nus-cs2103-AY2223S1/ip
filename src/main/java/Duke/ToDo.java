package duke;

/**
 * ToDo class to represent tasks to be done as soon as possible.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     * @param description description of ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toWrite() {
        return String.format("T~%s~%s", (isDone ? "1" : "0"), description.trim());
    }
}
