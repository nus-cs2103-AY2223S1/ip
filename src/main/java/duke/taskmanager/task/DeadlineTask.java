package duke.taskmanager.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.taskmanager.exceptions.EmptyTaskException;
import duke.taskmanager.exceptions.InvalidDeadlineException;

/**
 * Deadline Task is a Task with the additional deadline information.
 */
public class DeadlineTask extends Task {
    private static final String TASK_TYPE = "D";
    private final LocalDateTime deadline;
    private final String dateFormat;

    /**
     * Creates a new deadline task with information indicating the name of the task and
     * the deadline of the task provided in the form of a date specified by the dateFormat.
     * Default completion status of the task is false.
     *
     * @param taskName string of the name of the task
     * @param deadline string of the deadline of the task in the format of dateFormat
     * @param dateFormat string of the accepted format of dates.
     * @throws EmptyTaskException if taskName is empty
     * @throws InvalidDeadlineException if the format of taskTime does not follow dateFormat
     */
    public DeadlineTask(String taskName, String deadline, String dateFormat)
            throws EmptyTaskException, InvalidDeadlineException {
        super(TASK_TYPE, taskName);
        this.dateFormat = dateFormat;
        try {
            this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern(this.dateFormat));
        } catch (DateTimeParseException exception) {
            throw new InvalidDeadlineException(dateFormat);
        }
    }

    /**
     * Creates a new deadline task with information indicating the name of the task and
     * the deadline of the task provided in the form of a date specified by the dateFormat.
     *
     * @param taskName string of the name of the task
     * @param deadline string of the deadline of the task in the format of dateFormat
     * @param isCompleted boolean of the completion status of the task.
     * @param dateFormat string of the accepted format of dates.
     * @throws EmptyTaskException if taskName is empty
     * @throws InvalidDeadlineException if the format of taskTime does not follow dateFormat
     */
    public DeadlineTask(String taskName, String deadline, boolean isCompleted, String dateFormat)
            throws EmptyTaskException, InvalidDeadlineException {
        super(TASK_TYPE, taskName, isCompleted);
        this.dateFormat = dateFormat;
        try {
            this.deadline = LocalDateTime.parse(deadline);
        } catch (DateTimeParseException exception) {
            throw new InvalidDeadlineException(dateFormat);
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
        return super.getFormattedString(attributeSeparator) + attributeSeparator + this.deadline;
    }

    /**
     * Returns the details of the task to be displayed by the chatbot.
     *
     * @return details of the task
     */
    @Override
    public String toString() {
        return super.toString() + " (by: "
                + this.deadline.getDayOfMonth() + " "
                + this.deadline.getMonth() + " "
                + this.deadline.getYear() + " | "
                + this.deadline.getHour() + ":" + String.format("%02d", this.deadline.getMinute()) + ")";
    }
}
