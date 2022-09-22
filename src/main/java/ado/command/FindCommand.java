package ado.command;

import ado.AdoException;
import ado.Response;
import ado.Ui;
import ado.storage.Storage;
import ado.task.TaskList;

/**
 * Finds task with specific word in tasklist
 */
public class FindCommand extends Command {
    private String text;

    public FindCommand(String text) {
        this.text = text;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AdoException {
        String message = ui.matchingTasklistToString(taskList.getList(), text);
        Response response = new Response(message, false, false);
        ui.setResponse(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
