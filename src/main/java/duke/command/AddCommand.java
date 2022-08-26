package duke.command;

import duke.TaskList;
import duke.models.Task;
import duke.storage.Storage;

/**
 * Adds new Task to the TaskList
 */
public class AddCommand extends Command {
    private final Task toAdd;

    public AddCommand(Task task) {
        this.toAdd = task;
    }


    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.addTask(toAdd);
        storage.write(toAdd.stringToWrite());
    }
}
