import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate at;

    public Event(String description, String at) throws UncException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException exception) {
            throw new UncException("Invalid datetime");
        }
    }

    public Event(String description, String at, String done) throws UncException {
        super(description, done == "true");
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException exception) {
            throw new UncException("Invalid datetime");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ")";
    }


    @Override
    public String toStorageString() {
        return "E" + "///" + this.description + "///" + this.at + "///" + this.isDone;
    }
}