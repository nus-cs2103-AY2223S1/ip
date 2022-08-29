package justin.command;

import justin.Storage;
import justin.TaskList;
import justin.Ui;

/**
 * Represents a command that is called when
 * there is no data in the text file.
 * @author Justin Cheng.
 */
public class NewCommand extends Command {

    /**
     * Executes the command by doing nothing.
     * @param list The TaskList to carry out operations.
     * @param ui The Ui to send outputs.
     * @param storage The Storage to save changes.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {}
}
