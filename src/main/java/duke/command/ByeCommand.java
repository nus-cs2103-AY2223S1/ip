package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This command terminates Duke when executed with a goodbye message.
 */
public class ByeCommand extends Command {
    /** The keyword to run FindCommand. */
    public static final String COMMAND_NAME = "bye";

    /**
     * Sole constructor of ByeCommand.
     */
    public ByeCommand() {

    }

    /**
     * Displays a goodbye message to the specified Ui parameter.
     *
     * @param tasks the specified TaskList object.
     * @param ui the specified Ui object.
     * @param storage the specified Storage object.
     */
    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Returns true as ByeCommand is a terminating Command.
     *
     * @return true.
     */
    @Override
    public boolean isTerminator() {
        return true;
    }
}
