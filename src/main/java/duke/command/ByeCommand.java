package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.storage.Storage;

/**
 * Can be executed to exit the Duke program.
 */
public class ByeCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "You can't leave!";
    }


}
