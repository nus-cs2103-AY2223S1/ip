package duke.command;

import duke.Ui;

/**
 * Command for exiting program.
 */
public class ByeCommand extends Command {
    /**
     * Constructor for ByeCommand.
     * {@code isExit = true} to signal exiting of application.
     */
    public ByeCommand() {
        super.isExit = true;
    }

    /**
     * {@inheritDoc}}
     * Displays exit message.
     */
    @Override
    public String execute() {
        return Ui.getExitMessage();
    }
}
