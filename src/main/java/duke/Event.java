package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    /**
     * Encodes the task to string for storage.
     * @return the encoded string
     */
    @Override
    public String encodeToString() {
        return "E|" + this.getStatusIcon() + "|" + this.description + "|" + this.at;
    }

    @Override
    public String toString() {
        String formattedAt = at.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        return "[E]" + super.toString() + " (at: " + formattedAt + ")";
    }
}
