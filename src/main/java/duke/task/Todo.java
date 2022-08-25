package duke.task;

public class Todo extends Task {
    /**
     * Creates a new Todo.
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Outputs as a String to be printed by UI.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Outputs as a String to be saved by Storage.
     */
    @Override
    public String getSaveFormat() {
        return "T | " + super.getSaveFormat();
    }
}
