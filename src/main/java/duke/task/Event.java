package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the user's task, specifically an event on a specific date.
 */
public class Event extends Task {
    /** The date of the event. */
    protected LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public Event(boolean done, String description, LocalDate date) {
        super(done, description);
        this.date = date;
    }

    @Override
    public String getTask() {
        String done = this.isDone ? "1" : "0";
        return String.format("E | %s | %s | %s", done, this.description, this.date);
    }

    @Override
    public String toString() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }
}
