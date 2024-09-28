package jarvis.command;

import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

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
     * @param storage stores the tasks locally.
     * @param tasks the list of tasks.
     * @param ui prints feedback.
     * @return response after executing the command.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.showByeMessage();
    }
}
