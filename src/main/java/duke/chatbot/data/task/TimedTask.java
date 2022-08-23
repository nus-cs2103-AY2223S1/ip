package duke.chatbot.data.task;

import duke.chatbot.data.exception.InvalidInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class TimedTask extends Task {
    private LocalDateTime dateTime;

    public TimedTask(String description, String dateTime) throws InvalidInputException {
        super(description);
        this.dateTime = parseDateTime(dateTime);
    }

    public TimedTask(String description, String dateTime, boolean isDone) throws InvalidInputException {
        super(description, isDone);
        this.dateTime = parseDateTime(dateTime);
    }

    public boolean hasMatchingDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" yyyy-MM-dd");
        return dateTime.toLocalDate().format(formatter).equals(date);
    }

    protected String getDateString() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    protected String getTimeString() {
        return dateTime.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String encode() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return super.encode() + ",,," + dateTime.format(formatter);
    }

    public static LocalDateTime parseDateTime(String dateTime) throws InvalidInputException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException();
        }
    }
}
