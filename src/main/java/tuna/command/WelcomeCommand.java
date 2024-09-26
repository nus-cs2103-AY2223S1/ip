package tuna.command;

import tuna.utility.Storage;
import tuna.utility.TaskList;
import tuna.utility.Ui;

/**
 * Represents a welcome command.
 */
public class WelcomeCommand extends Command {

    /**
     * Creates a WelcomeCommand object.
     */
    public WelcomeCommand() {
        super(CommandType.WELCOME);
    }

    /**
     * Executes the welcome command, printing the welcome message.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.welcomeMessage();
    }
}

