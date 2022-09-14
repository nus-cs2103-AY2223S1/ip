package duke.tasks;

import java.time.LocalDate;

import duke.commons.Parser;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    public static final String TASK_ICON = "D";
    /** Due date of deadline */
    private LocalDate date;

    /**
     * Constructs a deadline with specified description and due date.
     *
     * @param description Description of deadline.
     * @param date Due date of deadline.
     */
    public Deadline(String description, Boolean isDone, LocalDate date) {
        super(description);
        if (isDone) {
            super.markAsDone();
        }
        this.date = date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String taskToDataString() {
        String deadlineDataFormat = TASK_ICON + " | %s | %s | %s\n";
        return String.format(deadlineDataFormat, this.isDoneToDataString(), super.description,
                Parser.formatDateToData(date));
    }

    /**
     * Updates the deadline with a new date.
     *
     * @param newDate New date to be updated.
     */
    public void updateDate(LocalDate newDate) {
        date = newDate;
    }

    /**
     * Returns string representation of deadline task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        String deadlineStringFormat = "[" + TASK_ICON + "]%s (by: %s)";
        return String.format(deadlineStringFormat, super.toString(),
                Parser.formatDateToMessage(date));
    }
}
