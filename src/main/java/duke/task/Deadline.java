package duke.task;

import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /**
     * Initialises Deadline object with specified description and date.
     *
     * @param description Description for Deadline object
     * @param by          Date the task is due
     */
    public Deadline(String description, String by) {
        super(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}