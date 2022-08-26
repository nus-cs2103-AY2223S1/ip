public class ListCommand extends Command{
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        String message = "";
        message += "Here are the tasks in your list:\n";
        message += taskList.printTaskList();
        ui.printMessage(message);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
