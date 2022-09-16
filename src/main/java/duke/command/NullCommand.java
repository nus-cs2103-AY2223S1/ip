package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.storage.Storage;

/**
 * Represents a command that does nothing.
 */
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
