package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
    public void execute(TaskList list, Ui ui, Storage storage) {
        super.isExit = true;
        ui.goodbye();
    }
}
