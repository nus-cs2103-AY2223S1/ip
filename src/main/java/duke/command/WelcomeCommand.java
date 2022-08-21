package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printWelcomeMessage();
    }
}

