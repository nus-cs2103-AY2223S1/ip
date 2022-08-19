public class ListCommand extends Command {
    ListCommand() {
        super.isExit = false;
    }

    @Override
    void execute() {
        Command.ui.formatAndPrint(Command.taskList.getLogs());
    }
}
