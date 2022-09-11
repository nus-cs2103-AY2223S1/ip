package ado.command;

import ado.Constants;
import ado.AdoException;
import ado.Response;
import ado.Ui;
import ado.storage.Storage;
import ado.task.TaskList;

/**
 * Terminates the program.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AdoException {
        ui.setResponse(new Response(Constants.BYE_MESSAGE, true, false));
        storage.saveTasksInStorage(taskList.getList());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
