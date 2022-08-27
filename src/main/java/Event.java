import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate at;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Invalid datetime");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ")";
    }


    @Override
    public String toStorageString() {
        return "E" + "///" + this.description + "///" + this.at;
    }
}