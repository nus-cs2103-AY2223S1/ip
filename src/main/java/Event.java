import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate date) {
        super(description);
        at = date;
    }
    public Event(String description, String dateString, boolean isDone) {
        super(description, isDone);
        at = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String getSaveFormat() {
        return "E" + " | " + (getIsDone() ? 1 : 0) + " | " + getDescription() + " | " +
                at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
