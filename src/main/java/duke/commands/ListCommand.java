package duke.commands;

import duke.ui.ListBox;
import duke.utils.Storage;
import duke.utils.TaskList;


/**
 * Represents an executable command prints an overview of all added tasks and their status.
 *
 * @author sikai00
 */
public class ListCommand extends Command {
    /** Command identifier used by Parser **/
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = "List all tasks:\n"
            + "    list\n";

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) {
        String msg;
        if (taskList.size() <= 0) {
            msg = "There's nothing in your list! I want to see your manager for wasting my time.";
        } else {
            msg = "Look at all these tasks you're never going to complete anyway:\n";
        }
        ListBox lb = ListBox.getListBox(taskList);
        return new CommandResult(msg, lb);
    }
}
