package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command class to add specified task
 */
public class AddCommand extends Command {
    /**
     * Initialises AddCommand object with specified task.
     *
     * @param task Task to be added
     */
    public AddCommand(Task task) {
        super(task);
    }

    /**
     * Adds stored task to tasks TaskList, displays result and saves tasks to file.
     *
     * @param tasks   TaskList object corresponding to all tasks
     * @param ui      Ui object to show user output/errors
     * @param storage Storage object to save data after execution
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task, ui);
        storage.saveToFile(tasks);
    }
}
