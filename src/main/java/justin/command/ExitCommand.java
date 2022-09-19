package justin.command;

import justin.Storage;
import justin.TaskList;
import justin.Ui;

/**
 * Represents a command to exit the program.
 * @author Justin Cheng.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command by exiting the program.
     * @param list The TaskList to carry out operations.
     * @param ui The Ui to send outputs.
     * @param storage The Storage to save changes.
     * @return The String message of the Ui.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        super.isExit = true;
        return ui.getGoodbyeMessage();
    }
}
