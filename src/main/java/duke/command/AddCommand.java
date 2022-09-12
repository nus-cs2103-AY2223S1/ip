package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds a task to the existing storage
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Creates a command to add a task to the existing storage
     *
     * @param task task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Determines if the command should end the program for the user
     *
     * @return false by default
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the addition of a task from the storage
     *
     * @param tasks the list of tasks to be modified in execution
     * @param ui the ui used to display messages to the user upon successful addition
     * @param storage the storage to be modified in execution
     * @throws DukeException if the change cannot be saved in storage successfully
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.task);
        storage.save(this.task.toSimpleString());
        ui.showAddition(this.task, tasks.getCount());
    }
}
