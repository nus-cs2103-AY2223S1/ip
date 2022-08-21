package duke.command;

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
     * Displays exit message.
     */
    @Override
    public void execute() {
        Command.ui.displayExitMessage();
    }
}
