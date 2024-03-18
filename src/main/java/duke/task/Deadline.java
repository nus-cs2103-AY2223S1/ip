package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Initialises a <code>Deadline</code> task with its description and deadline.
     * @param name The description of the <code>Task</code>.
     * @param tag The tag of the <code>Task</code>.
     * @param by The deadline of the <code>Task</code>.
     */
    public Deadline(String name, String tag,LocalDate by) {
        super(name, tag);
        this.by = by;
    }

    /**
     * Returns the date of the deadline of the <code>Deadline</code>.
     * @return The date of the deadline.
     */
    public String getBy() {
        return this.by.toString();
    }


    /**
     * Returns a String with a different date format.
     * @return A <code>Deadline</code> with a different date format.
     */
    public String changeDateFormat() {
        String updatedDate = this.by.format(DateTimeFormatter.ofPattern("d MMM uuuu"));
        return "[D]" + super.toString()
                + "(by: " + updatedDate + ")";
    }

    /**
     * Returns the description of the <code>Deadline</code> with its deadline.
     * @return The description of the <code>Deadline</code> with its deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "(by: " + by + ")";
    }
}
