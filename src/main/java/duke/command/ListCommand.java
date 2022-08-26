package duke.command;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;

public class ListCommand extends Command{

    /**
     * Outputs the current task list.
     *
     * @param storage {@inheritDoc}
     * @param ui {@inheritDoc}
     * @param taskList {@inheritDoc}
     */
    @Override
    public void execute(Storage storage, UI ui, TaskList taskList) {
        ui.showTasks();
        taskList.read();
    }
}
