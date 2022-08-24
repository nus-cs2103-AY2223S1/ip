package jarvis.command;

import jarvis.JarvisException;
import jarvis.storage.Storage;
import jarvis.task.TaskList;

/**
 * ListCommand --- command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Constructor.
     *
     * @param command the command entered by the user.
     */
    public ListCommand(String command) {
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
    public String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }
}
