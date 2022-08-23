import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String description, boolean done, LocalDateTime at) {
        super(description, done);
        this.at = at;
    }

    @Override
    public String toString() {
        String checkbox = this.getDone() ? "[E][X]" : "[E][ ]";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        String dateFormatted = "(at: " + at.format(formatter) + ")";
        return checkbox + " " + super.getDescription() + " " + dateFormatted;
    }
}
