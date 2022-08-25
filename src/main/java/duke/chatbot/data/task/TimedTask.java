package duke.chatbot.data.task;

import java.time.LocalDateTime;

import static duke.chatbot.common.DateFormat.DATE_OUTPUT_FORMAT;
import static duke.chatbot.common.DateFormat.DATE_TIME_INPUT_FORMAT;
import static duke.chatbot.common.DateFormat.TIME_OUTPUT_FORMAT;

public abstract class TimedTask extends Task {
    private final LocalDateTime dateTime;

    public TimedTask(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public TimedTask(String description, LocalDateTime dateTime, boolean isDone) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    public boolean hasMatchingDate(String date) {
        return dateTime.toLocalDate().format(DATE_TIME_INPUT_FORMAT).equals(date);
    }

    protected String getDateString() {
        return dateTime.format(DATE_OUTPUT_FORMAT);
    }

    protected String getTimeString() {
        return dateTime.format(TIME_OUTPUT_FORMAT) + "hrs";
    }

    @Override
    public String encode() {
        return super.encode() + ",,," + dateTime.format(DATE_TIME_INPUT_FORMAT);
    }
}
