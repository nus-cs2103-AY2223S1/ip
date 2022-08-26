package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date;

    public Event(String taskDescription, LocalDate date) {
        super(taskDescription);
        this.date = date;
    }

    public Event(String taskDescription, boolean isDone, LocalDate date) {
        super(taskDescription, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.
                ofPattern("MMM dd yyyy")) + ")";
    }
}
