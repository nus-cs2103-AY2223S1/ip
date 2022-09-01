package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is to be done by a certain date and time.
 */
public class Deadline extends Task {
    protected LocalDate by;
    protected LocalTime time;

    /**
     * Creates a deadline object upon receiving a deadline command from the user.
     *
     * @param description The description of the deadline.
     * @param by The due date of the deadline.
     * @param time The time the deadline is due, if applicable.
     * @param type The type of task created.
     */
    public Deadline(String description, LocalDate by, LocalTime time, TaskType type) {
        super(description, type);
        this.by = by;
        this.time = time;
    }

    @Override
    public boolean isDateEqual(LocalDate date) {
        return by.isEqual(date);
    }

    /**
     * Returns the description of a deadline with its date for writing purposes.
     *
     * @return The string containing the description.
     */
    @Override
    public String getDescription() {
        if (time == null) {
            return super.getDescription() + " | " + by;
        } else {
            return super.getDescription() + " | " + by + " " + time;
        }
    }

    @Override
    public String toString() {
        String str = "";
        if (time != null) {
            str = time.format(DateTimeFormatter.ofPattern("HH:mm "));
        }
        str += by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + "(by: " + str + ")";
    }
}
