package jarvis.command;

import jarvis.JarvisException;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

/**
 * SortCommand --- command to sort tasks by deadlines and event start times.
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
     * @param storage stores the tasks locally.
     * @param tasks the list of tasks.
     * @param ui prints feedback.
     * @return response after executing the command.
     * @throws JarvisException exception for invalid commands.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws JarvisException {
        tasks.sortTasks();
        return tasks.toString();
    }
}
