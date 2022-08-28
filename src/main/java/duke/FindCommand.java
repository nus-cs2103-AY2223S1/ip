package duke;

/**
 * Represents a command to find tasks in a task list.
 */
public class FindCommand extends Command {
    private String searchTerm;
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = tasks.find(searchTerm);
        if (foundTasks.size() == 0) {
            ui.showError("No tasks found.");
        } else {
            ui.showList(foundTasks);
        }
    }
}
