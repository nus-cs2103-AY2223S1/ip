package date;

import exception.DukeException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Stores a representation of a data, start time and end time based on ISO-8601
 *
 * @author Bryan Lim Jing Xiang
 */
public class EventDateTime extends Date {
    private LocalTime startTime;
    private LocalTime endTime;

    private EventDateTime(String date, String startTime, String endTime) throws DateTimeParseException {
        super(date);
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
    }

    /**
     * Parses a given string and returns an object storing the Date and Time given
     *
     * @param dateTime A string with a date, start time and end time as per
     *                 ISO-8601 format, delimited by a space
     * @return EventDateTime object that stores the Date and Time passed in
     * @throws DukeException If the dateTime passed in cannot be parsed
     */
    public static EventDateTime parseDate(String dateTime) throws DukeException {
        String[] splitted = dateTime.split("\\s+", 3);
        if (splitted.length < 3 ||
                splitted[0].strip().equals("") ||
                splitted[1].strip().equals("") ||
                splitted[2].strip().equals("")) {
            throw new DukeException(DukeException.ErrorCode.INVALID_EVENT_DATETIME_FORMAT);
        }
        try {
            return new EventDateTime(splitted[0], splitted[1], splitted[2]);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.ErrorCode.INVALID_EVENT_DATETIME_FORMAT);
        }
    }

    /**
     * Parses date from encoded data in save file
     *
     * @param storedDateTime An encoding of EventDateTime stored in the save file
     * @return EventDateTime object that stores the Date and Time
     * @throws DukeException If the storedDateTime cannot be parsed
     */
    public static EventDateTime parseDateFromStorage(String storedDateTime) throws DukeException {
        String[] dateTime = storedDateTime.split("\\|", 3);
        try {
            return new EventDateTime(dateTime[0], dateTime[1], dateTime[2]);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.ErrorCode.INVALID_EVENT_DATETIME_FORMAT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String timeColonPattern = "HH:mm:ss";
        String formattedStartTime = startTime.format(DateTimeFormatter.ofPattern(timeColonPattern));
        String formattedEndTime = endTime.format(DateTimeFormatter.ofPattern(timeColonPattern));
        return super.toString() + ' ' + formattedStartTime + " - " + formattedEndTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        String timeColonPattern = "HH:mm:ss";
        String formattedStartTime = startTime.format(DateTimeFormatter.ofPattern(timeColonPattern));
        String formattedEndTime = endTime.format(DateTimeFormatter.ofPattern(timeColonPattern));
        return super.encode() + '|' + formattedStartTime + '|' + formattedEndTime;
    }
}
