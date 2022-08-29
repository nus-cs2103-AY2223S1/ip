import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDate by, boolean done) {
        super(description, done);
        this.by = by;
    }

    @Override
    public String toString() {
        // Implemented DateTimeFormatter for Level-8 here
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }


    /**
     * Getter to access by field.
     * @return The due date
     */
    public LocalDate getBy() {
        return this.by;
    }

}
