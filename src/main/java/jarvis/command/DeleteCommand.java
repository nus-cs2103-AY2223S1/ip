package jarvis.command;

import jarvis.JarvisException;
import jarvis.storage.Storage;
import jarvis.task.Task;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

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
     * @param storage stores the tasks locally.
     * @param tasks the list of tasks.
     * @param ui prints feedback.
     * @return response after executing the command.
     * @throws JarvisException exception for invalid commands.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws JarvisException {
        int taskIndex = super.getTaskIndex();
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new JarvisException("No task found. Please enter a valid task number.");
        }
        Task task = tasks.remove(taskIndex);
        storage.saveTasks(tasks);
        return ui.showTaskDeletedMessage(task, tasks.size());
    }
}
