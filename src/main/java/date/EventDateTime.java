package date;

import exception.DukeException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventDateTime extends Date {
    private LocalTime startTime;
    private LocalTime endTime;

    private EventDateTime(String date, String startTime, String endTime) throws DateTimeParseException {
        super(date);
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
    }

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

    public static EventDateTime parseDateFromStorage(String storedDateTime) throws DukeException {
        String[] dateTime = storedDateTime.split("\\|", 3);
        try {
            return new EventDateTime(dateTime[0], dateTime[1], dateTime[2]);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.ErrorCode.INVALID_EVENT_DATETIME_FORMAT);
        }
    }

    @Override
    public String toString() {
        String timeColonPattern = "HH:mm:ss";
        String formattedStartTime = startTime.format(DateTimeFormatter.ofPattern(timeColonPattern));
        String formattedEndTime = endTime.format(DateTimeFormatter.ofPattern(timeColonPattern));
        return super.toString() + ' ' + formattedStartTime + " - " + formattedEndTime;
    }

    @Override
    public String encode() {
        String timeColonPattern = "HH:mm:ss";
        String formattedStartTime = startTime.format(DateTimeFormatter.ofPattern(timeColonPattern));
        String formattedEndTime = endTime.format(DateTimeFormatter.ofPattern(timeColonPattern));
        return super.encode() + '|' + formattedStartTime + '|' + formattedEndTime;
    }
}
