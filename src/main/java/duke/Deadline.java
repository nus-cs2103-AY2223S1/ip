package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Dealine Class to represent a class that will store Deadline object that extends from Tasks Class
 * @author amresh A0235398R
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Method to convert deadline String to LocalDate format
     *
     * @return boolean true value if converted
     */
    public boolean convertToDate() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(this.by, formatter);
            this.date = localDate;
            this.by = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;
            return super.equals(deadline) && this.by.equals(deadline.by);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}