package commands;

import duke.Ui;

/**
 * Command that describes inability to understand user input.
 */
public class UnknownCommand extends Command {

    private Ui ui;

    /**
     * Returns a new UnknownCommand.
     * @param ui User Interface that prints a message to the user.
     */
    public UnknownCommand(Ui ui) {
        this.ui = ui;
    }

    /**
     * Executes the command, and returns a String
     * describing the execution of this Command.
     * @return A String describing the program's inability to understand input.
     */
    public String execute() {
        return ui.showUnknown();
    }
}
