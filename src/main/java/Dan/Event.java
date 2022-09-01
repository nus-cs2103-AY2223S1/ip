package dan;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

/**
 * One of the task types. A task associated with a timing for which it's happening
 */
public class Event extends Task {
    private static final String ICON = "E";
    protected LocalDateTime dateString;

    /**
     * Creates an Event with its associated description and date.
     *
     * @param description The description of the task
     * @param dateString A String representing a date-time in the pattern "dd/MM/yyyy HHmm"
     * @throws DateTimeParseException If the date-time string is not part of its regular pattern
     */
    public Event(String description, String dateString) throws DateTimeParseException {
        super(description);
        this.dateString = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toDataString(String separator) {
        return String.format("%s%s%s%s", ICON, super.toDataString(separator), separator,
                this.dateString.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", ICON, super.toString(),
                this.dateString.format(DateTimeFormatter.ofPattern("MMM dd h:mma")));
    }
}
