package duke.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                at.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    @Override
    public String getSavedFileFormat() {
        return "E | " + (this.isDone ? 1 : 0) + " | " + this.description
                + " | "
                + this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}
