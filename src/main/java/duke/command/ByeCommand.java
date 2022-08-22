package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        super.endApp();
        ui.printExit();
    }
}
