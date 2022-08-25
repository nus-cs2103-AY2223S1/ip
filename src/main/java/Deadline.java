import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime dateTime;

    public Deadline(LocalDateTime dateTime, String description) {
        super(description, "D");
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return " (by: " +
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy', ' hh:mm a")) + ")";
    }

    @Override
    public String printText() {
        return super.printText() + " | " + this.dateTime;
    }

    @Override
    public String toString() {
        return "[" + this.getCode() + "]" + super.toString() + this.getDateTime();
    }
}