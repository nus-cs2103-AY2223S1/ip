package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDate by;

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    public String toFileFormat() {
        String isDone = this.isDone ? "1" : "0";
        return "D | " + isDone + " | " + this.description + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline tmp = (Deadline) obj;
            return super.equals(tmp) && this.by.equals(tmp.by);
        }
        return false;
    }

}
