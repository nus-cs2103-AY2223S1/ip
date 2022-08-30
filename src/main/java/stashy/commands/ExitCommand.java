package stashy.commands;

import stashy.data.exception.StashyException;
import stashy.data.task.TaskList;
import stashy.storage.Storage;
import stashy.ui.Ui;

/**
 * A Command specifically designed to exit the application.
 */
public class ExitCommand extends Command {
    public static final String KEYWORD = "bye";

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Exits the conversation before saving the updated task list
     * into the storage and quitting the application.
     *
     * @param tasks The list of tasks
     * @param ui The UI of this application
     * @param storage The storage used for this application
     * @throws StashyException If any exception is caught
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        ui.showGoodbye();
        storage.writeTaskListToFile(tasks);
    }
}
