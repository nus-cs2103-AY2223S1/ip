public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        ui.displayMessage(taskList.taskListString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}