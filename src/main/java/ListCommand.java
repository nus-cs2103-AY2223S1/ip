public class ListCommand extends Command {

    public ListCommand() {
        isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FredException {
        ui.showMessage(tasks.list());
    }
}
