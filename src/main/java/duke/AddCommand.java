package duke;

/**
 * Encapsulate the command that allow users to add new task to the TaskList,
 * which is-a Command.
 */
public class AddCommand extends Command {

    private Task newTask;

    /**
     * Class constructor for AddCommand class.
     *
     * @param newTask new task to be added.
     */
    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(newTask);
        tasks.updateStorage(storage);
        return ui.showAddedTask(newTask, tasks);
    }
}
