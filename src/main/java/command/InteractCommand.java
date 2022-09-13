package command;

/**
 * Handles the Interact command, which allows the user
 * to have a "conversation" with Henry. Currently only
 * supports teaching Henry new words.
 */
public class InteractCommand extends Command {

    public static final String COMMAND_WORD = "interact";
    private final String response;

    public InteractCommand(String botResponse) {
        this.response = botResponse;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(response);
    }
}
