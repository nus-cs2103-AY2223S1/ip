package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Bye command class to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Shows bye message.
     *
     * @param tasks Tasks to be executed.
     * @return The goodbye message
     */
    @Override
    public String execute(TaskList tasks) {
        return Ui.showByeMessage();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
