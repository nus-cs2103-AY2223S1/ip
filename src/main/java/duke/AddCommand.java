package duke;

/**
 * This class handles all commands related to adding tasks
 * and inherits from the Command class.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for the AddCommand class.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     * Adds a task to the tasklist.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        String str = ui.displayTask(ui.ADDED, task);
        str += ui.showTotalTasks(taskList);
        storage.save(taskList, ui);
        return str;
    }

    /**
     * Returns false.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
