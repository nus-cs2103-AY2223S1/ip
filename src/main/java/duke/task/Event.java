package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task of specific event type.
 */
public class Event extends Task {
    protected LocalDate atDate;

    /**
     * Creates an instance of an event.
     *
     * @param desc Description of the event
     * @param atDate Date of the event
     */
    public Event(String desc, LocalDate atDate) {
        super(desc);
        this.atDate = atDate;
    }

    public String getDescription() {
        return super.description;
    }

    public String getEventAt() {
        return this.atDate.toString();
    }

    /**
     * Return string representation of event task.
     *
     * @return Dtring representation of event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Return shorthand for event task type.
     *
     * @return E for event
     */
    @Override
    public String getTaskType() {
        return "E";
    }


}

