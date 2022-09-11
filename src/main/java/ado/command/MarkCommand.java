package ado.command;

import ado.AdoException;
import ado.Response;
import ado.Ui;
import ado.storage.Storage;
import ado.task.TaskList;

/**
 * Marks a task at a specific index in tasklist.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AdoException {
        if (index > taskList.listSize()) {
            throw new AdoException("There is no " + index + " index in the list. \n");
        } else {
            taskList.markTaskAtIndex(index - 1);
            String message = "[X] You've completed a task!\n" + taskList.getTaskAtIndex(index - 1) + "\n";
            Response response = new Response(message, false, false);
            ui.setResponse(response);

        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
