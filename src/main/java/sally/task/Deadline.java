package sally.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Deadline class to represent new Deadline task
 *
 * @author liviamil
 */

public class Deadline extends Task {
    protected String moreInfo;
    protected LocalDate byDate;

    /**
     * Constructor for Deadline class
     *
     * @param description description of Deadline task
     * @param moreInfo due date for Deadline task in String
     */
    public Deadline(String description, String moreInfo) {
        super(description);
        this.moreInfo = moreInfo;
    }

    /**
     * Constructor for Deadline class, specific to LocalDateTime dates
     *
     * @param description description of Deadline task
     * @param byDate due date for Deadline task in LocalDateTime
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Gets the output string for save to file
     *
     * @return output string for save to file
     */
    public String getOutput() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, (byDate == null) ? moreInfo : byDate);
    }

    @Override
    public String toString() {
        if (byDate != null) {
            return "[D]" + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + moreInfo + ")";
        }

    }
}
