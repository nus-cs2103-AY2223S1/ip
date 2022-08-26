package Duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DukeDate {
    private LocalDateTime dateTime;

    public DukeDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time = LocalDateTime.parse(input, formatter);
        this.dateTime = time;
    }


    public LocalDateTime getDateTime() {
        return this.dateTime;
    }
}
