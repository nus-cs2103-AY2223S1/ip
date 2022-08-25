public class WrongCommand extends Command{
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printBorder();
        String message = "Please enter some valid Command";
        ui.displayCommandMessage(message, null, null);
        ui.printBorder();
    }
}
