package duke.command;

import duke.task.*;
import duke.ui.Ui;
import duke.data.Storage;

public class ListCommand extends Command {

    /**
     * Displays a list of tasks.
     *
     * @param taskList TaskList object containing ArrayList of Task.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showListMessage(taskList.enumerateList());
    }
}
