package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline object.
 * A Deadline object supports a description and a by-date.
 */
public class Deadline extends Task implements Dated {

    private LocalDate date;

    /**
     * Constructs a Deadline object.
     * @param item description
     * @param dateString by-date in String
     */
    public Deadline(String item, String dateString) {
        super(item);
        this.date = LocalDate.parse(dateString);
    }

    private String dateFormat(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public boolean isBetween(LocalDate start, LocalDate end) {
        return (this.date.isAfter(start) && this.date.isBefore(end));
    }

    @Override
    public boolean isDated() {
        return true;
    }

    @Override
    public String toStoredString() {
        return "D/" + super.toStoredString() + "/" + date.toString();
    }

    /**
     * Returns String representation of Deadline object.
     * @return String representation
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " + dateFormat(date);
    }
}
