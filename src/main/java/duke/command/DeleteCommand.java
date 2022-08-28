package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command child class to delete stored task.
 */
public class DeleteCommand extends Command {

    /**
     * Initialises DeleteCommand object with specified task index.
     *
     * @param index Index of task to be deleted
     */
    public DeleteCommand(int index) {
        super(index);
    }

    /**
     * Deletes task which is at the index stored.
     *
     * @param tasks   TaskList object corresponding to all tasks
     * @param ui      Ui object to show user output/errors
     * @param storage Storage object to save data after execution
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(index, ui);
        storage.saveToFile(tasks);
    }
}
