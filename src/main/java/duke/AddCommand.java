package duke;

/**
 * Encapsulate the command that allow users to add new task to the TaskList,
 * which is-a Command.
 *
 * @author: Jonas Png
 */
public class AddCommand extends Command {

    private Task newTask;

    /**
     * Class constructor for AddCommand class.
     *
     * @param newTask new task to be added
     */
    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    /**
     * Adds new task to the tasklist and update the data file.
     *
     * @param tasks list of existing tasks
     * @param ui user interface to be shown
     * @param storage to rewrite the data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(newTask);
        ui.showAddedTask(newTask, tasks);
        tasks.updateStorage(storage);
    }
}
