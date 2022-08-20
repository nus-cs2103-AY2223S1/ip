public class CommandExit extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showText("Adiós, hasta luego!");
        System.exit(0);
    }
}
