package maria.command;

import maria.Storage;
import maria.Ui;
import maria.task.TaskList;

public class CommandUnknown extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showText("There is a mistake in the command given, please try again.");
    }
}
