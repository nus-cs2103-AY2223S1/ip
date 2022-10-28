package command;

/**
 * Handles the Echo command, which reads the user's
 * input and prints it to the console.
 */
public class EchoCommand extends Command {

    public static final String COMMAND_WORD = "echo";
    private final String echoedText;

    /**
     * Creates an EchoCommand.
     *
     * @param description the message to be echoed to the user.
     */
    public EchoCommand(String description) {
        this.echoedText = description;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(echoedText);
    }
}
