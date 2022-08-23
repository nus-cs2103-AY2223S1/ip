public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public boolean isBye() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}
