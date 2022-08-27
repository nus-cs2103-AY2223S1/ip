import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String dateString) {
        super(description);
        at = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String getSaveFormat() {
        return "E" + " | " + (getIsDone() ? 1 : 0) + " | " + getDescription() + " | " + getAt() +
                System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
