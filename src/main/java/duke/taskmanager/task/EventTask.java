package duke.taskmanager.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event Task is a Task with the additional event time information.
 */
public class EventTask extends Task {
    public static final String TASK_DELIMITER = "/at ";
    public static final String TASK_TYPE = "E";
    private LocalDateTime eventTime;

    /**
     * Creates a new event task with information indicating the name of the task and
     * the event time of the task provided in the form of a date specified by the dateFormat.
     * Default completion status of the task is false.
     *
     * @param taskName string of the name of the task
     * @param eventTime string of the event time of the task in the format of dateFormat
     */
    public EventTask(String taskName, LocalDateTime eventTime) {
        super(TASK_TYPE, taskName);
        this.eventTime = eventTime;
        assert !(super.getTaskName().equals("")) : "Task should not be empty";
    }

    /**
     * Creates a new event task with information indicating the name of the task and
     * the event time of the task provided in the form of a date specified by the dateFormat.
     *
     * @param taskName string of the name of the task
     * @param eventTime string of the event time of the task in the format of dateFormat
     * @param isCompleted boolean of the completion status of the task.
     */
    public EventTask(String taskName, LocalDateTime eventTime, boolean isCompleted) {
        super(TASK_TYPE, taskName, isCompleted);
        this.eventTime = eventTime;
        assert !(super.getTaskName().equals("")) : "Task should not be empty";
    }

    /**
     * Returns the event time of the task.
     *
     * @return localDateTime of the event time of the task
     */
    private LocalDateTime getEventTime() {
        return getEventTime();
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
     * Updates the event task with the updated task name and event time.
     *
     * @param arguments the update task name and event time
     */
    @Override
    public void update(String... arguments) {
        if (arguments[0].length() != 0) {
            this.setTaskName(arguments[0]);
        }
        if (arguments[1].length() != 0) {
            this.eventTime = LocalDateTime.parse(arguments[1], DateTimeFormatter.ofPattern(arguments[2]));
        }
    }

    /**
     * Returns the details of the task to be displayed by the chatbot.
     *
     * @return details of the task
     */
    @Override
    public String toString() {
        return super.toString() + " (at: "
                + this.eventTime.getDayOfMonth() + " "
                + this.eventTime.getMonth() + " "
                + this.eventTime.getYear() + " | "
                + this.eventTime.getHour() + ":" + String.format("%02d", this.eventTime.getMinute()) + ")";
    }
}
