package duke.chatbot.command;

/**
 * A command that prints an error message.
 *
 * @author jq1836
 */
public class InvalidInputCommand extends Command {
    public InvalidInputCommand(String message) {
        arguments = message;
    }

    /**
     * Returns an instance of {@link CommandResult} with a message that displays an error message.
     *
     * @return The result after executing the command.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(arguments);
    }
}
