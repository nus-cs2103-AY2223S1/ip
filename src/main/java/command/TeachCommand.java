package command;

/**
 * Handles the Teach command, which allows the user
 * to have a "conversation" with Henry. Currently only
 * supports teaching Henry new words.
 */
public class TeachCommand extends Command {

    public static final String COMMAND_WORD = "teach";
    private final String response;

    /**
     * Creates a TeachCommand
     *
     * @param botResponse the message received from the AIML bot.
     */
    public TeachCommand(String botResponse) {
        this.response = botResponse;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(response);
    }
}
