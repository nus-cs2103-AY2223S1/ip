package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand implements Command {
    public ByeCommand() {}

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printFarewellMessage();
    }
}
