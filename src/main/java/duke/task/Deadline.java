package duke.task;

import java.time.LocalDate;

/**
 * Class which inherits the Task class for a Deadline
 *
 * @author kaij77
 * @version 0.1
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Public constructor for a Deadline
     *
     * @param description The description of the Deadline
     * @param by When the Deadline is due
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Public constructor which also takes in an optional note about the Deadline.
     *
     * @param description The description of the deadline
     * @param by When the Deadline is due
     * @param note An optional note about the Deadline
     */
    public Deadline(String description, LocalDate by, String note) {
        super(description, note);
        this.by = by;
    }

    private String printDate() {
        return String.format("%s %d %d",
                this.by.getMonth().toString().substring(0, 3),
                this.by.getDayOfMonth(),
                this.by.getYear());
    }

    @Override
    public String stringifyTask() {
        return String.format("%s | %s | %s", "D", super.stringifyTask(), this.by);
    }

    /**
     * Returns the String representation of the Deadline.
     *
     * @return the String representation of the Deadline
     */
    @Override
    public String toString() {
        if (this.getNote() == null) {
            return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.getDescription(), this.printDate());
        } else {
            assert this.getNote() != null : "This task should have a note";
            return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.getDescription(), this.printDate())
                    + "\n" + String.format("       - Notes: %s", this.getNote());
        }
    }
}
