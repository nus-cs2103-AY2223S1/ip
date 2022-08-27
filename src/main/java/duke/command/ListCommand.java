package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ListCommand implements Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.dukePrint(tasks.toPrintFormat());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
