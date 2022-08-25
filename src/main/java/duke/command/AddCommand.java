package duke.command;

import duke.exception.FileDoesNotExistException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class that encapsulates the action of adding a specific task into the task list.
 */
public class AddCommand extends Command {
    private Task taskToAdd;

    /**
     * Constructs an AddCommand instance
     * @param taskToAdd Task instance that needs to be added.
     */
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws FileDoesNotExistException {
        tasks.addTask(taskToAdd);
        storage.save(tasks);
        ui.showAddTask(taskToAdd, tasks.getNumOfRemainingTasks());
    }

    @Override
    public boolean bye() {
        return false;
    }
}
