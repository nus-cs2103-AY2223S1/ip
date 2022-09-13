package command;

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
