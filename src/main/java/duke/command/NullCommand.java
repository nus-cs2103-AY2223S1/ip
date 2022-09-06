package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class NullCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

    }

}
