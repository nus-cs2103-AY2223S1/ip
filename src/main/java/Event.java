import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime at;

    public Event(String task, String at) {
        super(task);
        this.at = LocalDateTime.parse(at.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy kkmm"));;
    }

    protected String getDateTime() {
        return formatDateTime(at);
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDateTime() + ")";
    }
}
