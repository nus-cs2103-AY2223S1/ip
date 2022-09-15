package duke.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to delete a task from the list of tasks.
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private List<Integer> taskIndices;

    /**
     * Creates a Command to delete tasks.
     *
     * @param taskIndices Task indices to delete.
     */
    public DeleteTaskCommand(List<Integer> taskIndices) {
        this.taskIndices = taskIndices;
    }

    /**
     * Deletes the tasks from the task list.
     *
     * @param tasks List of tasks.
     * @param storage Storage for the task list.
     * @return Result of the deletion.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        taskIndices = new ArrayList<>(new HashSet<>(taskIndices)); // Remove duplicates
        Task[] removedTasks = new Task[taskIndices.size()];
        for (int i = taskIndices.size() - 1; i >= 0; i--) {
            Task deletedTask = tasks.deleteTask(taskIndices.get(i));
            removedTasks[i] = deletedTask;
        }
        storage.write(tasks);
        StringBuilder ret = new StringBuilder("Noted. I've removed the following tasks:");
        for (Task deletedTask : removedTasks) {
            ret.append("\n\t").append(deletedTask);
        }
        ret.append("\nNow you have ").append(tasks.size()).append(" tasks in the list.");
        return ret.toString();
    }
}
