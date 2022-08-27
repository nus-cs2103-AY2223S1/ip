package date;

import exception.DukeException;
import util.Ui;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Stores a representation of a data and a time based on ISO-8601
 *
 * @author Bryan Lim Jing Xiang
 */
public class DeadlineDateTime extends Date {
    private LocalTime time;

    private DeadlineDateTime(String date, String time) throws DateTimeParseException {
        super(date);
        this.time = LocalTime.parse(time);
    }

    /**
     * Parses a given string and returns an object storing the Date and Time given
     *
     * @param dateTime A string with a date and time as per ISO-8601 format,
     *                 delimited by a space
     * @return DeadlineDateTime object that stores the Date and Time passed in
     * @throws DukeException If the dateTime passed in cannot be parsed
     */
    public static DeadlineDateTime parseDate(String dateTime) throws DukeException {
        String[] splitted = dateTime.split("\\s+", 2);
        if (splitted.length < 2 || splitted[0].strip().equals("") || splitted[1].strip().equals("")) {
            throw new DukeException(DukeException.ErrorCode.INVALID_DEADLINE_DATETIME_FORMAT);
        }
        try {
            return new DeadlineDateTime(splitted[0], splitted[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.ErrorCode.INVALID_DEADLINE_DATETIME_FORMAT);
        }
    }

    /**
     * Parses date from encoded data in save file
     *
     * @param storedDateTime An encoding of DeadlineDateTime stored in the save file
     * @return DeadlineDateTime object that stores the Date and Time
     * @throws DukeException If the storedDateTime cannot be parsed
     */
    public static DeadlineDateTime parseDateFromStorage(String storedDateTime) throws DukeException {
        String[] dateTime = storedDateTime.split("\\|", 2);
        try {
            return new DeadlineDateTime(dateTime[0], dateTime[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.ErrorCode.INVALID_DEADLINE_DATETIME_FORMAT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String timeColonPattern = "HH:mm:ss";
        String formattedTime = time.format(DateTimeFormatter.ofPattern(timeColonPattern));
        return super.toString() + ' ' + formattedTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        String timeColonPattern = "HH:mm:ss";
        String formattedTime = time.format(DateTimeFormatter.ofPattern(timeColonPattern));
        return super.encode() + '|' + formattedTime;
    }
}
