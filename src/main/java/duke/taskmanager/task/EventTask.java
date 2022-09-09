package duke.taskmanager.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.taskmanager.exceptions.EmptyTaskException;
import duke.taskmanager.exceptions.InvalidEventException;

/**
 * Event Task is a Task with the additional event time information.
 */
public class EventTask extends Task {
    private static final String TASK_TYPE = "E";
    private final LocalDateTime eventTime;
    private final String dateFormat;

    /**
     * Creates a new event task with information indicating the name of the task and
     * the event time of the task provided in the form of a date specified by the dateFormat.
     * Default completion status of the task is false.
     *
     * @param taskName string of the name of the task
     * @param eventTime string of the event time of the task in the format of dateFormat
     * @param dateFormat string of the accepted format of dates.
     * @throws EmptyTaskException if taskName is empty
     * @throws InvalidEventException if the format of taskTime does not follow dateFormat
     */
    public EventTask(String taskName, String eventTime, String dateFormat)
            throws EmptyTaskException, InvalidEventException {
        super(TASK_TYPE, taskName);
        this.dateFormat = dateFormat;
        try {
            this.eventTime = LocalDateTime.parse(eventTime, DateTimeFormatter.ofPattern(this.dateFormat));
        } catch (DateTimeParseException exception) {
            throw new InvalidEventException(dateFormat);
        }
    }

    /**
     * Creates a new event task with information indicating the name of the task and
     * the event time of the task provided in the form of a date specified by the dateFormat.
     *
     * @param taskName string of the name of the task
     * @param eventTime string of the event time of the task in the format of dateFormat
     * @param isCompleted boolean of the completion status of the task.
     * @param dateFormat string of the accepted format of dates.
     * @throws EmptyTaskException if taskName is empty
     * @throws InvalidEventException if the format of taskTime does not follow dateFormat
     */
    public EventTask(String taskName, String eventTime, boolean isCompleted, String dateFormat)
            throws EmptyTaskException, InvalidEventException {
        super(TASK_TYPE, taskName, isCompleted);
        this.dateFormat = dateFormat;
        try {
            this.eventTime = LocalDateTime.parse(eventTime);
        } catch (DateTimeParseException exception) {
            throw new InvalidEventException(dateFormat);
        }
    }

    /**
     * Formats the details of the task into a format that can be read and written by
     * the task manager.
     *
     * @return details of the task in a string format readable and writable by taskManager
     */
    @Override
    public String getFormattedString(String attributeSeparator) {
        return super.getFormattedString(attributeSeparator) + attributeSeparator + this.eventTime;
    }

    /**
     * Returns the details of the task to be displayed by the chatbot.
     *
     * @return details of the task
     */
    @Override
    public String toString() {
        return super.toString() + " (by: "
                + this.eventTime.getDayOfMonth() + " "
                + this.eventTime.getMonth() + " "
                + this.eventTime.getYear() + " | "
                + this.eventTime.getHour() + ":" + String.format("%02d", this.eventTime.getMinute()) + ")";
    }
}
