package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Encapsulation of the command of adding tasks to a list.
 *
 * @author Sun Ruoxin
 */
public class AddCommand extends Command {
    /** The task to be added to the list. */
    protected Task task;

    /**
     * Class constructor.
     *
     * @param task the task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command.
     * Adds the task to the list.
     * Rewrites the file in the hard disk.
     * Shows the feedback to the user.
     *
     * @param tasks the list of tasks
     * @param ui the UI
     * @param storage the storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.writeFile(tasks, ui);
        ui.addMessage(tasks, task);
    }

    /**
     * Returns a boolean value representing whether to exit the programme
     * after the command is executed.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
