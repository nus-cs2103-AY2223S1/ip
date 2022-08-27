package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    protected LocalTime time;

    public Deadline(String description, boolean isDone, String by, String time) {
        super(description, isDone);
        this.by = LocalDate.parse(by);
        this.time = LocalTime.parse(time);
    }

    @Override
    public String toString() {
        return "D | " + this.getStatusIcon() + " | " + this.getDescription() + " | " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.time.format(DateTimeFormatter.ofPattern("hhmma"));
    }
}
