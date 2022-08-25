import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DukeDate {
    protected LocalDateTime dateTime;

    public LocalDateTime convertToDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time = LocalDateTime.parse(input, formatter);
        this.dateTime = time;
        return time;
    }
}
