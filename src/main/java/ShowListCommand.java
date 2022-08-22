public class ShowListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
