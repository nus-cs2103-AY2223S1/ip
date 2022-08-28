package Duke.commands;

import Duke.exceptions.DukeException;
import Duke.storage.Storage;
import Duke.tasks.TaskList;
import Duke.ui.UI;

public class DeleteTaskCommand extends Command {

    private final int taskID;

    public DeleteTaskCommand(String input) throws DukeException {
        if (!checkValid(input)) throw new DukeException(" ☹ OOPS!!! Please enter task to delete.");
        this.taskID = Integer.parseInt(input.substring(7)) - 1;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.deleteTaskMessage(taskList.getTask(taskID), taskList.size());
        taskList.deleteTask(taskID);
        storage.store(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
