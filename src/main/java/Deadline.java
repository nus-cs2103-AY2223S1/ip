import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private final LocalDate by;

    public Deadline(String description, String by) throws DukeException {
        super(description);

        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date.");
        }
    }

    @Override
    public String encode() {
        return "D | " + super.encode() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
