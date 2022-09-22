package ado.command;

import ado.AdoException;
import ado.Response;
import ado.Ui;
import ado.storage.Storage;
import ado.task.Task;
import ado.task.TaskList;

/**
 * Removes task at specific index from tasklist.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AdoException {
        if (index > taskList.listSize()) {
            throw new AdoException("There is no " + index + " index in the list.\n");
        }
        Task curr = taskList.getTaskAtIndex(index - 1);
        taskList.removeTaskAtIndex(index - 1);
        String message = " - Removed this task:\n" + curr + "\nNow you have " + taskList.listSize()
                + " tasks in the list\n";
        Response response = new Response(message, false, false);
        ui.setResponse(response);
        storage.saveTasksInStorage(taskList.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
