package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that will occur on a particular date
 */

public class Event extends Task {

    private LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of the event
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "+ at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    //super.toString() will return [] description
}