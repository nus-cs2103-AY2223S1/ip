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

    @Override
    public String stringify() {
        return String.format("%s | %s | %s", "E", super.stringify(), this.at);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.printDate());
    }
}
