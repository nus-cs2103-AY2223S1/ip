package duke;

/**
 * Represents a Todo Task.
 * @author Tan Wen Cong
 */
public class Todo extends Task {
    /**
     * Constructor for Todo
     *
     * @param description Description of the Todo
     * @param isDone      boolean indicating if the Todo is done
     */
    public Todo(String description, boolean isDone) {
        super(description, "T", isDone);
    }

    /**
     * Return String representation of Todo to be saved in Txt file
     *
     * @return String representation of Todo to be saved in Txt file
     */
    @Override
    public String getTxtString() {
        return String.format("todo %s", super.getTxtString());
    }
}
