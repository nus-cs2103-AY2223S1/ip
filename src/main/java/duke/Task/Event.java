package duke.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime time;
    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    public Event(Boolean isDone, String description, LocalDateTime time) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String formatTask() {
        String dateTimePattern = "dd MMM yyyy, h.mm a";

        return String.format("[E] [%s] %s (at: %s)", this.getStatusIcon(), this.description, this.time.format(DateTimeFormatter.ofPattern(dateTimePattern)) );
    }
    public String formatTaskString() {
        return String.format("E|%s|%s|%s", this.isDone, this.description, this.time);
    }

}
