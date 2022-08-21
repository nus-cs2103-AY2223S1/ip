package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate dateStart;
    private LocalTime timeStart;

    private LocalDate dateEnd;
    private LocalTime timeEnd;

    public Event(String description,
                    String dateStart, String timeStart,
                    String dateEnd, String timeEnd) throws DateTimeParseException {
        super(description);
        this.dateStart = LocalDate.parse(dateStart);
        this.timeStart = LocalTime.parse(timeStart);
        this.dateEnd = LocalDate.parse(dateEnd);
        this.timeEnd = LocalTime.parse(timeEnd);
    }

    public Event(String description, int status,
                 String dateStart, String timeStart,
                 String dateEnd, String timeEnd) throws DateTimeParseException {
        this(description, dateStart, timeStart, dateEnd, timeEnd);
        isDone = status == 1;
    }

    private String formatAsMmmDdYyyy(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private String formatAs12Hour(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    @Override
    public String parseToSaveData() {
        return "E" + "|" + super.parseToSaveData() + "|"
                + dateStart + "|" + timeStart + "|" + dateEnd + "|" + timeEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + formatAsMmmDdYyyy(dateStart) + " " + formatAs12Hour(timeStart) + " - "
                + formatAsMmmDdYyyy(dateEnd) + " " + formatAs12Hour(timeEnd) + ")";
    }
}
