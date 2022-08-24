import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected LocalDateTime by;
    public Deadline(String description, String by) throws DukeException {

        super(description);
        try {
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Date and time should be in yyyy-mm-dd hh:mm format");
        }
    }

    @Override
    public String toStorageString() {
        String taskString = super.toStorageString();
        return "D" + Task.STORAGE_DELIMITER + taskString + Task.STORAGE_DELIMITER + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm"))
                + ")";
    }
}
