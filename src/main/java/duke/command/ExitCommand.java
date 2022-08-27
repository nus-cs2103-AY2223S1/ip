package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ExitCommand implements Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.close();
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
