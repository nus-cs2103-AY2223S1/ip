public class ByeCommand extends Command {
    ByeCommand() {
        super.isExit = true;
    }

    @Override
    void execute() {
        Command.ui.displayExitMessage();
    }
}
