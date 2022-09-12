package jarvis.command;

import jarvis.JarvisException;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

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
     * @param storage stores the tasks locally.
     * @param tasks the list of tasks.
     * @param ui prints feedback.
     * @return response after executing the command.
     * @throws JarvisException exception for invalid commands.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return tasks.toString();
    }
}
