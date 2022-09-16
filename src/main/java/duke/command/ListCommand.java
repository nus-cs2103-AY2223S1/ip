package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.storage.Storage;

/**
 * Can be executed to display the list of tasks.
 */
public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.display(taskList.list());
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.list();
    }

}
