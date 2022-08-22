public class ByeCommand extends Command {
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        super.endApp();
        ui.printExit();
    }
}
