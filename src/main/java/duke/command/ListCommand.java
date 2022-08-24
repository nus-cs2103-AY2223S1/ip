package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

public class ListCommand extends Command{
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.printTaskList(taskList, "Here are the tasks in your list:");
    }
}
