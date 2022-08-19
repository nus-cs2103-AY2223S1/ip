import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulate Event which is-a Task.
 *
 * @author: Jonas Png
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Class constructor for Event.
     *
     * @param description Event's description.
     * @param at Event's at.
     * @throws DukeException if event's at is empty.
     */
    public Event(String description, String at) throws DukeException{
        super(description);
        if (at.equals(" ")) {
            throw new DukeException("☹ OOPS!!! The event needs to have specific start time and end time");
        }
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("date after /at should be in YYYY-MM-DD Format.");
        }

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toStorageString() {
        return "E" + super.toStorageString() + " | " + this.at;
    }

}
