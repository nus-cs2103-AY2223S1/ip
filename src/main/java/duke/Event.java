package duke;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public LocalDateTime getDateTime() {
        return this.at;
    }

    @Override
    public String getWriteString() {
        return String.format("E | %s | %s", super.getWriteString(), this.at);
    }


    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), this.at);
    }
}