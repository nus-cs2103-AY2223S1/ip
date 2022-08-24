package jarvis.command;

import jarvis.JarvisException;
import jarvis.storage.Storage;
import jarvis.task.TaskList;

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

        if (getKeyCommand().equals("mark")) {
            tasks.get(taskIndex).setIsDone(true);
            storage.saveTasks(tasks);
            return "Nice! I've marked this task as done:\n\t" + tasks.get(taskIndex);
        } else {
            tasks.get(taskIndex).setIsDone(false);
            storage.saveTasks(tasks);
            return "Okay, I've marked this task as not done yet:\n\t" + tasks.get(taskIndex);
        }
    }
}
