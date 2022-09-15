package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task of type Deadline.
 */
public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Creates a Deadline task.
     *
     * @param description Description of the Deadline task.
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Gets the string representation of the Deadline task.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        String date = this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String time = this.time.toString();
        return "[D]" + super.toString() + " (by: " + date + " " + time + ")";
    }

    /**
     * Sets the Deadline task's date using string representation of date.
     *
     * @param date String representing the date to be set.
     */
    public void setDate(String date) {
        this.date = LocalDate.parse(date);
    }

    /**
     * Sets the Deadline task's date using date in the form of LocalDate.
     *
     * @param date LocalDate representing the date to be set.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the Deadline task's time.
     *
     * @param time String representing the time to be set.
     */
    public void setTime(String time) {
        this.time = LocalTime.parse(time);
    }
}
