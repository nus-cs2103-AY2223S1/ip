import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate atDate;
    protected LocalTime atTime;

    public Event(String description, String at) {
        super(description);
        String[] dateTimeSplit = at.split(" ");
        this.atDate = LocalDate.parse(dateTimeSplit[0]);
        if (dateTimeSplit.length > 1) {
            this.atTime = LocalTime.parse(dateTimeSplit[1]);
        }
    }

    @Override
    public String toString() {
        String formattedDate =  this.atDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String formattedTime = this.atTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        return String.format("[E] %s (by: %s %s)", super.toString(), formattedDate, formattedTime);
    }
}