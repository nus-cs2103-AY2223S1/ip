package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task containing a string description and a due date.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Initializes an instance of an uncompleted Deadline that has the specified description and due date.
     *
     * @param taskDescription Specified description of the deadline.
     * @param by Due date of deadline.
     */
    public Deadline(String taskDescription, LocalDate by) {
        super(taskDescription);
        this.by = by;
    }

    /**
     * Initializes an instance of Deadline that has the specified description, due date, and state of completion.
     *
     * @param taskDescription Specified description of the deadline.
     * @param by Due date of deadline.
     * @param isDone Indicates the state of completion the deadline.
     */
    public Deadline(String taskDescription, LocalDate by, Boolean isDone) {
        super(taskDescription);
        this.by = by;
        this.isDoneSetter(isDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getTypeIcon() {
        return "[D]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toStorageString() {
        return "D" + "|" + super.toStorageString() + "|" + by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
