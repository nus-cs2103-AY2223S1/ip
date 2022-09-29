package command;

/**
 * Handles the Interact command, which allows the
 * user to have a "conversation" with Henry.
 */
public class InteractCommand extends Command {
    private final String response;

    public InteractCommand(String botResponse) {
        this.response = botResponse;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(response);
    }
}
