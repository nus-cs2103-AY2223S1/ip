package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Represents an executable command that finds a Task from the specified TaskList.
 *
 * @author sikai00
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
    public CommandResult execute(TaskList taskList, Storage storage) {
        TaskList results = taskList.findMatchingTasks(this.keyword);
        String msg = "Here are the matching tasks in your list:\n";
        if (results.size() > 0) {
            msg += results.toString();
        } else {
            msg = "There are no matching tasks in your list.";
        }
        return new CommandResult(msg);
    }
}
