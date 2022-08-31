package duke.taskmanager.task;

import duke.taskmanager.exceptions.EmptyTaskException;
import duke.taskmanager.exceptions.InvalidDeadlineException;
import duke.taskmanager.exceptions.InvalidEventException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    private static final String TASK_TYPE = "E";
    private final LocalDateTime taskTime;
    private final String dateFormat;

    /**
     * Creates a new event task with information indicating the name of the task and
     * the event time of the task provided in the form of a date specified by the dateFormat.
     * Default completion status of the task is false.
     *
     * @param taskName string of the name of the task
     * @param taskTime string of the event time of the task in the format of dateFormat
     * @param dateFormat string of the accepted format of dates.
     * @throws EmptyTaskException if taskName is empty
     * @throws InvalidEventException if the format of taskTime does not follow dateFormat
     */
    public EventTask(String taskName, String taskTime, String dateFormat) throws EmptyTaskException, InvalidEventException {
        super(taskName);
        this.dateFormat = dateFormat;
        if (super.getTaskName().equals("")) {
            throw new EmptyTaskException();
        }
        try {
            this.taskTime = LocalDateTime.parse(taskTime, DateTimeFormatter.ofPattern(dateFormat));
        } catch (DateTimeParseException exception) {
            throw new InvalidEventException(dateFormat);
        }
    }

    /**
     * Creates a new event task with information indicating the name of the task and
     * the event time of the task provided in the form of a date specified by the dateFormat.
     *
     * @param taskName string of the name of the task
     * @param taskTime string of the event time of the task in the format of dateFormat
     * @param status boolean of the completion status of the task.
     * @param dateFormat string of the accepted format of dates.
     * @throws EmptyTaskException if taskName is empty
     * @throws InvalidEventException if the format of taskTime does not follow dateFormat
     */
    public EventTask(String taskName, String taskTime, boolean status, String dateFormat) throws EmptyTaskException, InvalidEventException {
        super(taskName, status);
        this.dateFormat = dateFormat;
        if (super.getTaskName().equals("")) {
            throw new EmptyTaskException();
        }
        try {
            this.taskTime = LocalDateTime.parse(taskTime);
        } catch (DateTimeParseException exception) {
            throw new InvalidEventException(dateFormat);
        }
    }

    /**
     * Returns the event time of the task in a nice format.
     *
     * @return event time of the task
     */
    public String getTaskTime() {
        return (taskTime.getDayOfMonth() + " " +
                taskTime.getMonth() + " " +
                taskTime.getYear() + " | " +
                taskTime.getHour() + ":" +
                taskTime.getMinute());
    }

    /**
     * Returns whether the task is empty. Returns false as this is not an empty task.
     *
     * @return false
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Formats the details of the task into a format that can be read and written by
     * the task manager.
     *
     * @return details of the task in a string format readable and writable by taskManager
     */
    @Override
    public String getFormattedString() {
        return TASK_TYPE + " | " +
                (getStatus() ? 1 : 0) + " | " +
                getTaskName() + " | " +
                this.taskTime + "\n";
    }

    /**
     * Returns the details of the task to be displayed by the chatbot.
     *
     * @return details of the task
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (at:" + getTaskTime() + ")";
    }
}