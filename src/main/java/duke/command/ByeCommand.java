package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ByeCommand is a Command that exits the program.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class ByeCommand extends Command {
    /**
     * Returns the farewell message and terminates the program.
     *
     * @param tasks TaskList containing the Task list.
     * @param ui Ui handling interactions with the user.
     * @param storage Storage handling loading data from and saving data to files.
     * @return The farewell message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
    }
}
