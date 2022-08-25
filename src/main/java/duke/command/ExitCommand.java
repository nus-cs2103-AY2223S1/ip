package duke.command;

import duke.Storage;
import duke.Ui;

import duke.task.TaskList;

/**
 * The ExitCommand class represents
 * a command that tells Duke to exit.
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }
}
