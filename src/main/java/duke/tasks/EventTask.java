package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that start at a specific time and ends at a specific time
 */
public class EventTask extends Task {

    private final LocalDate at;

    /**
     * Constructor for EventTask
     *
     * @param description Description of task details
     * @param at          When the event task is at
     */
    public EventTask(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor for EventTask and isDone initialised to false
     *
     * @param description Description of task details
     * @param at          When the event task is at
     * @param isDone      Whether task has been done
     */
    public EventTask(String description, LocalDate at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns String representation of EventTask
     *
     * @return String representation of EventTask
     */
    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }

    @Override
    public String toSaveString() {
        return "E " + super.toSaveString() + " | " + this.at;
    }
}
