package duke.task;

import duke.exception.EventException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * Event task for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class Event extends Task {
    private String timeString;
    private LocalDate time;

    /**
     * Constructs a new Event instance.
     *
     * @param description the description of the task.
     * @param timeString the string which represents the time of the task.
     * @throws EventException If timeString is not valid.
     */
    public Event(String description, String timeString) throws EventException {
        super(description);
        try {
            this.timeString = timeString;
            this.time = LocalDate.parse(this.timeString);
        } catch (DateTimeParseException error) {
            throw new EventException("The time given is not a valid date. " +
                    "Try to represent the time in yyyy-mm-dd format.");
        }
    }

    /**
     * Gets the string representation of the Event.
     *
     * @return the string which represents the current Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Transforms the Event to a string that is compatible to Duke's storage.
     *
     * @return the string to be saved in storage.
     */
    @Override
    public String toStorageRepresentation() {
        return "E|" + super.toStorageRepresentation() + "|" + this.timeString;
    }
}
