public class ExitCommand extends Command{
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        storage.writeFile(taskList);
        ui.exit();
    }
}
