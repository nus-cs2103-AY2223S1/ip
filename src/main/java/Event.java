import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;

    public Event(String taskDescription, String at) {
        super(taskDescription);
        this.at = LocalDate.parse(at);
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + " (at:" + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
