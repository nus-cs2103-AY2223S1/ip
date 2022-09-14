package ren;

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
     * Factory method for TimeStamps.
     *
     * @param dateTime Date and Time information in the format of (dd/MM/yyyy-HH:mm).
     * @return TimeStamp.
     * @throws RenException If dateTime is not formatted correctly.
     */
    public static TimeStamp of(String dateTime) throws RenException {
        // format of the date and time information in the input
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy-H:mm");
        try {
            LocalDateTime timestamp = LocalDateTime.parse(dateTime.strip(), format);
            return new TimeStamp(timestamp);
        } catch (DateTimeParseException e) {
            throw new RenException("Please indicate date and time properly. (20/8/2022-15:37)");
        }
    }

    /**
     * Factory method for TimeStamps, meant for reading from File.
     *
     * @param dateTime Date and Time information in the format of (dd/MM/yyyy-HH:mm).
     * @return TimeStamp.
     */
    public static TimeStamp fromFile(String dateTime) {
        // format of the date and time information in the input
        DateTimeFormatter format = DateTimeFormatter.ofPattern("E, d MMMM yyyy h:mm a");
        try {
            LocalDateTime timestamp = LocalDateTime.parse(dateTime.strip(), format);
            return new TimeStamp(timestamp);
        } catch (DateTimeParseException e) {
            System.out.println("fromFile failed");
            return new TimeStamp(LocalDateTime.now());
        }
    }

    /**
     * Compares this TimeStamp to another TimeStamp.
     *
     * @param other The other TimeStamp to compare with.
     * @return -1 if this TimeStamp is earlier. 1 if the other TimeStamp is earlier. 0 if both are the same.
     */
    public int compareTo(TimeStamp other) {
        return this.timestamp.compareTo(other.timestamp);
    }

    /**
     * Returns String Representation of a TimeStamp.
     *
     * @return String Representation.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, d MMMM yyyy h:mm a");
        return " " + timestamp.format(formatter);
    }
}
