package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

/**
 * Command that represents a
 * find operation on TaskList.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        TaskList matchedTasks = taskList.findTasks(keyword);
        ListCommand listCommand = new ListCommand();
        listCommand.execute(matchedTasks, ui, storage);
    }
}
