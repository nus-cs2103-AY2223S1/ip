package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ByeCommand is a command that exits the program.
 */
public class ByeCommand extends Command {
    /**
     * Constructor of ByeCommand.
     */
    public ByeCommand() {
        super();
        this.setExitToTrue();
    }

    /**
     * Displays the bye message.
     *
     * @param tasks Tasklist containing the tasks.
     * @param ui Ui handling the user interaction.
     * @param storage Storage to store data.
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.printByeMessage();
    }
}
