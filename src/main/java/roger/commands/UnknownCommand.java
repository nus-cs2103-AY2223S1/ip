package roger.commands;

import roger.storage.Storage;
import roger.tasks.TaskList;

/**
 * Encapsulates a command that the program does not understand.
 */
public class UnknownCommand extends Command {

    /**
     * Notify the user that Roger does not understand the command.
     *
     * @param tasks The TaskList of the Roger program.
     * @param storage The storage to read and load to.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Uncle really don't understand.";
    }
}
