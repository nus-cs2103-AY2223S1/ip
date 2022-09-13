package duke.commands;

import java.util.Set;

/**
 * Help Command Class
 */
public class HelpCommand implements BaseCommand {
    public static final String COMMAND_WORD = "help";
    private String successMessage = "Here are some wishes you can try:\n";
    private final Set<String> availableCommands;

    /**
     * Help Command constructor method
     *
     * @param availableCommands
     *            The set of strings of available command words
     */
    public HelpCommand(Set<String> availableCommands) {
        this.availableCommands = availableCommands;
    }

    @Override
    public CommandResult execute() {
        successMessage = String.format(
                "%s%s",
                successMessage,
                String.join("\n", availableCommands));
        return new CommandResult(successMessage);
    }

}
