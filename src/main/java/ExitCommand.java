public class ExitCommand extends Command {


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveTaskList(taskList);
        ui.displayExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}