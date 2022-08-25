package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime atTime;

    public Event(TaskType type, String name, boolean isMarked, String timeStr) {
        super(type, name, isMarked);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "HHmm, d/MM/yyyy");
        this.atTime = LocalDateTime.parse(timeStr, formatter);
    }

    public LocalDateTime getAtTime() {
        return this.atTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "hh:mm a, EEE, d MMM yyyy");
        return "[E]" + super.toString()
                + " (at: " + atTime.format(formatter) + ")";
    }
}
