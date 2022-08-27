package blob.commands;

public class InvalidCommand extends Command {

    private String[] resultMessages;

    public InvalidCommand(String ...messages) {
        super(null);
        this.resultMessages = messages;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(resultMessages);
    }
}
