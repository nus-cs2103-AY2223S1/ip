package dukepro.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for Deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a Deadline.
     *
     * @param description The description of the task.
     * @param by Due date in string format.
     * @return A Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        by = by.stripLeading();
        LocalDate ld = LocalDate.parse(by);
        this.by = ld;
    }

    /**
     * Returns String version of a deadline.
     *
     * @return A String version of this object.
     */
    @Override
    public String toString() {
        String form = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + form + ")";
    }

    /**
     * Returns String to be saved to data/tasklist.txt.
     *
     * @return A String in specific format.
     */
    @Override
    public String fileForm() {
        return "D" + "," + super.fileForm() + "," + this.by;
    }

    /**
     * Returns if the localdate of this task matches
     * the localdate in the param.
     *
     * @param localDate the localdate to be compared to.
     * @return A boolean.
     */
    @Override
    public boolean compareDate(LocalDate localDate) {
        return localDate.equals(this.by);
    }
}
