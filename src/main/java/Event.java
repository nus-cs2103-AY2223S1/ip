import java.time.LocalDate;

public class Event extends Task {
    private LocalDate at;

    Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    Event(String description, boolean isDone, String at) throws DukeException {
        this(description, isDone, Parser.parseDate(at));
    }

    Event(String description, String at) throws DukeException {
        this(description, false, Parser.parseDate(at));
    }

    String getFormattedDateString() {
        return this.at.format(DATE_FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getFormattedDateString() + ")";
    }
}
