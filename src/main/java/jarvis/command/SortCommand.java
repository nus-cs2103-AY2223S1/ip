package jarvis.command;

import jarvis.JarvisException;
import jarvis.storage.Storage;
import jarvis.task.TaskList;

/**
 * SortCommand --- sort tasks by specified order.
 */
public class SortCommand extends Command {
    /**
     * Constructor.
     *
     * @param fullCommand the command entered by the user.
     */
    public SortCommand(String fullCommand) {
        super(fullCommand);
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
        tasks.sortTasks();
        return tasks.toString();
    }
}
