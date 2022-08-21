import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate date;

    public Event(String description, String date) throws DukeException{
        super(description);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date should be in yyyy-mm-dd format.");
        }
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }

    @Override
    public String toStorageString() {
        String doneDescriptionString = super.toStorageString();
        return "E" + Task.STORAGE_DELIMITER
                + doneDescriptionString + Task.STORAGE_DELIMITER
                + this.date;
    }
}