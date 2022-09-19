package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at);
    }

    @Override
    public String saveString() {
        return "E | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | " + this.at;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm:ss")) + ")";
    }
}
