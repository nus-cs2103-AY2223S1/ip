package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime time;

    public Event(String description, LocalDateTime time) {
        super(description, Tag.E);
        this.time = time;
    }

    @Override
    public String getDescription() {
        return description + " (" + DateTimeFormatter.ofPattern("MMM dd yyyy H:mm").format(time) + ")";
    }
}
