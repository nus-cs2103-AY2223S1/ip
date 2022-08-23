package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate eventTime;

    public Event(String description, LocalDate eventTime) {
        this.description = description;
        this.isDone = false;
        this.eventTime = eventTime;
    }

    public Event(String description, boolean isDone, LocalDate eventTime) {
        this.description = description;
        this.isDone = isDone;
        this.eventTime = eventTime;
    }

    @Override
    public String saveStringFormat() {
        return String.format("E | %d | %s | %s", this.isDone? 1 : 0, this.description,
                this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
    @Override
    public String toString() {
        return "[E] " + "[" + this.getStatusIcon() + "] " + this.description
                +  " (at: " + this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
