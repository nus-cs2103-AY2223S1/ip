package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has to be done by a certain date and/or time.
 */
public class Deadline extends Task {
    protected String by = "";
    protected LocalDate byDate;
    protected LocalTime byTime;

    /**
     * Constructor for the Deadline class.
     * @param description A string that provides information for the task.
     * @param by A string that provides the deadline that the task has to be completed by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        dateTimeConverter(by);
    }

    /**
     * Another constructor for the Deadline class.
     * @param description A string that provides information for the task.
     * @param byDate A LocalDate object that provides information about the date that the task has to be completed by.
     * @param byTime A LocalTime object that provides information about the time that the task has to be completed by.
     */
    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    private void dateTimeConverter(String by) {
        if (by.length() > 10) {
            int spacePos = by.indexOf(" ");
            String date = by.substring(0, spacePos);
            String time = by.substring(spacePos + 1);
            this.byDate = LocalDate.parse(date);
            this.byTime = LocalTime.parse(time);
        } else {
            this.byDate = LocalDate.parse(by);
        }
    }

    /**
     * Returns the deadline task as a string.
     * @return The deadline task as a string.
     */
    @Override
    public String toString() {
        return ("D | "
                + super.toString()
                + " | "
                + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ((this.byTime == null)
                    ? ""
                    : " | " + this.byTime.toString()));
    }
}
