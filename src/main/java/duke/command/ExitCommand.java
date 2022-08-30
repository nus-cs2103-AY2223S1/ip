package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The ExitCommand that helps the user exit the bot.
 *
 * @author Leong Jia Hao Daniel
 */
public class ExitCommand extends Command {

    /**
     * The constructor for the exit command.
     */
    public ExitCommand() {}

    /**
     * Executes the exit command.
     *
     * @param ui The ui class which handles the user interface.
     * @param storage The storage class which deals with the file.
     * @param taskList The tasklist that stores the tasks.
     * @return The String that duke says.
     * @throws DukeException throws if there is an error.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        return ui.formatMessage("Good Bye! See you soon! :)");
    }

    /**
     * Returns true if the command is an ExitCommand.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
