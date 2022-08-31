package seedu.duke;

import java.time.LocalDateTime;

/**
 * Represents a task which has the description and the due date.
 */
public class Deadline extends Task {

    /** The due date of the task*/
    protected LocalDateTime by;

    /**
     * A constructor for Deadline object.
     *
     * @param description the name of the task.
     * @param by the due date of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        int year = Integer.parseInt(by.substring(0, 4));
        int month = Integer.parseInt(by.substring(5, 7));
        int day = Integer.parseInt(by.substring(8, 10));
        int hours = Integer.parseInt(by.substring(11, 13));
        int minutes = Integer.parseInt(by.substring(13, 15));

        LocalDateTime date = LocalDateTime.of(year, month, day, hours, minutes);
        this.by = date;
    }

    /**
     * A constructor for Deadline object which takes in a description and a LocalDateTime object.
     *
     * @param description the name of the task.
     * @param date the due date of the task.
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.by = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.getMonth() + "-" + by.getDayOfMonth() + "-" + by.getYear()
            + " " + String.format("%02d", by.getHour()) + ":" + String.format("%02d", by.getMinute()) + ")";
    }
}
