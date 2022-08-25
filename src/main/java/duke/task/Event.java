package duke.task;

import duke.main.DateTimeFormatUtils;
import java.time.LocalDateTime;

/**
 * Class handling the event task type.
 */
public class Event extends Task {
    /* Duration of Event */
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    /**
     * Constructor for Event Class.
     *
     * @param name String representation of task name
     * @param start LocalTimeDate representing start of event.
     * @param end LocalTimeDate representing end of event.
     */
    public Event(String name, LocalDateTime start, LocalDateTime end) {
        this.name = name;
        this.startDate = start;
        this.endDate = end;
    }

    /**
     * Returns string representation of Event object.
     *
     * @return String representation of Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateTimeFormatUtils.printDate(startDate)
                + " to " + DateTimeFormatUtils.printDate(endDate) + ")";
    }
    /**
     * Returns formatted string representation of event task for save processing.
     *
     * @return Formatted string representation of event task.
     */
    @Override
    public String convertToSaveFormat() {
        int status = isDone ? 1 : 0;
        return String.format("%s | %d | %s | %s", "E", status, name,
                DateTimeFormatUtils.inputFormat(startDate) + " to " + DateTimeFormatUtils.inputFormat(endDate));
    }
}
