package duke.task;

/**
 * Represents a task without any date and time attached to it.
 */
public class ToDo extends Task {

    /**
     * Constructs a task that needs to be done.
     *
     * @param description A brief description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String fileDescription() {
        return "T | " + super.fileDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
