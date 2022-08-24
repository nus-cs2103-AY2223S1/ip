package duke;

/**
 * Represents a List Command, which lists all tasks.
 *
 * @author Elgin
 */
public class ListCommand extends Command {
    /**
     * Executes the command and lists all tasks to the user.
     *
     * @param tasks All tasks present in Duke.
     * @param ui The UI controller that handles interaction between user and Duke.
     * @param storage Storage that stores all tasks on Disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String items = tasks.allItems();

        ui.showAllTasks(items);
    }
}
