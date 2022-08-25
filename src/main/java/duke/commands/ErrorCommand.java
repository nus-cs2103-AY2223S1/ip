package duke.commands;

public class ErrorCommand implements BaseCommand {
    private final String message;

    public ErrorCommand(String message) {
        this.message = message;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(this.message);
    }
}
