package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
