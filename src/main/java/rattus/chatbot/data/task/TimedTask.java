package rattus.chatbot.data.task;

import static rattus.chatbot.common.DateFormat.DATE_OUTPUT_FORMAT;
import static rattus.chatbot.common.DateFormat.DATE_TIME_INPUT_FORMAT;
import static rattus.chatbot.common.DateFormat.TIME_OUTPUT_FORMAT;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A task that has a date and time associated to it.
 *
 * @author jq1836
 */
public abstract class TimedTask extends Task {
    /**
     * The date and time associated to the task.
     */
    private final LocalDateTime dateTime;

    /**
     * Constructs a task that is not done and has a date and time associated to it.
     *
     * @param description A string that describes the task.
     * @param dateTime    A LocalDateTime that represents the date and time associated to the task.
     */
    public TimedTask(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Constructs a timed task that may be done depending on the isDone argument.
     *
     * @param description A string that describes the task.
     * @param dateTime    A LocalDateTime that represents the date and time associated to the task.
     * @param isDone      A boolean value that describes whether the task is done.
     */
    public TimedTask(String description, LocalDateTime dateTime, boolean isDone) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Returns true if the argument date and the date associated with the task are the same and false otherwise.
     *
     * @param date The date to compare the task date with.
     * @return A boolean that describes whether the argument date and task date are matching.
     */
    public boolean hasMatchingDate(LocalDate date) {
        LocalDate taskDate = dateTime.toLocalDate();
        return taskDate.equals(date);
    }

    /**
     * Returns a string that corresponds to the task date.
     *
     * @return A string that corresponds to the task date.
     */
    protected String getDateString() {
        return dateTime.format(DATE_OUTPUT_FORMAT);
    }

    /**
     * Returns a string that corresponds to the task time.
     *
     * @return A string that corresponds to the task time.
     */
    protected String getTimeString() {
        String timeString = dateTime.format(TIME_OUTPUT_FORMAT);
        return String.format("%shrs", timeString);
    }

    @Override
    public String encode() {
        String dateTimeString = dateTime.format(DATE_TIME_INPUT_FORMAT);
        return String.format("%s%s", super.encode(), dateTimeString);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj) || !(obj instanceof TimedTask)) {
            return false;
        }
        TimedTask task = (TimedTask) obj;
        return task.dateTime.equals(dateTime);
    }
}
