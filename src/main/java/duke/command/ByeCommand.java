package duke.command;

import duke.TaskList;
import duke.Ui;
/**
 * A class for the bye command.
 */
public class ByeCommand extends Command {

    /**
     * Returns as there is nothing to execute for bye command.
     *
     * @param taskList The taskList storing all tasks.
     * @param ui       The ui responsible for interactions with the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        return;
    }

    /**
     * Returns true as the command is bye.
     *
     * @return True as the command is bye.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
