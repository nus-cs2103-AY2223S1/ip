package duke.command;

import duke.TaskList;
import duke.models.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Adds new {@link Task} to the {@link TaskList}
 */
public class AddCommand extends Command {
    private final Task toAdd;

    /**
     * Initializes the AddCommand to add a task to the {@link TaskList}
     * @param task Task to be added
     */
    public AddCommand(Task task) {
        this.toAdd = task;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.addTask(toAdd);
        storage.write(toAdd.stringToWrite());
        return ui.newItemAdded(toAdd, tasks.getSize());
    }
}
