package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    LocalDate date;

    public Event(String task_description, LocalDate date) {
        super(task_description);
        this.date = date;
    }

    public Event(String task_description, boolean isDone, LocalDate date) {
        super(task_description, isDone);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.
                ofPattern("MMM dd yyyy")) + ")";
    }
}
