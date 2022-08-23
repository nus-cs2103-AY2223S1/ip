import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate date;
    protected LocalTime time;

    public Event(String description, String timeline) {
        super(description);
        String[] splitStr = timeline.trim().split("\\s+");
        LocalDate date = LocalDate.parse(splitStr[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalTime time = LocalTime.parse(splitStr[1], DateTimeFormatter.ofPattern("HH:mm"));
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        String formattedTime = this.time.format(DateTimeFormatter.ofPattern("HHmm"));
        return "[E]" + super.toString() + " (at: " + formattedDate + " " + formattedTime + ")";
    }
}