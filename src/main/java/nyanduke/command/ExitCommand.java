package nyanduke.command;

import nyanduke.Storage;
import nyanduke.Ui;
import nyanduke.task.TaskList;

/**
 * The ExitCommand class represents
 * a command that tells NyanDuke to exit.
 */
public class ExitCommand extends Command {
    /**
     * Exits NyanDuke.
     *
     * @param tasks The specified TaskList involved with the command.
     * @param ui The specified Ui involved with the command.
     * @param storage The specified Storage involved with the command.
     * @return A message that NyanDuke is exiting.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }
}
