package duke.command;

public class ByeCommand extends Command {
    public ByeCommand() {
        super.isExit = true;
    }

    @Override
    public void execute() {
        Command.ui.displayExitMessage();
    }
}
