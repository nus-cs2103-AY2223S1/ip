package justin.command;

import justin.MainWindow;
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
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage, MainWindow mw) {
        super.isExit = true;
    }

    @Override
    public String getMessage(TaskList list, Ui ui) {
        return ui.goodbye();
    }
}
