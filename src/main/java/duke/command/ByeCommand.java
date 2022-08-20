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
     * Constructor for ByeCommand.
     */
    public ByeCommand() {
        super();
        this.isExit = true;
    }

    /**
     * Displays the farewell message.
     *
     * @param tasks TaskList containing the Task list.
     * @param ui Ui handling interactions with the user.
     * @param storage Storage handling loading data from and saving data to files.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }
}
