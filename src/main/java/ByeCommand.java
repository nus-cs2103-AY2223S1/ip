public class ByeCommand extends Command {
    ByeCommand() {
        super.isExit = true;
    }

    @Override
    public void execute() {
        Command.ui.printMessages(new String[]{"Bye. Hope to see you again soon!"});
    }
}
