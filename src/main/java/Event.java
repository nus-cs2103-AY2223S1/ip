import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected static String inputFormat = "yyyy-MM-dd";
    protected static String outputFormat = "d MMM yyyy";
    protected LocalDate at;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at, DateTimeFormatter.ofPattern(inputFormat));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please specify a date in this format: " + inputFormat + "!");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern(outputFormat)) + ")";
    }
}
