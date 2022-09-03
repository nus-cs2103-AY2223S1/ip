package duke.command;

import duke.TaskList;
import duke.models.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Adds new Task to the TaskList
 */
public class AddCommand extends Command {
    private final Task toAdd;

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
