package jarvis.command;

import jarvis.JarvisException;
import jarvis.storage.Storage;
import jarvis.task.Task;
import jarvis.task.TaskList;

/**
 * DeleteCommand --- command to delete tasks.
 */
public class DeleteCommand extends Command {
    /**
     * Constructor.
     *
     * @param command the command entered by the user.
     */
    public DeleteCommand(String command) {
        super(command);
    }

    /**
     * Executes the command.
     *
     * @param tasks the list of tasks.
     * @param storage stores the tasks locally.
     * @return response after executing the command.
     * @throws JarvisException exception for invalid commands.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws JarvisException {
        int taskIndex = super.getTaskIndex();
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new JarvisException("No task found. Please enter a valid task number.");
        }
        Task task = tasks.remove(taskIndex);
        storage.saveTasks(tasks);
        return String.format("Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.",
                task, tasks.size());
    }
}
