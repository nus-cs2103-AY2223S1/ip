package tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
    private final LocalDateTime time;

    public Deadline(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[D][X] " + this.description + " (by: " + this.time.format(DF) + ")";
        } else {
            return "[D][ ] " + this.description + " (by: " + this.time.format(DF) + ")";
        }
    }

    @Override
    public String toStringText() {
        return "D | " + this.description + " | " + this.isDone + " | " + this.time.format(DF);
    }
}
