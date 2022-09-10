package duke.command;

import java.util.List;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command to mark one or more tasks as undone.
 */
public class UnmarkCommand extends Command {
    private List<Integer> indices;

    /**
     * Creates a UnmarkCommand.
     *
     * @param index List of task IDs of tasks to be marked as undone.
     */
    public UnmarkCommand(List<Integer> indices) {
        this.indices = indices;
    }

    /**
     * Executes the UnmarkCommand to mark tasks identified by {@code}indices{@code} as undone.
     *
     * @param tasks TaskList containing the task(s) to be marked as undone.
     * @param ui Ui that displays success or error to user.
     * @param storage Persistent storage of task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String undoneTasks = "";
        for (int index : indices) {
            String undoneTask = tasks.markAsUndone(index);
            undoneTasks += undoneTask + "\n";
        }
        storage.save(tasks);
        String plurality = indices.size() > 1 ? "these tasks" : "this task";
        return "I've marked " + plurality + " as not done:\n" + undoneTasks;
    }
}
