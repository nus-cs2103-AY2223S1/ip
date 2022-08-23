package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class EventTask extends Task {

    protected LocalDate eventDate;

    public EventTask(String description, String eventDate) {
        super(description);
        this.eventDate = LocalDate.parse(eventDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (at: " + this.eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String encodeToString() {
        String taskStatus = this.isDone ? "Done" : "Undone";
        return String.format("D | %s | %s | %s", taskStatus, this.description, this.eventDate);
    }
}
