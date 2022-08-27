package task;

import java.time.LocalDateTime;

import parser.DateTimeParser;

/**
 * <h1>Deadline class</h1>
 * Task that the user has inputted and is required to do
 * that has a Deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadlineDateTime;

    /**
     * Creates a Deadline object.
     *
     * @param description describes the Task.
     * @param isDone boolean value describing whether the user has done
     *               the Task or not.
     * @param deadlineDateTime the actual date and time of the deadline that
     *                         the Task is to be completed by.
     */
    public Deadline(String description, boolean isDone, LocalDateTime deadlineDateTime) {
        super(description, isDone);
        this.deadlineDateTime = deadlineDateTime;
    }

    /**
     * Returns the String representation of the Deadline.
     *
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.changeDateTimeFormat(deadlineDateTime) + ")";
    }
}
