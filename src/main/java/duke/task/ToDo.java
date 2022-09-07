package duke.task;

import java.time.LocalDate;

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
    public boolean isOn(LocalDate searchDate) {
        return false;
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
