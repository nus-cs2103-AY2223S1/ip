package hazell.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import hazell.exceptions.DateTimeInvalid;
import hazell.exceptions.HazellException;

/**
 * A superclass to be inherited by tasks that require a time.
 */
public abstract class TimeSensitiveTask extends Task {

    protected LocalDate time;
    protected TimeSensitiveTask(boolean isDone, String description, LocalDate time) {
        super(isDone, description);
        this.time = time;
    }

    public void postpone(String time) throws HazellException {
        this.time = toLocalDate(time);
    }

    protected static LocalDate toLocalDate(String dateString) throws HazellException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException ex) {
            throw new DateTimeInvalid();
        }
    }

    protected String getFriendlyTime() {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    protected String getParsedTime() {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
