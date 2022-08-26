package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Event extends Task {
    public static final String TASK_WORD = "event";

    private final LocalDateTime at;

    public Event(String description, boolean done, LocalDateTime at) {
        super(description, done);
        this.at = at;
    }

    @Override
    public Optional<LocalDateTime> getTime() {
        return Optional.of(this.at);
    }

    @Override
    public String getTaskType() {
        return "Event";
    }

    @Override
    public String toString() {
        String checkbox = this.getDone() ? "[E][X]" : "[E][ ]";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        String dateFormatted = "(at: " + this.at.format(formatter) + ")";
        return checkbox + " " + super.getDescription() + " " + dateFormatted;
    }
}
