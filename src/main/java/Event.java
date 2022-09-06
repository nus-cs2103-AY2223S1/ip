import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
// import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException err) {
            throw new DukeException("The date format should be: yyyy-MM-dd");
        }

        // this.at = LocalDate.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toSave() {
        return "E | " + (this.isDone ? "1 | " : "0 | ") + this.description +
                " | " + this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n";
    }
}