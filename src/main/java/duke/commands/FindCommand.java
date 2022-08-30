package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Represents an executable command that finds a Task from the specified TaskList.
 */
public class FindCommand extends Command {
    /** Command identifier used by Parser **/
    public static final String COMMAND_WORD = "find";
    private final String keyword;

    /**
     * Initializes a new FindCommand instance.
     *
     * @param keyword The keyword to match
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList results = taskList.findMatchingTasks(this.keyword);
        String msgBegin = "Here are the matching tasks in your list:\n";
        String msg = msgBegin;
        if (results.size() > 0) {
            msg += results.toString();
        } else {
            msg = "There are no matching tasks in your list.";
        }

        CommandResult cr = new CommandResult(msg);
        return cr;
    }
}
