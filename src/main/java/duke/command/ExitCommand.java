package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.exit();
    }
}
