import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
// import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) throws DukeException{
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException err) {
            throw new DukeException("The date format should be: yyyy-MM-dd");
        }

        // this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toSave() {
        return "D | " + (this.isDone ? "1 | " : "0 | ") + this.description +
                " | " + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n";
    }
}