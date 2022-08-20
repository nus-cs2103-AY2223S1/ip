import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * TimeStamp stores the date and time information of Deadline Tasks and Events.
 */
public class TimeStamp {
    private final LocalDateTime timestamp;

    private TimeStamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Factory method for TimeStamps
     *
     * @param dateTime Date and Time information in the format of (dd/MM/yyyy-HH:mm).
     * @return TimeStamp.
     * @throws DukeException If dateTime is not formatted correctly.
     */
    public static TimeStamp of(String dateTime) throws DukeException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy-H:mm");
        try {
            return new TimeStamp(LocalDateTime.parse(dateTime.strip(), format));
        } catch (DateTimeParseException e) {
            throw new DukeException("Please indicate date and time properly. (20/8/2022-15:37)");
        }
    }

    /**
     * Factory method for TimeStamps, meant for reading from File.
     *
     * @param dateTime Date and Time information in the format of (dd/MM/yyyy-HH:mm).
     * @return TimeStamp.
     */
    public static TimeStamp fromFile(String dateTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("E, d MMMM yyyy h:mm a");
        try {
            return new TimeStamp(LocalDateTime.parse(dateTime.strip(), format));
        } catch (DateTimeParseException e) {
            System.out.println("fromFile failed");
            return new TimeStamp(LocalDateTime.now());
        }
    }

    /**
     * Returns String Representation of a TimeStamp.
     *
     * @return String Representation.
     */
    @Override
    public String toString() {
        return " " + timestamp.format(DateTimeFormatter.ofPattern("E, d MMMM yyyy h:mm a"));
    }
}
