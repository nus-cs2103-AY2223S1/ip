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
        return String.format("[D]%s (by: %s)", super.toString(), this.printDate());
    }
}
