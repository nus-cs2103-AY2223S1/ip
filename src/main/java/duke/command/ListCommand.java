package duke.command;

import duke.Constants;
import duke.DukeException;
import duke.Response;
import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Lists all the task in tasklist.
 */
public class ListCommand extends Command {
    private String response;

    public ListCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Response response = new Response(ui.listToString(taskList.getList()), false, false);
        ui.setResponse(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
