package duke.command;

import duke.data.Storage;
import duke.task.*;
import duke.ui.Ui;

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
