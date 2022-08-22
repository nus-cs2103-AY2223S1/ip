public class ListCommand extends Command{
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.print(taskList);
    }
}
