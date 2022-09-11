package duke.taskmanager.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.chatbot.commands.exceptions.EmptyTaskException;
import duke.chatbot.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.commands.exceptions.InvalidDeadlineException;

/**
 * Deadline Task is a Task with the additional deadline information.
 */
public class DeadlineTask extends Task {
    public static final String TASK_DELIMITER = "/by ";
    private static final String TASK_TYPE = "D";
    private final String dateFormat;
    private LocalDateTime deadline;

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
            throw new InvalidDeadlineException(this.dateFormat);
        }
        assert !(super.getTaskName().equals("")) : "Task should not be empty";
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
            throw new InvalidDeadlineException(this.dateFormat);
        }
        assert !(super.getTaskName().equals("")) : "Task should not be empty";
    }

    /**
     * Sets the deadline of the task with the given string
     *
     * @param deadline string of the deadline
     * @throws InvalidDeadlineException when date format is invalid
     */
    private void setDeadline(String deadline) throws InvalidDeadlineException {
        try {
            this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern(this.dateFormat));
        } catch (DateTimeParseException exception) {
            throw new InvalidDeadlineException(this.dateFormat);
        }
    }

    /**
     * Updates the task with the given arguments
     *
     * @param arguments string of arguments to update the task
     * @throws InvalidArgumentsException when the arguments given are empty or date format is invalid
     */
    @Override
    public void update(String arguments) throws InvalidArgumentsException, InvalidDeadlineException {
        if (arguments.length() <= 0) {
            throw new InvalidArgumentsException();
        }
        String[] argumentList = arguments.split(TASK_DELIMITER);
        if (argumentList.length <= 0) {
            throw new InvalidArgumentsException();
        }

        if (arguments.startsWith(TASK_DELIMITER)) {
            setDeadline(argumentList[1]);
        } else {
            if (argumentList.length == 1) {
                setTaskName(argumentList[0]);
            } else if (argumentList.length == 2) {
                setTaskName(argumentList[0]);
                setDeadline(argumentList[1]);
            } else {
                throw new InvalidArgumentsException();
            }
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
