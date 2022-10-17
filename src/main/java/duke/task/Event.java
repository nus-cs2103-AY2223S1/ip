package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String formatTask() {
        return "[E] [" + super.getStatusIcon() + "] " + super.description + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toString() {
        return "E/" + super.getStatusIcon() + "/" + super.description + "/" + at;
    }
}

