import java.time.LocalDateTime;

public class DateTimeParser {
    private final LocalDateTime dateTime;
    public DateTimeParser(String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date);
        this.dateTime = dateTime;
    }
}
