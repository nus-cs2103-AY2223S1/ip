package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a deadline Class
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * Constructs a deadline class
     * @param description The description of the deadline
     * @param date The date of the deadline
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns boolean value indicating if this deadline will be over before a certain day
     * @param date the date being queried
     * @return Whether the deadline has passed
     */
    public boolean byThisDate(LocalDate date) {
        return this.date.isBefore(date);
    }

    /**
     * Formats the description and date to be printed in the commandline
     * @return The formatted description and date
     */
    public String toStringDate() {
        return super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the parsed version of the deadline to be stored in the text file.
     * @return the parsed deadline
     */
    @Override
    public String parseTask() {
        return "D" + super.parseTask() + "/" + this.date;
    }

    /**
     * Returns the string representation of the Deadline
     * @return The string representation of the Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }


}
