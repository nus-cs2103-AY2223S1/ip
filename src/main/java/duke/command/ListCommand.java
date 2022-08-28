package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents a command to list all current tasks.
 */
public class ListCommand extends Command{

    /**
     * Initialises the list command.
     */
    public ListCommand() {
    }

    /**
     * Executes this command.
     * @param tasks Task list to be listed.
     * @param ui UI to display list.
     * @param storage Storage used in application.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }

    /**
     * Checks if Duke application should exit after this command.
     * @return False as this command is not bye.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
