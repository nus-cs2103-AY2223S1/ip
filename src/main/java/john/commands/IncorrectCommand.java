package john.commands;

/**
 * Represents an incorrect command.
 */
public class IncorrectCommand extends Command {
    private String command;
    private String format;

    /**
     * Constructor for an IncorrectCommand.
     *
     * @param command The unknown command entered.
     */
    public IncorrectCommand(String command) {
        this.command = command;
    }

    /**
     * Constructor for an IncorrectCommand.
     *
     * @param command The known command entered.
     * @param format The correct format of the command.
     */
    public IncorrectCommand(String command, String format) {
        this.command = command;
        this.format = format;
    }

    /**
     * Returns the feedback of an incorrect command.
     *
     * @return A string representation of the incorrect command.
     */
    @Override
    public String execute() {
        if (this.format == null) {
            return ui.showIncorrectCommand(command);
        }
        return ui.showIncorrectCommandWithFormat(command, format);
    }
}
