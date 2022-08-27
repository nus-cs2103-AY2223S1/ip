package duke.commands;

import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Find a task by searching for a keyword.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Find tasks by searching a keyword.\n"
            + "\tEx.: " + COMMAND_WORD + " <keyword>";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(TaskList taskList, TextUi textUi, StorageFile storage) {
        return new CommandResult(TaskList.convertListOfTasksToString(taskList.findTasks(keyword)));
    }
}
