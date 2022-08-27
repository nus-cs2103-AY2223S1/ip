package date;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.DukeException;

public class DeadlineDateTime extends Date {
    private LocalTime time;

    private DeadlineDateTime(String date, String time) throws DateTimeParseException {
        super(date);
        this.time = LocalTime.parse(time);
    }

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

    public static DeadlineDateTime parseDateFromStorage(String storedDateTime) throws DukeException {
        String[] dateTime = storedDateTime.split("\\|", 2);
        try {
            return new DeadlineDateTime(dateTime[0], dateTime[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.ErrorCode.INVALID_DEADLINE_DATETIME_FORMAT);
        }
    }

    @Override
    public String toString() {
        String timeColonPattern = "HH:mm:ss";
        String formattedTime = time.format(DateTimeFormatter.ofPattern(timeColonPattern));
        return super.toString() + ' ' + formattedTime;
    }

    @Override
    public String encode() {
        String timeColonPattern = "HH:mm:ss";
        String formattedTime = time.format(DateTimeFormatter.ofPattern(timeColonPattern));
        return super.encode() + '|' + formattedTime;
    }
}
