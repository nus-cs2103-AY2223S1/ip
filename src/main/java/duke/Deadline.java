package duke;

import java.time.format.DateTimeFormatter;

/** A class that creates deadline task. */
public class Deadline extends Task {
    protected String by;

    /**
     * A constructor that initialises the deadline task.
     *
     * @param description Description of the deadline task.
     * @param by Describes the date and time for the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns string of the deadline task.
     *
     * @inheritDoc Inherits from toString() method but returns description of deadline task.
     * @return String that describes the deadline task and its deadline.
     */
    @Override
    public String toString() {
        if (by.contains("/") || by.contains("-")) {
            String date = super.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            String time = super.time.toString();
            return "[D]" + super.toString() + " (by: " + date + " " + time + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }
}
