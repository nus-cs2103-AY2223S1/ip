package duke.command;

import duke.exception.UnexpectedDateTimeFormatException;

import duke.task.Deadline;

/**
 * Represents a DeadlineCommand object to be called when user inputs 'deadline'.
 */
public class DeadlineCommand extends TaskCommand {
    public static final String COMMAND_WORD = "deadline";

    /**
     * Constructs a Deadline object with description, date and time.
     * @param description description of task.
     * @param dateTime date and time when the task should be done by.
     * @throws UnexpectedDateTimeFormatException when date and time is not in dd/MM/yyyy HHmm format.
     */
    public DeadlineCommand(String description, String dateTime) throws UnexpectedDateTimeFormatException {
        super(new Deadline(description, dateTime));
    }
}
