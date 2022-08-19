package duke.command;

public class UnknownCommand extends Command {
    private String command;

    UnknownCommand(String command) {
        super.isExit = false;
        this.command = command;
    }

    @Override
    void execute() {
        Command.ui.displayUnknownCommandMessage(this.command);
    }
}
