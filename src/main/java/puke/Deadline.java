package puke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String saveFormat() {
        if (this.isDone) {
            return "D | 1 | " + this.description + " | " + this.by;
        } else {
            return "D | 0 | " + this.description + " | " + this.by;
        }
    }
}
