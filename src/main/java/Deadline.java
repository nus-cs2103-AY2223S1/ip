import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate by;

    Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    Deadline(String description, boolean isDone, String by) throws DukeException {
        this(description, isDone, Parser.parseDate(by));
    }

    Deadline(String description, String by) throws DukeException {
        this(description, false, Parser.parseDate(by));
    }

    String getFormattedDateString() {
        return this.by.format(DATE_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getFormattedDateString() + ")";
    }
}
