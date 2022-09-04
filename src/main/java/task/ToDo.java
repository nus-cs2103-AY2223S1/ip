package task;

/**
 * A ToDo is a type of task that only has a description.
 */
public class ToDo extends Task {

    /**
     * Create new ToDo
     * @param description description of todo
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Create new ToDo
     * @param description description of todo
     * @param isDone boolean that is true when todo is marked done otherwise it is false
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getSaveFormat() {
        return "T" + " | " + (getIsDone() ? 1 : 0) + " | " + getDescription() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
