package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Command to exit duke.
 */
public class ExitCommand extends Command {
    @Override
    public String getResponse(TaskList tasks, Storage storage) {
        storage.storeTasks(tasks);
        return "Baa! :( See you later.";
    }

    /**
     * Check if the command exit duke.
     * @return
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
