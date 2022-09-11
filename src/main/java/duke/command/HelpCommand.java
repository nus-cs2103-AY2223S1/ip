package duke.command;

import duke.Constants;
import duke.DukeException;
import duke.Response;
import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Prints out all commands that chatbot has.
 */
public class HelpCommand extends Command {
    public HelpCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Response response = new Response(Constants.HELP_MESSAGE, false, false);
        ui.setResponse(response);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
