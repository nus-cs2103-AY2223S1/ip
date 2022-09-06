package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class UnmarkCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

    }

}
