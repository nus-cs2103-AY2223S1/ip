package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    protected LocalTime time;

    public Deadline(String description, String[] msg) {
        super(description);
        this.by = LocalDate.parse(msg[1]);
        this.time = LocalTime.parse(msg[2]);
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.getDescription() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.time.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
    }
}
