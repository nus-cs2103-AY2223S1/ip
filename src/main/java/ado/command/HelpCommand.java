package ado.command;

import ado.AdoException;
import ado.Constants;
import ado.Response;
import ado.Ui;
import ado.storage.Storage;
import ado.task.TaskList;

/**
 * Prints out all commands that chatbot has.
 */
public class HelpCommand extends Command {
    public HelpCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AdoException {
        Response response = new Response(Constants.HELP_MESSAGE, false, false);
        ui.setResponse(response);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
