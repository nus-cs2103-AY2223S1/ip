package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a Bye command.
 */
public class ByeCommand extends Command {

    /**
     * Creates a ByeCommand object.
     */
    public ByeCommand() {
        super(CommandType.BYE);
    }

    /**
     * Executes the bye command, printing a bye message.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printByeMessage();
    }
}
