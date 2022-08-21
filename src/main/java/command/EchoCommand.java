package command;

public class EchoCommand extends Command {

    public static final String COMMAND_WORD = "echo";
    private final String description;

    public EchoCommand(String description) {
        this.description = description;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(description);
    }
}
