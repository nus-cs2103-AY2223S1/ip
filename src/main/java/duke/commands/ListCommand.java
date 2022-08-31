package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Represents an executable command prints an overview of all added tasks and their status.
 */
public class ListCommand extends Command {
    /** Command identifier used by Parser **/
    public static final String COMMAND_WORD = "list";

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) {
        String msg;
        if (taskList.size() <= 0) {
            msg = "You currently have no task in your list! Great job for completing all your tasks :-)";
        } else {
            msg = "Here are the tasks in your list:\n" + taskList;
        }

        CommandResult cr = new CommandResult(msg);
        return cr;
    }
}
