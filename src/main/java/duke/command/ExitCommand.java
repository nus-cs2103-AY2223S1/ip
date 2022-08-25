package duke.command;

import duke.storage.TaskRecords;
import duke.ui.BotUI;

/**
 * Represents an exit command of task. A <code>ExitCommand</code> object
 * will allow user to exit from duke.
 */

public class ExitCommand extends Command {

    /**
     * Constructor of ExitCommand
     *
     * @param command command of the user input
     */
    public ExitCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskRecords taskList, BotUI ui) {

    }

    /**
     * Returns the true/false of the command exit status that
     * will cause duke stop running
     *
     * @return the true/false of the command exit status
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
