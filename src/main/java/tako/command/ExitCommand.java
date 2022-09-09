package tako.command;

import tako.Storage;
import tako.TaskList;
import tako.Ui;

/**
 * Command for Tako to exit.
 */
public class ExitCommand extends Command {

    /**
     * Exits the program.
     *
     * @param tasks Task list.
     * @param ui Ui.
     * @param storage Task storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) { }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
