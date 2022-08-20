public class CommandUnknown extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showText("There is a mistake in the command given, please try again.");
    }
}
