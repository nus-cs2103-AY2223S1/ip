package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends DatedTask {

    public Event(String description, LocalDate at) throws DateTimeException {
        super(description, at);
    }

    /**
     * Returns the string of event.
     *
     * @return the string of event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
