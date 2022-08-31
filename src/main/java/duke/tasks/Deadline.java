package duke.tasks;

import java.time.LocalDate;

import duke.tools.Parser;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
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
        String isDone;
        if (super.isDone) {
            isDone = "O";
        } else {
            isDone = "X";
        }
        return String.format("D | %s | %s | %s\n", isDone, super.description,
                Parser.formatDateToData(date));
    }

    /**
     * Returns string representation of deadline task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                Parser.formatDateToPrint(date));
    }
}
