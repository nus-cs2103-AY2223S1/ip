import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected static String inputFormat = "yyyy-MM-dd";
    protected static String outputFormat = "d MMM yyyy";
    protected LocalDate by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern(inputFormat));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please specify a date in this format: " + inputFormat + "!");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern(outputFormat)) + ")";
    }
}
