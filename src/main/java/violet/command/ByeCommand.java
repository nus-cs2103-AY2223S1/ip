package violet.command;

import violet.TaskList;
import violet.Ui;

/**
 * ByeCommand class to execute the bye command.
 */
public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        this.response = ui.exit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
