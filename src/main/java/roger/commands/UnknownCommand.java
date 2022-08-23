package roger.commands;

import roger.Storage;
import roger.TaskList;
import roger.Ui;
import roger.commands.Command;

public class UnknownCommand extends Command {

    /**
     * Notify the user that Roger does not understand the command.
     *
     * @param tasks The TaskList of the Roger program.
     * @param ui The Ui used.
     * @param storage The storage to read and load to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Uncle really don't understand.");
    }
}
