package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class AddTaskCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
