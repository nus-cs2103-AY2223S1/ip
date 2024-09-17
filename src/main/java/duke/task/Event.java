package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for an event task.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Creates an event with specified description and date.
     *
     * @param description The description of the event to be created.
     * @param at The date of the event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Creates an event with specified description, completeness and date.
     *
     * @param description The description of the event to be created.
     * @param isDone If the event is completed.
     * @param at The date of the event.
     */
    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    private String printDate() {
        return DateTimeFormatter.ofPattern("MMM dd yyyy").format(at);
    }

    /**
     * Returns the event in a string format to be saved in a local file.
     *
     * @return A string corresponding to the event.
     */
    @Override
    public String stringify() {
        return String.format("%s | %s | %s", "E", super.stringify(), at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), printDate());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (obj instanceof Event) {
            Event event = (Event) obj;
            return super.equals(event) && event.at.equals(at);
        } else {
            return false;
        }
    }
}
