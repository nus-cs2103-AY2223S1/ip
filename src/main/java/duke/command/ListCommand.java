package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class ListCommand extends Command {

    /**
     * Prints the list of tasks.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayList(tasks.getArr());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
