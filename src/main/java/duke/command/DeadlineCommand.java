package duke.command;

import duke.task.Deadline;
import duke.task.TaskList;

/**
 * This class encapsulates a deadline command from the user.
 */
public class DeadlineCommand extends AddCommand {
    // Solution below adapted from https://github.com/teikjun/duke
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_SEPARATOR = "/by";

    /**
     * Creates a DeadlineCommand with the given TaskList and Deadline.
     *
     * @param taskList The TaskList.
     * @param deadline The Deadline.
     */
    public DeadlineCommand(TaskList taskList, Deadline deadline) {
        super(taskList, deadline);
    }
}
