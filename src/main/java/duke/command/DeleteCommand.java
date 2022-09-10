package duke.command;

import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command to delete one or more tasks from the list.
 */
public class DeleteCommand extends Command {
    private List<Integer> indices;

    /**
     * Creates a DeleteCommand.
     *
     * @param indices List of task IDs of tasks to be deleted.
     */
    public DeleteCommand(List<Integer> indices) {
        // sort from largest to smallest, else deleting will fail due to decreasing array size.
        indices.sort((c1, c2) -> c1 > c2 ? -1 : 1);
        this.indices = indices;
    }

    /**
     * Executes the DeleteCommand to delete tasks identified by {@code}indices{@code} from the list.
     *
     * @param tasks TaskList that the task(s) will be deleted from.
     * @param ui Ui that displays success or error to user.
     * @param storage Persistent storage of task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String deletedTasks = "";
        for (int index : indices) {
            String deletedTask = tasks.delete(index);
            deletedTasks = deletedTask + "\n" + deletedTasks;
        }
        storage.save(tasks);
        String plurality = indices.size() > 1 ? "these tasks" : "this task";
        return "I've removed " + plurality + ":\n" + deletedTasks;
    }

    /**
     * Computes equality of two DeleteCommands.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeleteCommand)) {
            return false;
        }
        DeleteCommand otherCommand = (DeleteCommand) o;
        return this.indices.equals(otherCommand.indices);
    }
}
