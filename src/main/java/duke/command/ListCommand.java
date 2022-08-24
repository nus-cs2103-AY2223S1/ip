package duke.command;

import duke.data.Storage;
import duke.task.*;
import duke.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showListMessage(taskList.enumerateList());
    }
}
