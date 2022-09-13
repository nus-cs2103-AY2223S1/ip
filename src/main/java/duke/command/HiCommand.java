/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * public class HiCommand to handle Welcome/Hi/CommandList command.
 */
public class HiCommand extends Command {

    /**
     * public constructor for HiCommand.
     */
    public HiCommand() {
        super();
        this.isExit = false;
    }

    /**
     * public method execute to execute the hi command.
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printWelcome();
    }
}
