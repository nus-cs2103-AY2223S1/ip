package duke.command;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;

public class ListCommand extends Command{

    @Override
    public void execute(Storage storage, UI ui, TaskList taskList) {
        ui.showTasks();
        taskList.read();
    }
}
