import java.time.LocalDateTime;

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
        super(name);
        this.startDate = start;
        this.endDate = end;
    }
    /**
     * Returns string representation of Event object.
     * @return String representation of Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateTimeFormatUtils.printDate(startDate)
                + " to " + DateTimeFormatUtils.printDate(endDate) + ")";
    }


}
