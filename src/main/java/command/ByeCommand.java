package command;

import duke.Ui;

/**
 * An abstract class that represents ByeCommand which extends Command
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.2
 * @since   2022-9-15
 */
public class ByeCommand extends Command {
    Ui ui;

    /**
     * Constructor for ByeCommand Object
     */
    public ByeCommand() {
        this.ui = new Ui();
    }

    /**
     * Returns a string of the executed ByeCommand
     * @return a string after the execution of ByeCommand
     */
    @Override
    public String execute() {
        return ui.goodbyeMessage();
    }
}
