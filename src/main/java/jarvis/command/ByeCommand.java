package jarvis.command;

import jarvis.storage.Storage;
import jarvis.task.TaskList;

/**
 * ByeCommand --- command to quit the application.
 */
public class ByeCommand extends Command {
    /**
     * Constructor.
     *
     * @param command the command entered by the user.
     */
    public ByeCommand(String command) {
        super(command);
    }

    /**
     * Executes the command.
     *
     * @param tasks the list of tasks.
     * @param storage stores the tasks locally.
     * @return response after executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye! Hope to see you again soon!";
    }
}
