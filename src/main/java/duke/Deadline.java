package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    /**
     * Deadline object by field which indicates the deadline
     */
    protected LocalDate by;

    /**
     * A constructor to intialize the Deadline object with the description and deadline
     * @param description The task
     * @param by The deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string that represents the Deadline
     * @return String A string that represents the current object
     */
    @Override
    public String toString() {
        String date = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D][" + this.getStatusIcon() + "] " + super.toString() + " (by: " + date + ")";
    }

    /**
     * Returns a string that represents the Deadline in the format for the textfile
     * @return String A string that represents the current object
     */
    @Override
    public String toFileString() {
        String date = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "D | " +  this.getFileStatus() + " | " + super.toString() + " | " + date;
    }

}

