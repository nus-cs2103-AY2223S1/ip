package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ListCommand extends Command{

    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
