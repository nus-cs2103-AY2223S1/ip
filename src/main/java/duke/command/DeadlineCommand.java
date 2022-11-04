package duke.command;

import duke.task.Deadline;

/**
 * This command encapsulates a Deadline object and inserts it into
 * the task list when executed.
 */
public class DeadlineCommand extends TaskCommand {

    /** The keyword to run DeadlineCommand. */
    public static final String COMMAND_NAME = "deadline";

    /**
     * Constructs a DeadlineCommand object encapsulating the specified parameter.
     *
     * @param newDeadline the specified Deadline parameter.
     */
    public DeadlineCommand(Deadline newDeadline) {
        super(newDeadline);
    }
}
