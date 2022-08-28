package roger.commands;

import java.util.List;

import roger.Storage;
import roger.TaskList;
import roger.Ui;
import roger.tasks.Task;


/**
 * Encapsulates the command to delete a task.
 */
public class FindCommand extends Command {
    protected String query;

    /**
     * Create a FindCommand.
     *
     * @param query The query string to search on.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Find and return any tasks that contain the given query string.
     *
     * @param tasks The TaskList of the Roger program.
     * @param ui The Ui used.
     * @param storage The storage to read and load to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> tasksMatched = tasks.search(this.query);

        if (tasksMatched.isEmpty()) {
            ui.show("No tasks matching that string.");
            return;
        }

        ui.show("Here are the matching tasks in your list:");
        for (Task task: tasksMatched) {
            ui.show(String.valueOf(tasks.getTaskNum(task)) + ". " + task.toString());
        }
    }
}
