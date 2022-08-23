public class ByeCommand extends Command {

    public static final String COMMAND_NAME = "bye";

    ByeCommand() {

    }

    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isTerminator() {
        return true;
    }
}
