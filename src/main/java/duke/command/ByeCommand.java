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
     * Returns the bye message.
     *
     * @param tasks Tasklist containing the tasks.
     * @param ui Ui handling the user interaction.
     * @param storage Storage to store data.
     * @return Bye Message string
     */
    @Override
    public String run(TaskList tasks, Ui ui, Storage storage) {
        return ui.printByeMessage();
    }
}
