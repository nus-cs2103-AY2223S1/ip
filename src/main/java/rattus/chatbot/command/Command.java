package rattus.chatbot.command;

import rattus.chatbot.Rattus;
import rattus.chatbot.data.exception.InvalidInputException;
import rattus.chatbot.util.MessageBuilder;

/**
 * A command to be executed and outputs a result.
 *
 * @author jq1836
 */
public abstract class Command {
    protected Rattus duke;

    protected String arguments;

    protected final MessageBuilder messageBuilder = new MessageBuilder();

    /**
     * Returns false. Used to detect whether a command results in the closing of the application.
     *
     * @return false
     */
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Returns a string which represents the message built.
     *
     * @return A string which represents the message built.
     */
    protected abstract String buildMessage();

    /**
     * Returns a {@link CommandResult} instance after execution.
     *
     * @return The result after executing the command.
     * @throws InvalidInputException If arguments passed to Command is invalid.
     */
    public CommandResult execute() throws InvalidInputException {
        assert (duke != null);
        return new CommandResult(buildMessage());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Command)) {
            return false;
        }
        Command command = (Command) obj;
        return command.arguments.equals(arguments);
    }

    /**
     * Initialises the command by providing the required for the command to act on.
     *
     * @param duke The instance of duke the command is to be run in.
     */
    public void initCommand(Rattus duke) {
        this.duke = duke;
    }
}
