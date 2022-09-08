package jarvis.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    public LocalDate by;

    public Deadline(String description, String date) {
        super(description);
        this.by = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
