public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage st) {
        ui.tedResponse(tasks.list());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
