package duke.chatbot.data.task;

import duke.chatbot.data.exception.InvalidInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class TimedTask extends Task {
    public static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final DateTimeFormatter TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("HHmm");

    private LocalDateTime dateTime;

    public TimedTask(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public TimedTask(String description, LocalDateTime dateTime, boolean isDone) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    public boolean hasMatchingDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" yyyy-MM-dd");
        return dateTime.toLocalDate().format(formatter).equals(date);
    }

    protected String getDateString() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    protected String getTimeString() {
        return dateTime.format(DateTimeFormatter.ofPattern("HHmm")) + "hrs";
    }

    @Override
    public String encode() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return super.encode() + ",,," + dateTime.format(formatter);
    }
}
