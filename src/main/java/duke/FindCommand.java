package duke;

/**
 * Represents a command to find tasks in a task list.
 */
public class FindCommand extends Command {

    private String searchTerm;

    /**
     * Creates a FindCommand.
     *
     * @param searchTerm the search term to find tasks
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Finds the tasks in the task list that contain the search term and prints the tasks found.
     *
     * @param tasks   the task list
     * @param ui      the user interface
     * @param storage the storage
     * @return the message to be printed
     * @throws DukeException if there is an error writing to the file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = tasks.find(searchTerm);
        if (foundTasks.size() == 0) {
            return ui.showError("No tasks found.");
        } else {
            return "Searching based on \"" + searchTerm
                    + "\"... \n\n" + ui.showList(foundTasks);
        }
    }
}
