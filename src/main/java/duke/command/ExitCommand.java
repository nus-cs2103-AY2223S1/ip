package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Represents a command to end the program.
 */
public class ExitCommand extends Command {
    /**
     * Class constructor. Construct an object representing exitCommand.
     * @param taskList The target taskList that will be deleted task.
     * @param storage The object containing the corresponding file.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        super.setIsExitTrue();
        return "Goodbye. Hope to see you soon.";
    }
}
