package duke.command;

import duke.DukeException;
import duke.storage.TaskRecords;
import duke.ui.BotUI;

/**
 * Represents an exit command of task. A <code>ExitCommand</code> object
 * will allow user to exit from duke.
 */

public class ExitCommand extends Command {

    /**
     * Constructs ExitCommand object
     *
     * @param command command of the user input
     */
    public ExitCommand(String command) {
        super(command);
    }
    /**
     * Returns the "bye" message through BotUI object.
     *
     * @param taskList stores the list of tasks
     * @param ui Object that responsible in returning necessary formatted String
     *           to print on the user interface
     * @return String of "bye" response through BotUI object.
     */
    @Override
    public String execute(TaskRecords taskList, BotUI ui) {
        return ui.sayBye();
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
