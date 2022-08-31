package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The WelcomeCommand class that is executed everytime duke is started
 */
public class WelcomeCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.farewell();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
