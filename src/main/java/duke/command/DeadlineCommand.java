package duke.command;

import duke.task.Deadline;
import duke.task.TaskList;

/**
 * Represents a deadline command
 */
public class DeadlineCommand implements Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add a deadline task. Parameters: DESCRIPTION DATETIME. Example: " + COMMAND_WORD
            + " return book /by 2022-08-22";

    private String description;
    private String dateTime;

    /**
     * Constructor for a {@link DeadlineCommand}
     *
     * @param description Description for the event
     * @param dateTime    Date and time for the event
     */
    public DeadlineCommand(String description, String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Executes a command
     *
     * @param taskList
     */
    @Override
    public String execute(TaskList taskList) {
        Deadline deadline = new Deadline(description, dateTime);
        return taskList.add(deadline);
    }
}
