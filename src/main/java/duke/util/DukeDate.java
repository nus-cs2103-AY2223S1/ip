package duke.util;

import duke.exceptions.DukeDateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a class that handles conversion from String
 * to LocalDateTime to be stored in Task objects
 */
public class DukeDate {
    private LocalDateTime dateTime;

    public DukeDate(String input) throws DukeDateException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.dateTime = LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeDateException("The format of your date is wrong!");
        }
    }


    public LocalDateTime getDateTime() {
        return this.dateTime;
    }
}
