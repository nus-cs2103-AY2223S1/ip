import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy kkmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy kkmm")) + ")";
    }
}
