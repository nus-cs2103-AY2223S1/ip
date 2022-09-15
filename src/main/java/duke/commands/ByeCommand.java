package duke.commands;

import duke.ui.Ui;

/**
 * Class to represent bye command.
 */
public class ByeCommand extends Command {

    /**
     * Calls the relevant methods to print the shut down message.
     */
    @Override
    public void executeCommand() {
        Ui.getInstance().shutDownDuke();
    }
}
