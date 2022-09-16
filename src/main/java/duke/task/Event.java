package duke.task;

import java.time.LocalDate;

/**
 * Class which inherits the Task class for an Event
 *
 * @author kaij77
 * @version 0.1
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Public constructor for an Event.
     *
     * @param description The description of the Event
     * @param at The date and time of the Event
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String stringifyTask() {
        return String.format("%s | %s | %s", "E", super.stringifyTask(), this.at);
    }

    /**
     * Returns the String representation of the Event.
     *
     * @return the String representation of the Event
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }
}
