import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate at;

    Event(String description, boolean isComplete, LocalDate at) {
        super(description, isComplete);
        this.at = at;
    }

    Event(String description, LocalDate at) {
        this(description, false, at);
    }

    Event(String description, boolean isComplete, String at) throws DukeException {
        this(description, isComplete, Task.parseDate(at));
    }

    Event(String description, String at) throws DukeException {
        this(description, false, Task.parseDate(at));
    }

    LocalDate getDate() {
        return this.at;
    }

    String getFormattedDate() {
        return this.getDate().format(DATE_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + this.getFormattedDate() + ")";
    }
}
