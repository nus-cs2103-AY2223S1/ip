package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected final LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, LocalDate at) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    public String formatTime(LocalDate time) {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }


    /**
     * Shows the Event task description and the date it occurs at.
     *
     * @return String with the Event task description and the date it occurs at.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatTime(at) + ")";
    }

    @Override
    public String saveTask() {
        return String.format("E | %s | %s", super.saveTask(), at);
    }
}

