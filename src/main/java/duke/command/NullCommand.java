package duke.command;

import duke.storage.Storage;
import duke.TaskList;
import duke.Ui;

public class NullCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {}

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "?";
    }

}
