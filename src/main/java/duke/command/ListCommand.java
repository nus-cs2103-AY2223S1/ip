package duke.command;

import duke.DukeException;
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
        response = ui.listToString(taskList.getList());
        System.out.println(ui.listToString(taskList.getList()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
