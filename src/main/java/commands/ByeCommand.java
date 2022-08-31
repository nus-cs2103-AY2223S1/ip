package commands;

import dukeegg.Storage;
import dukeegg.TaskList;
import dukeegg.Ui;

/**
 * Terminates the chatbot program after execution, and saves the current tasks to some local storage.
 */
public class ByeCommand extends Command {

    /**
     * Terminates the chatbot program and saves current task to the local storage.
     * <p>
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasks(tasks);
        return ui.showGoodbye();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return true;
    }
}
