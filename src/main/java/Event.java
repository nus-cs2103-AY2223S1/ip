import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected LocalDateTime at;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDateTime.parse(at, formatter);

        } catch (DateTimeParseException exception) {
            throw new DukeException("Date and time should be in yyyy-mm-dd hh:mm format");
        }
    }

    @Override
    public String toStorageString() {
        String taskString = super.toStorageString();
        return "E" + Task.STORAGE_DELIMITER + taskString + Task.STORAGE_DELIMITER + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm"))
                + ")";
    }
}
