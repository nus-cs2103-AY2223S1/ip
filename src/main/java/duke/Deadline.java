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
     *  @param description The task
     * @param by The deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String date = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D][" + this.getStatusIcon() + "] " + super.toString() + " (by: " + date + ")";
    }

    @Override
    public String toFileString() {
        String date = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "D | " +  this.getFileStatus() + " | " + super.toString() + " | " + date;
    }

}

