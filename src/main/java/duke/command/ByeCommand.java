package duke.command;

import java.util.concurrent.TimeUnit;

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
        System.exit(0);
        return "Bye!";
    }

}
