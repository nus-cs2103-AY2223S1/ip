package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a task that is due by a specific date
 *
 * @author Eugene Tan
 */
public class Deadline extends Task {

    protected LocalDate returnBy;

    /**
     * Constructor for Deadline.
     *
     * @param description Deadline description.
     * @param returnBy The due date.
     */
    public Deadline(String description, LocalDate returnBy) {
        this.description = description;
        this.isDone = false;
        this.returnBy = returnBy;
    }

    /**
     * Constructor for Deadline.
     *
     * @param description Deadline description.
     * @param isDone Whether deadline task has been done.
     * @param returnBy The due date.
     */
    public Deadline(String description, boolean isDone, LocalDate returnBy) {
        this.description = description;
        this.isDone = isDone;
        this.returnBy = returnBy;
    }

    /**
     * Converts the Deadline to data to be saved.
     *
     * @return String representing deadline to be saved.
     */
    @Override
    public String saveStringFormat() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.description,
                this.returnBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Returns String representation of the deadline.
     *
     * @return String of the deadline.
     */
    @Override
    public String toString() {
        return "[D] " + "[" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.returnBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";

    }
}
