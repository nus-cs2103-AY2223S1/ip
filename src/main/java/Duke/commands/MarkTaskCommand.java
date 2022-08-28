package Duke.commands;

import Duke.exceptions.DukeException;
import Duke.storage.Storage;
import Duke.tasks.TaskList;
import Duke.ui.UI;

public class MarkTaskCommand extends Command {

    private final int taskID;

    public MarkTaskCommand(String input) throws DukeException {
        if (!checkValid(input)) throw new DukeException(" ☹ OOPS!!! Please enter task to unmark.");
        this.taskID = Integer.parseInt(input.substring(5)) - 1;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.markTask(this.taskID);
        ui.markTaskMessage(taskList.getTask(this.taskID));
        storage.store(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
