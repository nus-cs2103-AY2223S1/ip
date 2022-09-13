package pluto.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event task.
 */
public class Event extends Task {
    /** Event time */
    private LocalDateTime at;

    /**
     * Initializes global variables.
     * @param description Event description.
     * @param at Event time.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        String color = (isDone ? Task.ANSI_GREEN : Task.ANSI_RED);
        return color + "[E]" + super.toString() + " (at: " + getDateTime(at) + ")" + Task.ANSI_RESET;
    }

    @Override
    public String toFile() {
        int done = (isDone ? 1 : 0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return String.format("E | %d | %s | %s", done, description, dtf.format(at));
    }

    @Override
    public LocalDate getDateMaybe() {
        return at.toLocalDate();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event other = (Event) o;
            return this.at.equals(other.at) && this.description.equals(other.description)
                    && this.isDone == other.isDone;
        }
        return false;
    }
}
