package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An Event is a Task containing its time of occurrence.
 */
public class Event extends Task {
    private LocalDateTime time;

    /**
     * Constructor for Event.
     *
     * @param isDone boolean denoting whether Event should be marked.
     * @param text description of task.
     * @param time date and time of Event's occurrence.
     */
    public Event(boolean isDone, String text, LocalDateTime time) {
        super(isDone, text);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)",
                super.toString(),
                this.time.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm")));
    }
}
