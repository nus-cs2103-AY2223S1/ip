package tuna.command;

import tuna.Storage;
import tuna.TaskList;
import tuna.Ui;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.byeMessage();
    }
}
