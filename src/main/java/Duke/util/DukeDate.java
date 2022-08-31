package Duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a class that handles conversion from String
 * to LocalDateTime to be stored in Task objects
 */
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
