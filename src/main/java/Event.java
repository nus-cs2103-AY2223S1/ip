import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime dateTime;

    public Event(LocalDateTime dateTime, String description) {
        super(description, "E");
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return " (at: " +
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy', ' hh:mm a")) + ")";
    }

    public String printText() {
        return super.printText() + " | " + this.dateTime;
    }
    @Override
    public String toString() {
        return "[" + this.getCode() + "]" + super.toString() + this.getDateTime();
    }
}