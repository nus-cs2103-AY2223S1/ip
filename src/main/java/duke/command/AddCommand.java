package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {

    Task task;

    /**
     * Initialises the AddCommand with the task to be added.
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes this command to add a task to the list and update storage.
     * @param tasks Task list that task is added to.
     * @param ui UI to display success on addition.
     * @param storage Storage to be updated with newly added task.
     * @throws DukeException If unable to write to storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.task);
        storage.update(tasks);
        ui.showAddMessage(this.task, tasks.getNumTasks());
    };

    /**
     * Checks if Duke application should exit after this command.
     * @return False as this command is not bye.
     */
    @Override
    public boolean isExit() {
        return false;
    };

}
