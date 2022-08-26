import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            this.date = null;
        }

    }

    @Override
    public String getStringToSave() {
        return this.isDone
                ? "D | 1 | " + description + " | " + by
                : "D | 0 | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return date == null
                ? "[D]" + super.toString() + " (by: " + by + ")"
                : "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
