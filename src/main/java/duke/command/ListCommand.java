package duke.command;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;

/**
 * Created when user inputs "list".
 */
public class ListCommand extends Command {

    /**
     * Outputs the current task list.
     *
     * @param storage {@inheritDoc}
     * @param ui {@inheritDoc}
     * @param taskList {@inheritDoc}
     */
    @Override
    public String execute(Storage storage, UI ui, TaskList taskList) {
        return ui.showTasks() + taskList.read();
    }
}
