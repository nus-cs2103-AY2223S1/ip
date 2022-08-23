package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public String getStorageString() {
        return "D | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | " + this.by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
