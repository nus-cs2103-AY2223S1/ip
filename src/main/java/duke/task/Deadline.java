package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

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

    /**
     * Creates a deadline object with manually set priority upon receiving a deadline command from the user.
     *
     * @param description The description of the deadline.
     * @param by The due date of the deadline.
     * @param time The time the deadline is due, if applicable.
     * @param type The type of task created.
     */
    public Deadline(String description, LocalDate by, LocalTime time, TaskType type, Priority priority) {
        super(description, type, priority);
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
            return by + " | " + super.getDescription();
        } else {
            return by + " " + time + " | " + super.getDescription();
        }
    }

    @Override
    public String toString() {
        String str = "";
        if (time != null) {
            str = time.format(timeFormatter) + " ";
        }
        str += by.format(dateFormatter);
        return "[D]" + super.toString() + " (by: " + str + ")";
    }
}
