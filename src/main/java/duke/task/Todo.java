package duke.task;

/**
 * Class for todo task.
 *
 * @author Shawn Chew
 * @version CS2103T AY 22/23 Sem 1
 */
public class Todo extends Task {
    /**
     * A constructor to initialize Todo.
     *
     * @param description The details of what the task is about.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStringToSave() {
        return this.isDone
                ? "T | 1 | " + description
                : "T | 0 | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
