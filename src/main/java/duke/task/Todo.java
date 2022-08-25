package duke.task;

import java.time.LocalDate;

/**
 * The Todo class represents a task
 * without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo with a specified description.
     *
     * @param description A string specifying the description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the representation of a Todo task when stored in a data file on the hard disk.
     *
     * @return a string representing the Todo task as it is stored on a data file on the hard disk.
     */
    @Override
    public String toData() {
        return "T | " + super.toData() + "\n";
    }

    /**
     * Checks if the Todo task should be done on a specific date.
     *
     * @param date The specified date to check.
     * @return false.
     */
    @Override
    public boolean onDate(LocalDate date) {
        return false;
    }
}
