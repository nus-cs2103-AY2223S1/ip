package roger.commands;

import roger.storage.Storage;
import roger.tasks.TaskList;

/**
 * Encapsulates the command to exit Roger.
 */
public class ExitCommand extends Command {

    /**
     * Exit the Roger program.
     *
     * @param tasks The TaskList of the Roger program.
     * @param storage The storage to read and load to.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye bye niece and nephew";
    }

    /**
     * Returns true, signalling the Roger program to exit.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
