package ado.command;

import ado.AdoException;
import ado.Response;
import ado.Ui;
import ado.storage.Storage;
import ado.task.TaskList;

/**
 * Unmarks a task at a specific index in tasklist.
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AdoException {
        if (index > taskList.listSize()) {
            throw new AdoException("There is no " + index + " index in the list. \n");
        } else {
            taskList.unmarkTaskAtIndex(index - 1);
            String message = "[ ] I've marked this task as not done yet:\n" + taskList.getTaskAtIndex(index - 1) + "\n";
            Response response = new Response(message, false, false);
            ui.setResponse(response);
            storage.saveTasksInStorage(taskList.getList());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
