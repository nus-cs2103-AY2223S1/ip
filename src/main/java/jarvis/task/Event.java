package jarvis.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    public LocalDate at;

    public Event(String description, String date) {
        super(description);
        this.at = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
