public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(tasks.showTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
