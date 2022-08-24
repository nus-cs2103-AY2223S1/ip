package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;
    protected LocalTime time;

    public Event(String description, String[] msg) {
        super(description);
        this.at = LocalDate.parse(msg[1]);
        this.time = LocalTime.parse(msg[2]);
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.time.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
    }
}
