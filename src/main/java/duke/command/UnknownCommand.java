package duke.command;

public class UnknownCommand extends Command {
    private String command;

    public UnknownCommand(String command) {
        super.isExit = false;
        this.command = command;
    }

    @Override
    public void execute() {
        Command.ui.displayUnknownCommandMessage(this.command);
    }
}
