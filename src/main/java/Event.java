import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    LocalDate start;
    LocalDate end;

    public Event(String description,String start, String end) {
        super(description);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    @Override
    public String toString() {
        String startFormat = String.format("%s, %d %s, %s",
                start.getDayOfWeek(), start.getDayOfMonth(),
                start.getMonth(), start.getYear());
        String endFormat = String.format("%s, %d %s, %s",
                end.getDayOfWeek(), end.getDayOfMonth(),
                end.getMonth(), end.getYear());
        return String.format("[E] %s(at: %s to %s)", super.toString(), startFormat, endFormat);
    }
}
