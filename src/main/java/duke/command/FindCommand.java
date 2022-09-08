package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * Representation of the find command.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Checks if this is an exit command.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the FindCommand.
     * @param tasks TaskList to find tasks that have keyword.
     * @param ui UI to print to users.
     * @param storage Storage to save and load TaskList.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        TaskList keywordTasks = tasks.findTask(keyword);
        ui.printTaskList(keywordTasks, "Here are the matching tasks in your list:");
    }
    /**
     * Returns message from FindCommand.
     * @param tasks TaskList to find tasks that have keyword.
     * @param ui UI to print to users.
     * @param storage Storage to save and load TaskList.
     */
    @Override
    public String getResponse(TaskList tasks, UI ui, Storage storage) {
        TaskList keywordTasks = tasks.findTask(keyword);
        return ui.getTaskListResponse(keywordTasks, "Here are the matching tasks in your list:");
    }
}
