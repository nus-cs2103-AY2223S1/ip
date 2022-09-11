package duke.command;

import duke.Constants;
import duke.DukeException;
import duke.Response;
import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Terminates the program.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.setResponse(new Response(Constants.BYE_MESSAGE, true, false));
        storage.saveTasksInStorage(taskList.getList());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
