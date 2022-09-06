package john.commands;

/**
 * Represents a command to display all the available commands.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    /**
     * Returns a string showing all the available commands.
     *
     * @return A string showing all the available commands.
     */
    @Override
    public String execute() {
        return ui.showHelp();
    }
}
