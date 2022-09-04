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
     * Exits Duke.
     *
     * @param tasks The specified TaskList involved with the command.
     * @param ui The specified Ui involved with the command.
     * @param storage The specified Storage involved with the command.
     * @return A message that Duke is exiting.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }
}
