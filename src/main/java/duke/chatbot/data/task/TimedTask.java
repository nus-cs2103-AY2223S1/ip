package duke.chatbot.data.task;

import java.time.LocalDateTime;

import static duke.chatbot.common.DateFormat.DATE_INPUT_FORMAT;
import static duke.chatbot.common.DateFormat.DATE_OUTPUT_FORMAT;
import static duke.chatbot.common.DateFormat.DATE_TIME_INPUT_FORMAT;
import static duke.chatbot.common.DateFormat.TIME_OUTPUT_FORMAT;

/**
 * A task that has a date and time associated to it.
 */
public abstract class TimedTask extends Task {
    /** The date and time associated to the task */
    private LocalDateTime dateTime;

    public TimedTask(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public TimedTask(String description, LocalDateTime dateTime, boolean isDone) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Returns true if the argument date string and the date string associated with
     * the task are the same and false otherwise.
     * @param date The date to compared dateTime with.
     * @return A boolean that describes whether the argument date and task date are
     * matching.
     */
    public boolean hasMatchingDate(String date) {
        return dateTime.toLocalDate().format(DATE_INPUT_FORMAT).equals(date);
    }

    /**
     * Returns a string that corresponds to the task date.
     * @return A string that corresponds to the task date.
     */
    protected String getDateString() {
        return dateTime.format(DATE_OUTPUT_FORMAT);
    }

    /**
     * Returns a string that corresponds to the task time.
     * @return A string that corresponds to the task time.
     */
    protected String getTimeString() {
        return dateTime.format(TIME_OUTPUT_FORMAT) + "hrs";
    }

    @Override
    public String encode() {
        return super.encode() + ",,," + dateTime.format(DATE_TIME_INPUT_FORMAT);
    }
}
