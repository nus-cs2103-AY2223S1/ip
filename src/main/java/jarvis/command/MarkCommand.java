package jarvis.command;

import jarvis.JarvisException;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

/**
 * MarkCommand --- command to mark tasks as done or undone.
 */
public class MarkCommand extends Command {
    /**
     * Constructor.
     *
     * @param command the command entered by the user.
     */
    public MarkCommand(String command) {
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

        if (getKeyCommand().equals("mark")) {
            tasks.get(taskIndex).setIsDone(true);
            storage.saveTasks(tasks);
            return ui.showTaskDoneMessage(taskIndex, tasks);
        } else {
            tasks.get(taskIndex).setIsDone(false);
            storage.saveTasks(tasks);
            return ui.showTaskUndoneMessage(taskIndex, tasks);
        }
    }
}
