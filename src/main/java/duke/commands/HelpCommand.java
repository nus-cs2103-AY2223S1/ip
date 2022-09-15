package duke.commands;

import duke.ui.Ui;

/**
 * Class for help command.
 */
public class HelpCommand extends Command {


    /**
     * Calls the method to print out help message for user.
     */
    public void executeCommand() {
        Ui.getInstance().getHelpMessage();
    }
}
