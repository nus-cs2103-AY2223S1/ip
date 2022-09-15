package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Command class for adding Events.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for Event Command.
     *
     * @param description Task description.
     * @param at Time the Event occurs.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String taskMemo() {
        return "E" + super.taskMemo() + " | " + this.at.toString();
    }
}
