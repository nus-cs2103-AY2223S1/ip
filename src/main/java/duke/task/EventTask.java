package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private final LocalDateTime time;
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yy HHmm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a, MMM d, yyyy");

    public EventTask(String description, LocalDateTime time) {
        super('E', description);
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.time.format(outputFormatter) + ")";
    }

    @Override
    public String toSaveString() {
        return super.toSaveString() + " | " + this.time.format(inputFormatter);
    }
}
