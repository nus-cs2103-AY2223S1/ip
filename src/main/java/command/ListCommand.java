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
        StringBuffer contents = new StringBuffer();
        openList(contents);
        addTasksToList(contents, taskList);
        closeList(contents, taskList);
        ui.showMessage(contents.toString());
    }

    private void openList(StringBuffer list) {
        list.append("Tasks in your list are:\n________\n");
    }

    private void addTasksToList(StringBuffer list, TaskList taskList) {
        list.append(taskList.getContents());
    }

    private void closeList(StringBuffer list, TaskList taskList) {
        list.append("________\n");
        list.append("Total: " + taskList.getSize());
    }
}
