package duke;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Lists all tasks in the task list.
     *
     * @param tasks   the task list
     * @param ui      the user interface
     * @param storage the storage
     * @return the message to be printed
     * @throws DukeException if there is an error writing to the file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }
}
