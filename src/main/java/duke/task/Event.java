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

    /**
     * Public constructor which also takes in an optional note about the Event.
     *
     * @param description The description of the deadline
     * @param at When the Deadline is due
     * @param note An optional note about the Deadline
     */
    public Event(String description, LocalDate at, String note) {
        super(description, note);
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
        if (this.getNote() == null) {
            return String.format("[E][%s] %s (at: %s)", this.getStatusIcon(), this.getDescription(), this.at);
        } else {
            assert this.getNote() != null : "This task should have a note";
            return String.format("[D][%s] %s (at: %s)", this.getStatusIcon(), this.getDescription(), this.at)
                    + "\n" + String.format("       - Notes: %s", this.getNote());
        }
    }
}
