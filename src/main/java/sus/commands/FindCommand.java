package sus.commands;

import sus.storage.StorageFile;
import sus.task.TaskList;
import sus.ui.TextUi;

/**
 * Find a task by searching for a keyword.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Find tasks by searching a keyword.\n"
            + "\tEx: " + COMMAND_WORD + " <keyword>";

    private final String keyword;

    /**
     * Constructor.
     *
     * @param keyword keyword to search for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(TaskList taskList, TextUi textUi, StorageFile storage) {
        return new CommandResult(TaskList.convertListOfTasksToString(taskList.findTasks(keyword)));
    }
}
