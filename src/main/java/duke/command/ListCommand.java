package duke.command;

import duke.task.*;
import duke.ui.Ui;
import duke.data.Storage;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showListMessage(taskList.enumerateList());
    }
}
