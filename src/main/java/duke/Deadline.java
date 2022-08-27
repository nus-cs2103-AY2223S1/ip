package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task that has a due date and time.
 * @author Justin Cheng.
 */
public class Deadline extends Task {
    protected LocalDate by;
    protected LocalTime time;

    /**
     * Constructor for the Deadline class.
     * @param description The description of the task.
     * @param isDone The boolean value of whether the task is done.
     * @param by The due date given in String.
     * @param time The due time given in String.
     */
    public Deadline(String description, boolean isDone, String by, String time) {
        super(description, isDone);
        this.by = LocalDate.parse(by);
        this.time = LocalTime.parse(time);
    }

    /**
     * Returns the String representation of the Deadline task.
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "D | " + this.getStatusIcon() + " | " + this.getDescription() + " | " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.time.format(DateTimeFormatter.ofPattern("hhmma"));
    }
}
