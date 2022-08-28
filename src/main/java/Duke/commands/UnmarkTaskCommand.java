package Duke.commands;

import Duke.exceptions.DukeException;
import Duke.storage.Storage;
import Duke.tasks.TaskList;
import Duke.ui.UI;

public class UnmarkTaskCommand extends Command {

    private final int taskID;

    public UnmarkTaskCommand(String input) throws DukeException {
        if (!checkValid(input)) throw new DukeException(" ☹ OOPS!!! Please enter task to unmark.");
        this.taskID = Integer.parseInt(input.substring(7)) - 1;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.unmarkTask(this.taskID);
        ui.unmarkTaskMessage(taskList.getTask(this.taskID));
        storage.store(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
