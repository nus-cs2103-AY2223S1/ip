package duke;

/**
 * ListCommand is a Command that lists out tasks in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand and outputs tasks in TaskList to the user.
     *
     * @param taskList list of tasks in Duke application.
     * @param ui user interface for Duke.
     * @param storage storage that stores the taskList into the hard drive.
     * @return tasks in TaskList.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.list(taskList);
    }
}
