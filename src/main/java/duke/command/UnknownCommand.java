package duke.command;

/**
 * Command for unknown commands given.
 */
public class UnknownCommand extends Command {
    private String command;

    /**
     * Constructor for Unknown command.
     *
     * @param command Input given by user that resulted in this command.
     */
    public UnknownCommand(String command) {
        super.isExit = false;
        this.command = command;
    }

    /**
     * Displays "unknown command" message.
     */
    @Override
    public void execute() {
        Command.ui.displayUnknownCommandMessage(this.command);
    }
}
