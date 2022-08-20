import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate at;

    /**
     * Creates an event with a start time
     * @param description the description of the event
     * @param at the start time of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    /**
     * @return String representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + parseDate(at) + ")";
    }
}
