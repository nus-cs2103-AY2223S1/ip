/**
 * Event is a Task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    /** Date of the event. */
    private final String date;

    /**
     * Private constructor for an event, with a description and date.
     * Event is set as "not done" when created.
     *
     * @param description Description of an event.
     * @param date        Date of an event.
     */
    private Event(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Factory Method for an Event, with a user input.
     * Event is set as "not done" when created.
     *
     * @param input User input that starts with "event".
     *
     * @return Event object with the given user input.
     */
    public static Event create(String input) {
        // Obtain the description and date from the user input.
        String descAndDate = input.substring(6);
        // Split the input into description and date with the separator of "\by".
        String[] split = descAndDate.split(" /at ");
        String description = split[0];
        String date = split[1];
        return new Event(description, date);
    }

    /**
     * Gets the string representation of an event.
     *
     * @return String representation of an event
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.date);
    }
}
