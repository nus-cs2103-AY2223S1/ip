package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Terminates the chatbot program after execution, and saves the current tasks to some local storage
 */
public class ByeCommand extends Command {
    /**
     * Terminates the chatbot program and saves current task to the local storage
     * <p>
     * {@inheritDoc}
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        storage.saveTasks(tasks);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return true;
    }
}
