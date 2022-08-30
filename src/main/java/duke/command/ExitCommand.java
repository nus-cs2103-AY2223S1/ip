package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command{

    /**
     * Initialises the exit command.
     */
    public ExitCommand() {
    }

    /**
     * Executes this command.
     * @param tasks Task list used in application.
     * @param ui UI to display bye message.
     * @param storage Storage used in application.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    /**
     * Checks if Duke application should exit after this command.
     * @return True as bye is the command to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
