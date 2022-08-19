import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event is a Task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    public final static DukeException emptyDescription = new DukeException("Description of Event cannot be empty!");
    public final static DukeException wrongFormat =
            new DukeException("Wrong format for Event!\n    Should be 'event <description> /at YYYY-MM-DD'.");

    /** Date of the event. */
    private final LocalDate date;

    /**
     * Private constructor for an event, with a description and date.
     * Event is set as "not done" when created.
     *
     * @param description Description of an event.
     * @param date        Date of an event.
     */
    private Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Factory Method for an Event, with a user input.
     * Event is set as "not done" when created.
     *
     * @param input User input.
     *
     * @return Event object with the given user input.
     */
    public static Event create(String input) throws DukeException {
        if (input.length() < 1) {
            throw Event.emptyDescription;
        }
        // Split the input into description and date with the separator of "/at".
        String[] split = input.split(" /at ");
        if (split.length != 2) {
            throw Event.wrongFormat;
        }
        String description = split[0];
        String date = split[1];
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw DukeException.invalidDate;
        }
        return new Event(description, localDate);
    }

    /**
     * Factory method for an Event, with done and description and date.
     *
     * @param done Whether the Event is done.
     * @param description Description of Event.
     * @param date Date of Event.
     *
     * @return Event object with the given parameters.
     */
    public static Event create(String done, String description, String date) {
        Event event = new Event(description, date);
        if (done.equals("1")) {
            event.markAsDone();
        }
        return event;
    }

    /**
     * Gets the string representation of an Event.
     *
     * @return String representation of an Event.
     */
    public String getFileFormat() {
        return String.format("E | %s | %s", super.getFileFormat(), this.date);
    }

    /**
     * Gets the string representation of an event.
     *
     * @return String representation of an event
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy");
        String date = this.date.format(formatter);
        return String.format("[E]%s (at: %s)", super.toString(), date);
    }
}
