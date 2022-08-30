/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * public class ByeCommand to handle Exit command.
 */
public class ByeCommand extends Command{

    /**
     * public constructor for ByeCommand.
     */
    public ByeCommand() {
        super();
        this.isExit = true;
    }

    /**
     * public method execute to execute command.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printBye();
    }
}
