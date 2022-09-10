package duke.commands;

import java.util.Objects;
import java.util.Set;

/**
 * Help Command Class
 */
public class HelpCommand implements BaseCommand {
    public static final String COMMAND_WORD = "help";
    private String successMessage = "Here are some commands you can try:\n";
    private final Set<String> availableCommands;

    /**
     * Help Command constructor method
     * @param availableCommands The set of strings of available command words
     */
    public HelpCommand(Set<String> availableCommands) {
        this.availableCommands = availableCommands;
    }

    /**
     * Returns the input string formatted with borders.
     *
     * @param text
     *            Text to be formatted
     * @return Formatted text
     */
    public String formatOutputString(String text) {
        assert Objects.nonNull(text);
        String borderMessage = "______________________";
        return String.format(
                "\n%s\n%s\n%s\n",
                borderMessage,
                text,
                borderMessage);
    }

    @Override
    public CommandResult execute() {
        successMessage = String.format(
                "%s%s",
                successMessage,
                String.join("\n", availableCommands));
        return new CommandResult(formatOutputString(successMessage));
    }

}
