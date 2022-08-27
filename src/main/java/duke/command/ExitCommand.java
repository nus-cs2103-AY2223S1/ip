package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        super.isExit = true;
        ui.goodbye();
    }
}
