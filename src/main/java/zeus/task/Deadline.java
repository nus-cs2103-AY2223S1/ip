package zeus.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that inherits from Task.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate date;

    /**
     * Constructor of Deadline class.
     *
     * @param description the description of the deadline task
     * @param by          a String that represents date of deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor of Deadline class with datetime.
     *
     * @param description the description of the deadline task
     * @param date          a LocalDate that represents date of deadline
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Copy constructor of Deadline class.
     *
     * @param deadline Deadline to copy
     */
    public Deadline(Deadline deadline) {
        super(deadline.getDescription(), deadline.isDone);
        this.by = deadline.by;
        this.date = deadline.date;
    }

    /**
     * Returns Deadline as a String formatted to be a line in file.
     *
     * @return String representing deadline.
     */
    @Override
    public String getFileFormat() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, this.by);
    }

    /**
     * Returns Task that represents this Deadline to avoid casting in the copy constructor.
     *
     * @return Task that represents this Deadline
     */
    @Override
    public Task copy() {
        return new Deadline(this);
    }

    /**
     * Returns a String that represents the Deadline.
     *
     * @return a String that represents the Deadline
     */
    @Override
    public String toString() {
        if (this.date == null) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            String d = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[D]" + super.toString() + " (by: " + d + ")";
        }
    }
}
