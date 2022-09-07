package duke.command;

import duke.gui.Ui;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Represents the application terminating command that is executed when the user inputs bye.
 *
 * @author njxue
 * @version v0.1
 */
public class ByeCommand extends Command {
    /**
     * Executes the bye command. Terminates the current Duke session.
     *
     * @param tasks TaskList containing the list of tasks.
     * @param ui Ui object which interacts with the user.
     * @param storage Storage object which loads and saves tasks.
     * @return Message to be shown to the user after successful execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "^_^ Goodbye!";
    }

    /**
     * Returns the format of the bye command.
     *
     * @return The format of the bye command.
     */
    public static String getFormat() {
        return "bye";
    }

    /**
     * Returns true, because bye is an application terminating command.
     *
     * @return True, terminating the Duke application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
