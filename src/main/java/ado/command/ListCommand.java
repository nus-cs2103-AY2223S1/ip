package ado.command;

import ado.AdoException;
import ado.Response;
import ado.Ui;
import ado.storage.Storage;
import ado.task.TaskList;

/**
 * Lists all the task in tasklist.
 */
public class ListCommand extends Command {
    private String response;

    public ListCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AdoException {
        Response response = new Response(ui.listToString(taskList.getList()), false, false);
        ui.setResponse(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
