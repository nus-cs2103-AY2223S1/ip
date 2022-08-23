package jean.command;

import jean.storage.Storage;
import jean.task.TaskList;
import jean.ui.Ui;

import java.io.IOException;

/**
 * Represents a Command which instructs Jean to terminate.
 */
public class ExitCommand extends Command {

    /**
     * Ends Jean and saves the task list to memory.
     *
     * @param taskList List of tasks to be saved.
     * @param ui The ui object that prints feedback to the user interface.
     * @param storage The storage object that saves to or extracts from data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        storage.saveFile(taskList);
        ui.end();
    }
}
