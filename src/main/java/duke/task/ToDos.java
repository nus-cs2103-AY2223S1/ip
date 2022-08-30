package duke.task;

/**
 * Represents a ToDo task. A ToDo object contains the description of task.
 */
public class ToDos extends Task {

    /**
     * Constructor for ToDos
     * @param description String describing todo.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * @return String to save onto text document.
     */
    @Override
    public String textFormat() {
        return "T|" + (isDone ? 1 : 0) + "|" + description;
    }

    /**
     * @return String to be displayed to users.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
