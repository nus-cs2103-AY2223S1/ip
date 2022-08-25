package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

/**
 * Lists all tasks in TaskList.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        StringBuffer contents = new StringBuffer("Tasks in your list are:\n________\n");
        contents.append(taskList.getContents());
        contents.append("________\n");
        contents.append("Total: " + taskList.getSize());
        ui.showMessage(contents.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
