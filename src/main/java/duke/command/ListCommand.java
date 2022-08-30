/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * puvlic class ListCommand that handles List Command/enumerate the tasklist.
 */
public class ListCommand extends Command{

    /**
     * public constructor for ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * public method execute to execute command.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printMessage(tasks.enumerate());
    }
}